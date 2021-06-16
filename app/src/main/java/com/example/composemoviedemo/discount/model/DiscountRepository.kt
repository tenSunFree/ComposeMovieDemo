package com.example.composemoviedemo.discount.model

import com.example.composemoviedemo.common.model.Constants
import kotlin.jvm.Volatile
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class DiscountRepository private constructor() {

    private val service: DiscountService

    companion object {

        @Volatile
        private var instance: DiscountRepository? = null

        fun getInstance(): DiscountRepository? {
            if (instance == null) {
                synchronized(DiscountRepository::class.java) {
                    if (instance == null) instance = DiscountRepository()
                }
            }
            return instance
        }
    }

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        service = retrofit.create(DiscountService::class.java)
    }

    suspend fun requestPhotos(): DiscountResponse {
        return service.requestPhotos()
    }
}