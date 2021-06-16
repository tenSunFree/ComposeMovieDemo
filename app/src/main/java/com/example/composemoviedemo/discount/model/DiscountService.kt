package com.example.composemoviedemo.discount.model

import retrofit2.http.GET

interface DiscountService {

    @GET("photos")
    suspend fun requestPhotos(): DiscountResponse
}