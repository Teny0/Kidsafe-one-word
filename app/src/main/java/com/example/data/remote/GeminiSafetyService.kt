package com.example.data.remote

import com.example.BuildConfig
import com.example.data.model.SafetyGuide
import com.example.data.parser.SafetyGuideParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class GeminiSafetyService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val systemInstructionText = """
You are KidSafe OneWord, an expert child-safety assistant for parents of children aged 1–7 years.

ROLE
A parent enters one word only (example: sleep, water, fever, milk, screen, bath, school, choking, medicine, car, allergy, tantrum).
Your job is to instantly return a brief, practical, evidence-informed child safety guide based on current pediatric and child-safety recommendations.

RESPONSE RULES
- Use very simple English.
- Maximum 150 words.
- Short bullet points only.
- No long explanations.
- No medical jargon.
- No fear-based language.
- No unnecessary warnings.
- Focus on practical safety actions.
- Be precise and easy to read on a phone.
- Prioritize prevention and safety.
- If the topic is medical, remind parents to contact a pediatrician for concerns.
- If there are emergency warning signs, clearly state them.

KNOWLEDGE AREAS
Nutrition & Food Safety, Sleep, Hygiene, Home Safety, Medicine Safety, Outdoor Safety, Digital Safety, Emotional & Personal Safety

REFERENCE VALUES
Sleep Per Day:
Age 1–2: 11–14 hours
Age 3–5: 10–13 hours
Age 6–7: 9–12 hours

Water Per Day:
Age 1–3: Approximately 1–1.3 liters
Age 4–7: Approximately 1.3–1.7 liters

OUTPUT FORMAT
[INPUT WORD]

✅ What Parents Should Know
• Brief fact
• Brief fact

⚠️ Safety Risks
• Risk
• Risk

🛡️ Safe Practice
• Action
• Action

👶 Age 1–3
• Age-specific tip

🧒 Age 4–7
• Age-specific tip

🚑 Get Help Now If
• Emergency sign
• Emergency sign

🎯 Today's Parent Action
• One simple action

SPECIAL RULES
- Never diagnose illnesses.
- Never prescribe medication.
- Never replace emergency medical care.
- Never provide unsafe instructions.
- If information is uncertain, say: "Ask your pediatrician."
- Keep answers calm, supportive, practical, and evidence-informed.
- Always optimize for child safety.
- Always return the exact format above.
""".trimIndent()

    suspend fun generateGuide(word: String): Result<SafetyGuide> = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext Result.failure(
                IllegalStateException("Gemini API key is not configured. Please set GEMINI_API_KEY in the Secrets panel.")
            )
        }

        val cleanedWord = word.trim().uppercase()

        // Build Request JSON
        val requestJson = JSONObject().apply {
            // contents
            val contentsArray = JSONArray()
            val userContent = JSONObject().apply {
                val partsArray = JSONArray().apply {
                    put(JSONObject().apply {
                        put("text", "Word: $cleanedWord")
                    })
                }
                put("parts", partsArray)
            }
            contentsArray.put(userContent)
            put("contents", contentsArray)

            // systemInstruction
            val sysInstruction = JSONObject().apply {
                val partsArray = JSONArray().apply {
                    put(JSONObject().apply {
                        put("text", systemInstructionText)
                    })
                }
                put("parts", partsArray)
            }
            put("systemInstruction", sysInstruction)

            // generationConfig
            val genConfig = JSONObject().apply {
                put("temperature", 0.2)
                put("maxOutputTokens", 600)
            }
            put("generationConfig", genConfig)
        }

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = requestJson.toString().toRequestBody(mediaType)

        // Try primary model (gemini-2.5-flash), fallback to gemini-3.5-flash if needed
        val models = listOf("gemini-2.5-flash", "gemini-3.5-flash")
        var lastException: Exception? = null

        for (modelName in models) {
            val url = "https://generativelanguage.googleapis.com/v1beta/models/$modelName:generateContent?key=$apiKey"
            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()

            try {
                client.newCall(request).execute().use { response ->
                    val responseBody = response.body?.string() ?: ""
                    if (!response.isSuccessful) {
                        lastException = RuntimeException("API Error (${response.code}): $responseBody")
                        return@use // try next model
                    }

                    val json = JSONObject(responseBody)
                    val candidates = json.optJSONArray("candidates")
                    if (candidates != null && candidates.length() > 0) {
                        val firstCandidate = candidates.getJSONObject(0)
                        val contentObj = firstCandidate.optJSONObject("content")
                        val parts = contentObj?.optJSONArray("parts")
                        val text = parts?.optJSONObject(0)?.optString("text")

                        if (!text.isNullOrBlank()) {
                            val guide = SafetyGuideParser.parse(
                                word = cleanedWord,
                                rawText = text,
                                isCustomAi = true
                            )
                            return@withContext Result.success(guide)
                        }
                    }
                    lastException = RuntimeException("Empty response from AI")
                }
            } catch (e: Exception) {
                lastException = e
            }
        }

        return@withContext Result.failure(lastException ?: RuntimeException("Failed to generate child safety guide."))
    }
}
