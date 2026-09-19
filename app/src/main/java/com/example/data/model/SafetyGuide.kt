package com.example.data.model

data class SafetyGuide(
    val word: String,
    val whatParentsShouldKnow: List<String>,
    val safetyRisks: List<String>,
    val safePractice: List<String>,
    val age1To3: List<String>,
    val age4To7: List<String>,
    val getHelpNowIf: List<String>,
    val todaysAction: String,
    val rawText: String,
    val category: String = "General Safety",
    val isFavorite: Boolean = false,
    val isCustomAi: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
) {
    fun toFormattedSpeech(): String {
        val sb = StringBuilder()
        sb.append("Safety guide for ").append(word).append(". ")
        if (whatParentsShouldKnow.isNotEmpty()) {
            sb.append("What parents should know: ").append(whatParentsShouldKnow.joinToString(". ")).append(". ")
        }
        if (safetyRisks.isNotEmpty()) {
            sb.append("Safety risks: ").append(safetyRisks.joinToString(". ")).append(". ")
        }
        if (safePractice.isNotEmpty()) {
            sb.append("Safe practice: ").append(safePractice.joinToString(". ")).append(". ")
        }
        if (age1To3.isNotEmpty()) {
            sb.append("For ages 1 to 3: ").append(age1To3.joinToString(". ")).append(". ")
        }
        if (age4To7.isNotEmpty()) {
            sb.append("For ages 4 to 7: ").append(age4To7.joinToString(". ")).append(". ")
        }
        if (getHelpNowIf.isNotEmpty()) {
            sb.append("Get help now if: ").append(getHelpNowIf.joinToString(". ")).append(". ")
        }
        if (todaysAction.isNotBlank()) {
            sb.append("Today's parent action: ").append(todaysAction)
        }
        return sb.toString()
    }
}
