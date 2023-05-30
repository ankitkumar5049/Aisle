package com.example.aisle.ui.notes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.aisle.R
import com.example.aisle.data.ResponseModel
import com.example.aisle.domain.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NotesFragment : Fragment() {

    private val apiService = RetrofitClient.apiService
    private var authToken: String? = "32c7794d2e6a1f7316ef35aa1eb34541"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        apiCall()
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    companion object {
        private const val ARG_DATA = "token"
        fun newInstance(data: String?): NotesFragment {

            val fragment = NotesFragment()
            val args = Bundle()
            args.putString(ARG_DATA, data)
            fragment.arguments = args
            return fragment
        }
    }

    private fun apiCall() {

        authToken?.let {
            apiService.getTestProfiles(it).enqueue(object : Callback<ResponseModel> {
                override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                    if (response.isSuccessful) {
                        val testProfilesResponse = response.body()
                        // Handle the successful response
                        Log.e("TAG", "onResponse: ${testProfilesResponse.toString()}", )
                    } else {
                        // Handle the error response
                        Toast.makeText(context,"Something went wrong here", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    // Handle the network or other failures
                    Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


}