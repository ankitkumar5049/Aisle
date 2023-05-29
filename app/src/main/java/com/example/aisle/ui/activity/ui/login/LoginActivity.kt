package com.example.aisle.ui.activity.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aisle.data.PhoneNumberRequest
import com.example.aisle.data.PhoneNumberResponse
import com.example.aisle.databinding.ActivityLoginBinding
import com.example.aisle.domain.retrofit.RetrofitClient
import com.example.aisle.ui.activity.otp.OtpActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    private val apiService = RetrofitClient.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClicks()

    }

    private fun initClicks() {

        binding.apply {
            binding.btnContinue?.setOnClickListener {
                if (validate()){
                    binding.loading.visibility = View.VISIBLE

                    apiCall()

                }
                else{
                    Toast.makeText(applicationContext,"empty number",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun apiCall() {
        val hashMap = HashMap<String, String>()
        hashMap.set("number", "+91${binding.etPhoneNumber?.text}")

//        val phoneNumberRequest = PhoneNumberRequest(hashMap)
        val phoneNumberRequest = PhoneNumberRequest(number = "+91${binding.etPhoneNumber?.text.toString()}")
        apiService.phoneNumberLogin(phoneNumberRequest).
        enqueue(object : Callback<PhoneNumberResponse> {
            override fun onResponse(
                call: Call<PhoneNumberResponse>,
                response: Response<PhoneNumberResponse>) {

                binding.loading.visibility = View.VISIBLE
                if (response.code()==200) {
                    val phoneNumberResponse = response.body()
                    // Handle the successful response
                    if (phoneNumberResponse?.status == true) {
                        val intent =
                            Intent(this@LoginActivity, OtpActivity::class.java)
                        intent.putExtra("mobileNo", binding.etPhoneNumber?.text.toString())
                        startActivity(intent)
                        finish()

                    }
                    else{
                        Toast.makeText(applicationContext,"Something went wrong here",Toast.LENGTH_SHORT).show()
                    }

                } else {
                    // Handle the error response
                    Toast.makeText(applicationContext,"Something went wrong",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PhoneNumberResponse>, t: Throwable) {
                // Handle the network or other failures
                Toast.makeText(applicationContext,"Api Failure!",Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun validate(): Boolean {
        return binding.etPhoneNumber?.text?.trim()?.isEmpty() != true
    }

}
