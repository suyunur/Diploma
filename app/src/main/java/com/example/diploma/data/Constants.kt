package com.example.diploma.data

import com.example.diploma.R
import com.example.diploma.data.model.Vacancy

internal var IS_DONE: Boolean? = null

internal var TECH_IMAGE_URL: String? = null

internal var CHOSEN_VACANCY: Vacancy? = null

internal var TOPIC_NAME: String? = null

internal var CHOSEN_ROADMAP: String? = null

internal var TOPIC_ID: Int? = null

internal var CHOSEN_SECTION: String? = null

internal var CHOSEN_TOPIC: String? = null

internal var LAST_PAGE: Int? = null

internal var PROGRESS: String? = null

internal var SKILL: String? = null

internal var REQS: List<String>? =
    listOf("Experience with GIT", "Fluent English", "Working with team")

internal var SKILLS: String? = null

internal var CAROUSEL: List<Int> =
    listOf(R.mipmap.ic_kolesa_v, R.mipmap.ic_one, R.mipmap.ic_jusan_v)

internal var RECS_IMAGES: List<Int?> =
    listOf<Int?>(R.mipmap.ic_rec1, R.mipmap.ic_rec2, R.mipmap.ic_rec3, R.mipmap.ic_rec4)

internal var VACANCY_IMAGES: List<Int?> =
    listOf<Int?>(R.mipmap.ic_kolesa_v, R.mipmap.ic_trt_v, R.mipmap.ic_jusan_v)

internal var IMAGE_INDEX: Int? = null

internal var VACANCY_STATIC: List<Vacancy?>? = null

internal var COMPANY_NAMES: List<String?> =
    listOf<String?>("Kolesa Group", "Top Remote Talent", "Jusan Bank")

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