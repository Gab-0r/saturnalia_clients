package com.example.saturnalia_clients.ui.fragments.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.saturnalia_clients.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding
    private lateinit var loginviewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        loginviewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        loginviewModel.erroMsg.observe(viewLifecycleOwner){
            showErrorMsg(it)
        }

        loginviewModel.loginSuccess.observe(viewLifecycleOwner){
            goToHome()
        }

        with(loginBinding){
            loginButton.setOnClickListener {
                loginviewModel.checkFields(emailInputLayout.text.toString(), passwordEditTextTextPassword.text.toString())
            }
            loginBinding.signUpButton.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionNavigationLoginToNavigationSignUp())
            }
        }

        return loginBinding.root
    }

    fun goToHome(){
        findNavController().navigate(LoginFragmentDirections.actionNavigationLoginToNavigationProfile())
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
    private fun showErrorMsg(msg_: String?) {
        Toast.makeText(requireActivity(), msg_, Toast.LENGTH_LONG).show()
    }
}