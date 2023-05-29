package com.example.aisle.data

import com.google.gson.annotations.SerializedName

data class PhoneNumberResponse(
    @SerializedName("status") var status: Boolean? = null
)