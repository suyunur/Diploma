package com.example.diploma.data

import com.example.diploma.R
import com.example.diploma.data.model.User
import com.example.diploma.data.model.Vacancy

internal var CURRENT_USER: User? = null

internal var TOPIC_NUM: Int? = null

internal var PASSWORD_SHOWN: Boolean = false

internal var CONFIRM_SHOWN: Boolean = false

internal var NEED_TOKEN: Boolean? = true

internal var CHOSEN_PROGRESS: Int? = null

internal var IS_DONE: Boolean? = null

internal var TECH_IMAGE_URL: String? = null

internal var CHOSEN_VACANCY: Vacancy? = null

internal var TOPIC_NAME: String? = null

internal var CHOSEN_ROADMAP: String? = null

internal var TOPIC_ID: Int? = null

internal var CHOSEN_SECTION: String? = null

internal var LAST_PAGE: Int? = null

internal var CAROUSEL: List<Int> =
    listOf(R.mipmap.ic_kolesa_v, R.mipmap.ic_one, R.mipmap.ic_jusan_v)

internal var RECS_IMAGES: List<Int?> =
    listOf<Int?>(R.mipmap.ic_rec1, R.mipmap.ic_rec2, R.mipmap.ic_rec3, R.mipmap.ic_rec4)

internal var VACANCY_FILTERS: List<String?> = listOf<String?>(
    "All Type",
    "Internship",
    "Full Time",
    "Part Time",
    "Project Work",
    "Volunteering"
)

internal var ROADMAP_ID: Int? = 0

internal var TECHNOLOGY_ID: Int? = 0

internal var colorsBack: List<String?> =
    listOf<String?>("#E0FFFF", "#DBEFFF", "#FFFBEC", "#FEEEEE")

internal var colorProgress: List<String?> =
    listOf<String?>("#23B0B0", "#415EB6", "#FFB110", "#AC4040")

internal var colorForeground: List<String?> =
    listOf<String?>("")

internal var SHOW_ALL: Boolean = true