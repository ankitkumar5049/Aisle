package com.example.aisle.ui.notes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.aisle.R
import com.example.aisle.data.ResponseModel
import com.example.aisle.databinding.FragmentNotesBinding
import com.example.aisle.domain.retrofit.RetrofitClient
import com.example.aisle.ui.activity.otp.OtpActivity
import com.example.aisle.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NotesFragment : Fragment() {

    private var authToken: String? = Constants.token
    lateinit var notesViewModel: NotesViewModel
    lateinit var binding: FragmentNotesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notesViewModel = NotesViewModel()
        authToken?.let { notesViewModel.getDetails(it) }
        attachObservers()
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


    private fun attachObservers(){
        notesViewModel.notesStatus.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                // Handle successful login

                Toast.makeText(context,"Data received in the backend",Toast.LENGTH_SHORT).show()

            } else {
                // Handle login failure
                Toast.makeText(context,"Something went wrong here",Toast.LENGTH_SHORT).show()
            }
        }
    }


}