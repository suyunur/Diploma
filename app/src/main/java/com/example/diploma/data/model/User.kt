package com.example.diploma.data.model


data class User (
    var id: String,
    var name: String,
    var surname: String,
    var email: String,
    var phone: String,
    var progress: List<UserProgress>?,
    var skill: List<UserSkills>?
) {
    data class UserSkills (
        var id: String,
        var name: String
    )

    data class UserProgress (
        var id: String,
        var courseName: String,
        var progress: String
    )
}
