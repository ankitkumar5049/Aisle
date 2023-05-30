package com.example.aisle.ui.notes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aisle.data.ResponseModel
import com.example.aisle.domain.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotesViewModel : ViewModel() {

    private val apiService = RetrofitClient.apiService
    private val _notesStatus = MutableLiveData<Boolean>()

    private val _getDropDownData = MutableLiveData<ResponseModel>()
    val getDropDownData : LiveData<ResponseModel> = _getDropDownData

    val notesStatus: LiveData<Boolean>
        get() = _notesStatus

    fun getDetails(token : String){
        apiService.getTestProfiles(token).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful) {
                    val testProfilesResponse = response.body()
                    // Handle the successful response
                    Log.e("TAG", "onResponse: ${testProfilesResponse.toString()}", )
                    if(testProfilesResponse!=null){
                        _getDropDownData.postValue(response.body())
                        _notesStatus.value = true
                    }
                    else{
                        _notesStatus.value = false
                    }

                } else {
                    // Handle the error response
                    _notesStatus.value = false
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                // Handle the network or other failures
                _notesStatus.value = false
            }
        })
    }
}