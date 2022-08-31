package com.example.saturnalia_clients.ui.fragments.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentLoginBinding
import com.example.saturnalia_clients.databinding.FragmentSignUpBinding
import com.example.saturnalia_clients.ui.fragments.login.LoginViewModel

class SignUpFragment : Fragment() {

    private lateinit var signUpBinding: FragmentSignUpBinding
    private lateinit var signupviewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        signupviewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        with(signUpBinding){
            registerButton.setOnClickListener {
            }
        }

        return signUpBinding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}