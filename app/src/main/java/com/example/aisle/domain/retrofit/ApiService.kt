package com.example.aisle.domain.retrofit

import com.example.aisle.data.OtpRequest
import com.example.aisle.data.OtpResponse
import com.example.aisle.data.PhoneNumberRequest
import com.example.aisle.data.PhoneNumberResponse
import com.example.aisle.data.TestProfilesResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("users/phone_number_login")
    fun phoneNumberLogin(@Body request: PhoneNumberRequest): Call<
            PhoneNumberResponse>

    @POST("users/verify_otp")
    fun verifyOtp(@Body request: OtpRequest): Call<OtpResponse>

    @GET("users/test_profile_list")
    fun getTestProfiles(@Header("Authorization") authToken: String): Call<TestProfilesResponse>
}
