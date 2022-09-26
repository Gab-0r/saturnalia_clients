package com.example.saturnalia_clients.ui.fragments.editprofile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {

    private lateinit var editProfileBinding: FragmentEditProfileBinding
    private lateinit var editProfileViewModel: EditProfileViewModel

    private val args: EditProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        editProfileBinding = FragmentEditProfileBinding.inflate(inflater, container, false)
        editProfileViewModel = ViewModelProvider(this)[EditProfileViewModel::class.java]

        val view = editProfileBinding.root

        val disco = args.discoToEdit

        editProfileViewModel.editProfileSuccess.observe(viewLifecycleOwner){
            goToProfile()
        }

        with(editProfileBinding){
            editTextName.setText(disco.name)
            editTextAboutUs.setText(disco.about)
            editTextPhone.setText(disco.phone)
            editTextEmail.setText(disco.email)
            editTextAddress.setText(disco.address)

            confirmEditProfile.setOnClickListener {
                editProfileViewModel.checkFields(disco.id.toString(), editTextName.text.toString(), editTextAboutUs.text.toString(), editTextPhone.text.toString(),
                    editTextEmail.text.toString(), editTextAddress.text.toString()
                )
            }
        }


        return view

    }

    private fun goToProfile() {
        findNavController().navigate(EditProfileFragmentDirections.actionNavigationEditProfileToNavigationProfile())
    }

}