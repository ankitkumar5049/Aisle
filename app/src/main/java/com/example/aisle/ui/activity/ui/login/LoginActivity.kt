package com.example.aisle.ui.activity.ui.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aisle.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = LoginViewModel()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.white)
        }

        initClicks()
        attachObservers()

    }

    private fun initClicks() {

        binding.apply {
            binding.btnContinue?.setOnClickListener {
                if (validate()){
                    binding.loading.visibility = View.VISIBLE
                    var phone = binding.etPhoneNumber?.text.toString()
                    loginViewModel.login("+91$phone")
                }
                else{
                    Toast.makeText(applicationContext,"Please enter valid number",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun attachObservers(){
        loginViewModel.loginStatus.observe(this@LoginActivity) { isLoggedIn ->
            binding.loading.visibility = View.GONE
            if (isLoggedIn) {
                // Handle successful login
                val intent = Intent(this@LoginActivity, OtpActivity::class.java)
                intent.putExtra("mobileNo", binding.etPhoneNumber?.text.toString())
                startActivity(intent)
                finish()

            } else {
                // Handle login failure
                Toast.makeText(applicationContext,"Something went wrong, try again",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validate(): Boolean {
        if(binding.etPhoneNumber?.text?.length!! <10)return false
        return binding.etPhoneNumber?.text?.trim()?.isEmpty() != true

    }

}
