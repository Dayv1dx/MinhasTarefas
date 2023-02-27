package com.example.minhastarefas.ui

import android.app.Fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.minhastarefas.R
import com.example.minhastarefas.databinding.FragmentDoingBinding
import com.example.minhastarefas.databinding.FragmentDoneBinding
import com.example.minhastarefas.databinding.FragmentHomeBinding

@Suppress("DEPRECATION")
class DoingFragment : Fragment() {

    private var _binding: FragmentDoingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDoingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}