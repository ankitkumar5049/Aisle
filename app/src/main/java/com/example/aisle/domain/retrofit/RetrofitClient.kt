package com.example.aisle.domain.retrofit

import com.example.aisle.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL) 
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}
