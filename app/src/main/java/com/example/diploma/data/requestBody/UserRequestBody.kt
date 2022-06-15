package com.example.diploma.data.requestBody

import java.io.Serializable

data class UserRequestBody(
    var first_name: String,
    var last_name: String,
    var email: String,
    var password: String
) : Serializable