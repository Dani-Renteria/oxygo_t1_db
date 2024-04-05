package com.example.oxygo_t1_fb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.oxygo_t1_fb.databinding.FragmentHomeBinding
import com.example.oxygo_t1_fb.databinding.FragmentSignupBinding
import com.example.oxygo_t1_fb.models.Users
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignupFragment : Fragment() {

    private var _binding : FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseref : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        firebaseref = FirebaseDatabase.getInstance().getReference("users")

        binding.signUp.setOnClickListener{
            saveData()
        }



        return binding.root
    }

    private fun saveData() {
        val firstName = binding.editTextFirstName.text.toString()
        val lastName = binding.editTextLastName.text.toString()
        val oxyid = binding.editTextOxyId.text.toString()
        val phone = binding.editTextPhone.text.toString()

        if (firstName.isEmpty()) binding.editTextFirstName.error = "Please enter a first name"
        if (lastName.isEmpty()) binding.editTextFirstName.error = "Please enter a last name"
        if (oxyid.isEmpty()) binding.editTextFirstName.error = "Please enter your Oxy ID"
        if (phone.isEmpty()) binding.editTextFirstName.error = "Please enter a phone number"

        val userID = firebaseref.push().key!! // creates unique id for each child automatically
        val users = Users(userID, firstName, lastName, oxyid, phone)

        firebaseref.child(userID).setValue(users)
            .addOnCompleteListener {
                Toast.makeText(context, "Succesfully registered! Thank you ${firstName}", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(context, "error ${it.message}", Toast.LENGTH_SHORT).show()
            }


    }

}