package com.example.minhastarefas.helper

import com.example.minhastarefas.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FirebaseHelper {

    companion object {

        fun getDatabase() = FirebaseDatabase.getInstance().reference

        fun getAuth() = FirebaseAuth.getInstance()

        fun getIdUser() = getAuth().uid

        fun isAutenticated() = getAuth().currentUser != null

        fun validarErros(error: String) : Int{
            return when {
                error.contains("There is no user record") -> R.string.account_not_registered
                error.contains("The email address is badly") -> R.string.invalid_email_register
                error.contains("The password is invalid") -> R.string.invalid_senha_register
                error.contains("The email address is already in use") -> R.string.email_in_use
                error.contains("Password should be at least 6") -> R.string.senha6caracteres
                else -> R.string.erro_generico
            }

        }
    }

}