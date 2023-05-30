package com.example.aisle.ui.activity.otp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.aisle.MainActivity
import com.example.aisle.data.OtpRequest
import com.example.aisle.data.OtpResponse
import com.example.aisle.databinding.ActivityOtpBinding
import com.example.aisle.domain.retrofit.RetrofitClient.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtpActivity : AppCompatActivity() {
    lateinit var binding: ActivityOtpBinding
    private var phoneNumber: String? = null
    private var timer: CountDownTimer? = null
    private val waitingTime: Long = 1 * 60 * 1000
    lateinit var otpViewModel: OtpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        phoneNumber = intent.getStringExtra("mobileNo")
        Log.e("TAG", "onCreate: $phoneNumber")
        otpViewModel = OtpViewModel()
        initClicks()
        attachObservers()
        startTimer()
        setContentView(binding.root)
    }

    private fun initClicks() {

        binding.tvPhoneNumber.text = "+91 $phoneNumber"
        binding.btnContinueVerify.setOnClickListener {
            if (validate()) {
                binding.loading.visibility = View.VISIBLE
                otpViewModel.verify("+91$phoneNumber", binding.etOTP.text.toString())

            } else {
                Toast.makeText(applicationContext, "empty otp", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun attachObservers() {
        otpViewModel.otpStatus.observe(this@OtpActivity) {
            binding.loading.visibility = View.GONE
            if (it) {
                // Handle successful login
                val intent = Intent(this@OtpActivity, MainActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                // Handle login failure
                Toast.makeText(applicationContext, "Something went wrong, try again!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun validate(): Boolean {
        return binding.etOTP?.text?.trim()?.isEmpty() != true
    }


    private fun startTimer() {
        timer = object : CountDownTimer(waitingTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Update UI with the remaining time if needed
                var sec: String = (millisUntilFinished / (1000)).toString()
                var min: String = (sec.toInt() / 60).toString()
                sec = ((millisUntilFinished / (1000)) % 60).toString()

                if (min.toInt() < 10) {
                    min = "0$min"
                }
                if (sec.toInt() < 10) {
                    sec = "0$sec"
                }

                binding.tvTimer.text = "$min : $sec"
            }

            override fun onFinish() {
                // Enable the resend OTP button here
                binding.tvTimer.visibility = View.GONE
            }
        }
        timer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }

    private fun restartTimer() {
        timer?.cancel()
        startTimer()
    }
}