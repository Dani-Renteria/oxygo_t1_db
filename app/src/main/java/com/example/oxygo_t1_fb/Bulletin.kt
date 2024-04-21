package com.example.oxygo_t1_fb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.oxygo_t1_fb.databinding.BulletinBinding
import com.example.oxygo_t1_fb.databinding.FragmentHomeBinding


class Bulletin : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding : BulletinBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BulletinBinding.inflate(inflater, container, false)


        binding.btnToCreate.setOnClickListener{
            findNavController().navigate(R.id.action_bulletin_to_serviceForm)
        }
        return binding.root
    }


}