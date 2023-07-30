package com.leafwise.test.data.network

import com.leafwise.test.data.network.ApiConstants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient {

    fun provideRetrofit(
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}