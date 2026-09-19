package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.parser.SafetyGuideParser
import com.example.data.preloaded.PreloadedGuides
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("KidSafe OneWord", appName)
    }

    @Test
    fun `preloaded sleep guide has reference hours`() {
        val guide = PreloadedGuides.getGuide("SLEEP")
        assertNotNull(guide)
        assertEquals("SLEEP", guide?.word)
        assertTrue(guide?.whatParentsShouldKnow?.isNotEmpty() == true)
        assertTrue(guide?.whatParentsShouldKnow?.any { it.contains("11–14 hours") } == true)
    }

    @Test
    fun `preloaded water guide has reference liters`() {
        val guide = PreloadedGuides.getGuide("WATER")
        assertNotNull(guide)
        assertEquals("WATER", guide?.word)
        assertTrue(guide?.whatParentsShouldKnow?.any { it.contains("1.0–1.3 liters") } == true)
    }

    @Test
    fun `parse custom guide format accurately`() {
        val sample = """
FEVER

✅ What Parents Should Know
• Fever is a healthy immune response
• Hydration is the top priority

⚠️ Safety Risks
• Dehydration from fluid refusal

🛡️ Safe Practice
• Dress in light breathable layers

👶 Age 1–3
• Offer frequent small sips of water

🧒 Age 4–7
• Never administer aspirin

🚑 Get Help Now If
• Temperature exceeds 104F or child is unresponsive

🎯 Today's Parent Action
• Check medicine measuring syringe
        """.trimIndent()

        val parsed = SafetyGuideParser.parse("FEVER", sample)
        assertEquals("FEVER", parsed.word)
        assertEquals(2, parsed.whatParentsShouldKnow.size)
        assertEquals(1, parsed.safetyRisks.size)
        assertEquals(1, parsed.safePractice.size)
        assertEquals(1, parsed.age1To3.size)
        assertEquals(1, parsed.age4To7.size)
        assertEquals(1, parsed.getHelpNowIf.size)
        assertTrue(parsed.todaysAction.contains("syringe"))
    }
}
