package com.example.aisle.ui.activity.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.aisle.R
import com.example.aisle.databinding.ActivityLoginBinding
import com.example.aisle.ui.activity.otp.OtpActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClicks()

//        val username = binding.username
//        val password = binding.password
//        val login = binding.login
//        val loading = binding.loading
//
//        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
//            .get(LoginViewModel::class.java)
//
//        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
//            val loginState = it ?: return@Observer
//
//            // disable login button unless both username / password is valid
//            login?.isEnabled = loginState.isDataValid
//
//            if (loginState.usernameError != null) {
//                if (username != null) {
//                    username.error = getString(loginState.usernameError)
//                }
//            }
//            if (loginState.passwordError != null) {
//                if (password != null) {
//                    password.error = getString(loginState.passwordError)
//                }
//            }
//        })
//
//        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
//            val loginResult = it ?: return@Observer
//
//            loading.visibility = View.GONE
//            if (loginResult.error != null) {
//                showLoginFailed(loginResult.error)
//            }
//            if (loginResult.success != null) {
//                updateUiWithUser(loginResult.success)
//            }
//            setResult(Activity.RESULT_OK)
//
//            //Complete and destroy login activity once successful
//            finish()
//        })
//
//        username?.afterTextChanged {
//            if (username != null) {
//                if (password != null) {
//                    loginViewModel.loginDataChanged(
//                        username.text.toString(),
//                        password.text.toString()
//                    )
//                }
//            }
//        }
//
//        password?.apply {
//            afterTextChanged {
//                loginViewModel.loginDataChanged(
//                    username?.text.toString(),
//                    password?.text.toString()
//                )
//            }
//
//            setOnEditorActionListener { _, actionId, _ ->
//                when (actionId) {
//                    EditorInfo.IME_ACTION_DONE ->
//                        loginViewModel.login(
//                            username?.text.toString(),
//                            password?.text.toString()
//                        )
//                }
//                false
//            }
//
//            login?.setOnClickListener {
//                loading.visibility = View.VISIBLE
//                loginViewModel.login(username?.text.toString(), password.text.toString())
//            }
//        }
    }

    private fun initClicks() {

        binding.apply {
            binding.btnContinue?.setOnClickListener {
                if (validate()){
                    val intent =
                        Intent(this@LoginActivity, OtpActivity::class.java)
                    intent.putExtra("mobileNo", binding.etPhoneNumber?.text.toString())
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(applicationContext,"empty number",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validate(): Boolean {
        return binding.etPhoneNumber?.text?.trim()?.isEmpty() != true
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}