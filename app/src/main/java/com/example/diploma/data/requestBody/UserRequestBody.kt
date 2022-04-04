package com.example.diploma.data.requestBody

import java.io.Serializable

data class UserRequestBody(
    private var name: String,
    private var surname: String,
    private var email: String,
    private var phone: String,
    private var password: String
): Serializable