package com.example.aisle.ui.activity.otp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aisle.data.OtpRequest
import com.example.aisle.data.OtpResponse
import com.example.aisle.domain.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtpViewModel : ViewModel() {

    private val apiService = RetrofitClient.apiService
    private val _otpStatus = MutableLiveData<Boolean>()

    val otpStatus: LiveData<Boolean>
        get() = _otpStatus

    fun verify(phoneNumber: String, otp : String){
        val otpRequest = OtpRequest(number = phoneNumber, otp = otp)
        apiService.verifyOtp(otpRequest).enqueue(object : Callback<OtpResponse> {
            override fun onResponse(call: Call<OtpResponse>, response: Response<OtpResponse>) {
                if (response.isSuccessful) {
                    val phoneNumberResponse = response.body()
                    Log.e("TAG", "onResponse: ${phoneNumberResponse.toString()}", )
                    _otpStatus.value = response.body()?.token != null

                } else {
                    _otpStatus.value = false
                }
            }

            override fun onFailure(call: Call<OtpResponse>, t: Throwable) {
                // Handle the network or other failures
                _otpStatus.value = false
            }
        })
    }


}