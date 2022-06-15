package com.example.diploma.data.model


data class User(
    var id: Int,
    var first_name: String?,
    var last_name: String?,
    var email: String,
    var phone: String,
    var skills: List<UserSkills>?,
    var user_studies: List<UserProgress>?
) {
    data class UserSkills(
        var id: Int,
        var name: String
    )

    data class UserProgress(
        var tech_name: String?,
        var progress: Float
    )
}
