package com.example.oxygo_t1_fb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.oxygo_t1_fb.databinding.FragmentHomeBinding
import com.example.oxygo_t1_fb.databinding.FragmentSignupBinding
import com.example.oxygo_t1_fb.models.Users
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth


class SignupFragment : Fragment() {

    private var _binding : FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseref : DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        firebaseref = FirebaseDatabase.getInstance().getReference("users")
        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUp.setOnClickListener{
            saveData()
            findNavController().navigate(R.id.action_signupFragment_to_bulletin)
        }
        return binding.root
    }

    private fun saveData() {
        val userName = binding.editTextUserName.text.toString()
        val oxyid = binding.editTextOxyId.text.toString()
        val password = binding.editPassword.text.toString()

        if (userName.isEmpty()) binding.editTextUserName.error = "Please enter a username"
        if (password.isEmpty()) binding.editPassword.error = "Please enter a last name"
        if (oxyid.isEmpty()) binding.editTextOxyId.error = "Please enter your Oxy ID"

        val userID = firebaseref.push().key!! // creates unique id for each child automatically
        val users = Users(userID, userName, password, oxyid)

        firebaseref.child(userID).setValue(users)
            .addOnCompleteListener {
                Toast.makeText(context, "Succesfully registered! Thank you ${userName}", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(context, "error ${it.message}", Toast.LENGTH_SHORT).show()
            }


    }

}