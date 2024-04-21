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
import android.content.Intent


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
        }
        return binding.root
    }

    private fun saveData() {
        val email = binding.enterEmail.text.toString()
        val oxyid = binding.enterOxyId.text.toString()
        val password = binding.enterPassword.text.toString()

        /*
        if (email.isEmpty()) binding.enterEmail.error = "Please enter a username"
        if (password.isEmpty()) binding.enterPassword.error = "Please enter a last name"
        if (oxyid.isEmpty()) binding.enterOxyId.error = "Please enter your Oxy ID"
        */

        if (email.isNotEmpty() && oxyid.isNotEmpty() && password.isNotEmpty()){
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    findNavController().navigate(R.id.action_signupFragment_to_homeFragment)
                } else {
                    Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(context, "Blank fields are not allowed.", Toast.LENGTH_SHORT).show()
        }


        val userID = firebaseref.push().key!! // creates unique id for each child automatically
        val users = Users(userID, email, password, oxyid)

        firebaseref.child(userID).setValue(users)
            .addOnCompleteListener {
                Toast.makeText(context, "Succesfully registered!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(context, "error ${it.message}", Toast.LENGTH_SHORT).show()
            }


    }

}