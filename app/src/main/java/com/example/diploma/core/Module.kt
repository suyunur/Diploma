package com.example.diploma.core

import android.content.Context
import android.content.SharedPreferences
import com.example.diploma.R
import com.example.diploma.data.DiplomaApi
import com.example.diploma.data.repo.AuthRepository
import com.example.diploma.data.repo.DashboardRepository
import com.example.diploma.ui.dashboard.home.HomeViewModel
import com.example.diploma.ui.dashboard.vacancies.VacanciesViewModel
import com.example.diploma.ui.login.AuthViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@DelicateCoroutinesApi
val module = module {
    single { provideRetrofit(get()) }
    single { provideApi(get()) }
    single { provideOKHttp(get(), androidContext()) }
    single { provideSharedPreferences(androidContext()) }
    single { provideAuthRepo(get(), get(), androidContext()) }
    single { provideDashRepo(get()) }
}

@DelicateCoroutinesApi
val vmModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { VacanciesViewModel(get()) }
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://demo-it-bilim.herokuapp.com/api/")
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

@DelicateCoroutinesApi
fun provideAuthRepo(
    api: DiplomaApi,
    sharedPreferences: SharedPreferences,
    context: Context
): AuthRepository =
    AuthRepository(api, sharedPreferences, context)

fun provideDashRepo(
    api: DiplomaApi
): DashboardRepository =
    DashboardRepository(api)