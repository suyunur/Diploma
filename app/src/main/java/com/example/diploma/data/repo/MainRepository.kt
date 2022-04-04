package com.example.diploma.data.repo

import com.example.diploma.data.responseBody.MainResponse
import retrofit2.Response
import java.lang.Exception


open class MainRepository {

    suspend fun <T : Any?> safeApiCall(responseFunction: suspend () -> Response<MainResponse<T?>>): Result<T?> {
        return try {
            val response = responseFunction.invoke()
            Result.success(response.body()?.data)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}