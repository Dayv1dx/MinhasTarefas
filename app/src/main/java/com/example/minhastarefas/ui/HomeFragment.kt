package com.example.minhastarefas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.minhastarefas.R
import com.example.minhastarefas.databinding.FragmentHomeBinding
import com.example.minhastarefas.databinding.FragmentLoginBinding
import com.example.minhastarefas.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        configTabLayout()

        initClicks()
    }

    private fun configTabLayout() {
        val adapter = ViewPagerAdapter(requireActivity())

        binding.viewPager.setAdapter(adapter)

        adapter.addFragment(ToDoFragment(), "A Fazer")
        adapter.addFragment(DoingFragment(), "Fazendo")
        adapter.addFragment(DoneFragment(), "Feitas")

        binding.viewPager.setOffscreenPageLimit(adapter.itemCount)

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab: TabLayout.Tab, position: Int ->
            tab.text = adapter.getTitle(position)
        }.attach()
    }

    private fun logoutApp(){
        auth.signOut()
        findNavController().navigate(R.id.action_homeFragment_to_navigation)
    }

    private fun initClicks(){
        binding.ibLogout.setOnClickListener{ logoutApp() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}