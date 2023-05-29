package com.example.aisle.ui.activity.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.aisle.R
import com.example.aisle.ui.activity.data.LoginRepository
import com.example.aisle.ui.activity.data.Result


class LoginViewModel() : ViewModel() {

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult




}