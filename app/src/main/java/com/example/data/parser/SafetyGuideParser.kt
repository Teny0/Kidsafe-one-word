package com.example.data.parser

import com.example.data.model.SafetyGuide

object SafetyGuideParser {

    fun parse(word: String, rawText: String, isFavorite: Boolean = false, isCustomAi: Boolean = false): SafetyGuide {
        val lines = rawText.lines().map { it.trim() }

        var currentSection = ""
        val whatParentsKnow = mutableListOf<String>()
        val safetyRisks = mutableListOf<String>()
        val safePractice = mutableListOf<String>()
        val age1To3 = mutableListOf<String>()
        val age4To7 = mutableListOf<String>()
        val getHelpNowIf = mutableListOf<String>()
        var todaysAction = ""

        fun cleanBullet(line: String): String {
            return line
                .replaceFirst(Regex("^[•\\-*\\d.]+\\s*"), "")
                .trim()
        }

        for (line in lines) {
            if (line.isBlank()) continue

            val normalized = line.lowercase()

            when {
                normalized.contains("what parents should know") || normalized.contains("what parents know") -> {
                    currentSection = "know"
                }
                normalized.contains("safety risks") || normalized.contains("risks") -> {
                    currentSection = "risks"
                }
                normalized.contains("safe practice") || normalized.contains("safe practices") -> {
                    currentSection = "practice"
                }
                normalized.contains("age 1–3") || normalized.contains("age 1-3") || normalized.contains("1 to 3") -> {
                    currentSection = "age13"
                }
                normalized.contains("age 4–7") || normalized.contains("age 4-7") || normalized.contains("4 to 7") -> {
                    currentSection = "age47"
                }
                normalized.contains("get help now if") || normalized.contains("get help") || normalized.contains("emergency") -> {
                    currentSection = "help"
                }
                normalized.contains("today's parent action") || normalized.contains("todays parent action") || normalized.contains("parent action") -> {
                    currentSection = "action"
                }
                else -> {
                    val bulletText = cleanBullet(line)
                    if (bulletText.isNotBlank()) {
                        when (currentSection) {
                            "know" -> whatParentsKnow.add(bulletText)
                            "risks" -> safetyRisks.add(bulletText)
                            "practice" -> safePractice.add(bulletText)
                            "age13" -> age1To3.add(bulletText)
                            "age47" -> age4To7.add(bulletText)
                            "help" -> getHelpNowIf.add(bulletText)
                            "action" -> {
                                if (todaysAction.isBlank()) {
                                    todaysAction = bulletText
                                } else {
                                    todaysAction += " $bulletText"
                                }
                            }
                        }
                    }
                }
            }
        }

        // Infer category based on word
        val category = inferCategory(word)

        return SafetyGuide(
            word = word.trim().uppercase(),
            whatParentsShouldKnow = whatParentsKnow.ifEmpty { listOf("Supervise children closely around this item or activity.", "Follow age-appropriate guidelines for growth.") },
            safetyRisks = safetyRisks.ifEmpty { listOf("Accidental injury without attentive supervision.") },
            safePractice = safePractice.ifEmpty { listOf("Create clear safety boundaries.", "Keep hazards out of reach.") },
            age1To3 = age1To3.ifEmpty { listOf("Maintain continuous adult visual and arm's-reach contact.") },
            age4To7 = age4To7.ifEmpty { listOf("Teach clear boundaries and practice safe routines together.") },
            getHelpNowIf = getHelpNowIf.ifEmpty { listOf("Child exhibits sudden lethargy, breathing difficulty, or severe pain.", "Contact your pediatrician or emergency services if unsure.") },
            todaysAction = todaysAction.ifBlank { "Check your child's immediate environment today for safety hazards." },
            rawText = rawText,
            category = category,
            isFavorite = isFavorite,
            isCustomAi = isCustomAi
        )
    }

    private fun inferCategory(word: String): String {
        val w = word.lowercase().trim()
        return when {
            w in listOf("water", "milk", "food", "choking", "sugar", "salt", "snack", "grape", "honey", "nut", "allergy", "peanut", "egg") -> "Nutrition & Food Safety"
            w in listOf("sleep", "nap", "bedtime", "crib", "nightmare", "rest") -> "Sleep Safety"
            w in listOf("bath", "teeth", "tooth", "soap", "hygiene", "nails", "washing", "hands", "brushing") -> "Hygiene & Care"
            w in listOf("socket", "burns", "fire", "stairs", "furniture", "window", "sharp", "knife", "falls", "battery", "poison", "magnet", "cleaning") -> "Home Safety"
            w in listOf("medicine", "pill", "syrup", "dosing", "vitamin", "fever", "cough") -> "Medicine Safety"
            w in listOf("car", "seat", "pool", "sun", "playground", "helmet", "bike", "road", "street", "dog", "insect", "trampoline") -> "Outdoor Safety"
            w in listOf("screen", "tablet", "phone", "tv", "youtube", "game", "digital") -> "Digital Safety"
            w in listOf("tantrum", "touch", "bullying", "school", "crying", "anger", "stranger", "body", "feeling") -> "Emotional & Personal"
            else -> "Child Safety"
        }
    }
}
