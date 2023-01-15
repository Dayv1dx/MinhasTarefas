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
import com.example.minhastarefas.databinding.FragmentRecuperarContaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RecuperarContaFragment : Fragment() {

    private var _binding: FragmentRecuperarContaBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecuperarContaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        initClick()
    }

    private fun RecuperarUsuario(email: String){
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Pronto, uma mensagem foi enviada para o seu e-mail", Toast.LENGTH_SHORT).show()
                }
                else {

                }
                binding.progressBar.isVisible = false
            }
    }
    private fun validarCampos(){

        val email = binding.edtEmail.text.toString().trim()

        if (email.isNotEmpty()){

            binding.progressBar.isVisible = true

            RecuperarUsuario(email)

        } else {
            Toast.makeText(requireContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun initClick(){
        binding.btRecuperar.setOnClickListener{ validarCampos() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}