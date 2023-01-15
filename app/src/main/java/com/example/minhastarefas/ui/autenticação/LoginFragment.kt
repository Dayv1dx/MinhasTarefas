package com.example.minhastarefas.ui.autenticação

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.minhastarefas.R
import com.example.minhastarefas.databinding.FragmentLoginBinding
import com.example.minhastarefas.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        initClick()
    }
    private fun LoginUsuario(email: String, senha: String){
        auth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    binding.progressBar.isVisible = false

                }
            }
    }

    private fun validarCampos(){

        val email = binding.edtEmail.text.toString().trim()
        val senha = binding.edtSenha.text.toString().trim()

        if (email.isNotEmpty() || senha.isNotEmpty()){

            binding.progressBar.isVisible = true

            LoginUsuario(email, senha)

        } else {
            Toast.makeText(requireContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun initClick(){

        binding.btLogin.setOnClickListener{ validarCampos() }

        binding.btCriarConta.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registroFragment)
        }

        binding.btRecuperar.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recuperarContaFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}