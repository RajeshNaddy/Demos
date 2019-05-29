package com.example.myapplication.MVVMPattern_LoginScreen.LoginModule.factory

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {

    const val RETROFIT_ERROR = 3000
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private fun getRetroInstance(url: String): Retrofit {
        return getBuilder(url)
            .client(getOkHttpClient())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                okHttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    private fun getBuilder(url: String): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(url)
    }

    private fun okHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
           // Timber.d(message)
            Log.d("API RESPONSE", message)
        })
    }

    fun <T> getService(url: String, clazz: Class<T>): T {
        return getRetroInstance(url).create(clazz)
    }

    fun <T> getMoshiAdapter(clazz: Class<T>): JsonAdapter<T> {
        val type = Types.newParameterizedType(List::class.java, clazz)
        return moshi.adapter(type)
    }
}