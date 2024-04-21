package com.example.oxygo_t1_fb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.oxygo_t1_fb.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        firebaseAuth = FirebaseAuth.getInstance()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnToSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_signupFragment)
        }

        binding.btnLogin.setOnClickListener {
            signIn()
        }
        return binding.root
    }


    private fun signIn() {
        val username = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()

        /*
        if (email.isEmpty()) binding.enterEmail.error = "Please enter a username"
        if (password.isEmpty()) binding.enterPassword.error = "Please enter a last name"
        if (oxyid.isEmpty()) binding.enterOxyId.error = "Please enter your Oxy ID"
        */

        if (username.isNotEmpty() && password.isNotEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    findNavController().navigate(R.id.action_homeFragment_to_bulletin)
                } else {
                    Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(context, "Blank fields are not allowed.", Toast.LENGTH_SHORT).show()
        }

    }
}