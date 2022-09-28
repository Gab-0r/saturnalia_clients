package com.example.saturnalia_clients.ui.fragments.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentLoginBinding
import com.example.saturnalia_clients.databinding.FragmentSignUpBinding
import com.example.saturnalia_clients.ui.fragments.login.LoginViewModel

class SignUpFragment : Fragment() {

    private lateinit var signUpBinding: FragmentSignUpBinding
    private lateinit var signUpviewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        signUpviewModel = ViewModelProvider(this)[SignUpViewModel::class.java]


        signUpviewModel.erroMsg.observe(viewLifecycleOwner){
            showErrorMsg(it)
        }

        signUpviewModel.registerSuccess.observe(viewLifecycleOwner){
            goToLogin()
        }

        with(signUpBinding){
            registerButton.setOnClickListener {
                signUpviewModel.checkFields(nameDisco.text.toString(), emailTextEmailAddress.text.toString(), discoAddress.text.toString(),
                    discoPhone.text.toString(), passwordText.text.toString(), confirmPassword.text.toString()
                )
            }
            buttonLogin.setOnClickListener {
                goToLogin()
            }
        }

        return signUpBinding.root
    }

    private fun showErrorMsg(msg_: String?) {
        Toast.makeText(requireActivity(), msg_, Toast.LENGTH_LONG).show()
    }

    fun goToLogin(){
        findNavController().navigate(SignUpFragmentDirections.actionNavigationSignUpToNavigationLogin())
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}