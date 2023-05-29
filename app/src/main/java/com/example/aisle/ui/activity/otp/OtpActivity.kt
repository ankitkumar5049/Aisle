package com.example.aisle.ui.activity.otp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.aisle.MainActivity
import com.example.aisle.R
import com.example.aisle.databinding.ActivityOtpBinding

class OtpActivity : AppCompatActivity() {
    lateinit var binding: ActivityOtpBinding
    private var phoneNumber: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        phoneNumber = intent.getStringExtra("mobileNo")
        Log.e("TAG", "onCreate: $phoneNumber", )
        initClicks()
        setContentView(binding.root)
    }

    private fun initClicks() {

        binding.tvPhoneNumber.text = "+91 $phoneNumber"
            binding.btnContinueVerify.setOnClickListener {
                Log.e("TAG", "initClicks: button clicked", null)
                if (validate()){
                    val intent =
                        Intent(this@OtpActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(applicationContext,"empty otp", Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun validate(): Boolean {
        return binding.etOTP?.text?.trim()?.isEmpty() != true
    }
}