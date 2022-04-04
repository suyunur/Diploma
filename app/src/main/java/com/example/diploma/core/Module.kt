package com.example.diploma.core

import android.content.Context
import android.content.SharedPreferences
import com.example.diploma.R
import com.example.diploma.data.DiplomaApi
import com.example.diploma.ui.login.AuthViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val module = module {
    single { provideRetrofit(get()) }
    single { provideApi(get()) }
    single { provideOKHttp(get(), androidContext()) }
    single { provideSharedPreferences(androidContext()) }
}

val vmModule = module {
    viewModel {
        AuthViewModel(get())
    }
}

private fun provideRetrofit(okHttpClient: OkHttpClient) {
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://it-bilim.herokuapp.com/api")
        .client(okHttpClient)
        .build()
}

private fun provideSharedPreferences(context: Context): SharedPreferences? = context.getSharedPreferences("pref", Context.MODE_PRIVATE)

private fun provideApi(retrofit: Retrofit): DiplomaApi =
    retrofit.create(DiplomaApi::class.java)

private fun provideOKHttp(sharedPreferences: SharedPreferences, context: Context): OkHttpClient{

    return OkHttpClient()
        .newBuilder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor { chain ->
            val request = chain
                .request()
                .newBuilder()
                .addHeader(
                    "authToken",
                    "bearer ${sharedPreferences.getString(context.getString(R.string.auth_token), null)}"
                )
                .build()
            chain.proceed(request)
        }
        .build()
}