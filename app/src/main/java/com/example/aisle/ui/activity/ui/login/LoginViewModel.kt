package com.example.aisle.ui.activity.ui.login


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aisle.data.PhoneNumberRequest
import com.example.aisle.data.PhoneNumberResponse
import com.example.aisle.domain.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val apiService = RetrofitClient.apiService
    private val _loginStatus = MutableLiveData<Boolean>()

    val loginStatus: LiveData<Boolean>
        get() = _loginStatus

    fun login(phoneNumber: String) {
        val phoneNumberRequest = PhoneNumberRequest(number = phoneNumber)
        apiService.phoneNumberLogin(phoneNumberRequest)
            .enqueue(object : Callback<PhoneNumberResponse> {
                override fun onResponse(
                    call: Call<PhoneNumberResponse>,
                    response: Response<PhoneNumberResponse>
                ) {
                    if (response.isSuccessful) {
                        val phoneNumberResponse = response.body()
                        Log.e("TAG", "onResponse: ${phoneNumberResponse.toString()}")
                        _loginStatus.value = phoneNumberResponse?.status == true
                    } else {
                        _loginStatus.value = false
                    }
                }

                override fun onFailure(call: Call<PhoneNumberResponse>, t: Throwable) {
                    _loginStatus.value = false
                }
            })
    }
}
