package com.kasania.theartofwar

const val SharedPrefName = "TheArtOfWar"
const val SharedPrefKeyLastChapter = "LastChapter"
const val SharedPrefKeyLastPhrase = "LastPhrase"

const val SharedPrefKeyLastAccessDate = "LastDate"

const val SharedPrefKeyTotalAccessTimeMil = "TotalTime"
const val SharedPrefKeyTotalAccessDate = "TotalDate"

const val SharedPrefKeyLongestDailyTime = "LongestTime"
const val SharedPrefKeyTodayAccessTime = "TodayAccessTime"

const val SharedPrefKeyLongestAccessDate = "LongestDate"
const val SharedPrefKeyCurrentContinueAccessDate ="CurrentContinueDate"

const val BookmarkDataFileName = "BookMark"
const val PhraseAccessCountDataFileName = "PhraseAccessCount"

const val BackGroundImg = "Background"
const val SetPitchValue = "PitchValue"
const val SetPitchRate = "PitchRate"

const val totalPhraseNum = 174
const val minChapterNum = 1
const val maxChapterNum = 13
val maxPhraseNum = intArrayOf(0,14,9,10,9,10,16,14,8,20,16,28,8,12)
val sumOfPhrase =  intArrayOf(0,14,23,33,42,52,68,82,90,110,126,154,162,174)
val displayPhraseName  = arrayOf("총괄","1절","2절","3절","4절","5절","6절","7절","8절","9절","10절","11절","12절","13절","14절","15절","16절","17절","18절","19절","20절","21절","22절","23절","24절","25절","26절","27절","28절")
val displayMonthName = arrayOf("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC")

val enableAnimation = false
