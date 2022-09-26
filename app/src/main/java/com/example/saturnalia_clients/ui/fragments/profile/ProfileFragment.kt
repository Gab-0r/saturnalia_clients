package com.example.saturnalia_clients.ui.fragments.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.saturnalia_clients.databinding.FragmentProfileBinding
import com.example.saturnalia_clients.ui.fragments.editprofile.EditProfileFragmentArgs
import com.example.saturnalia_clients.ui.model.Disco

class ProfileFragment : Fragment() {

    private lateinit var profileBinding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    private var disco: Disco = Disco()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        val view = profileBinding.root



        profileViewModel.loadProfile()

        profileViewModel.disco.observe(viewLifecycleOwner){
            disco = it
            drawProfile()
        }

        with(profileBinding){
            productsButton.setOnClickListener { goToProducts() }
            eventsButton.setOnClickListener { goToEvents() }

            editProfileButton.setOnClickListener { goToEdit(disco) }
        }

        return view

    }

    private fun goToEdit(disco: Disco) {
        findNavController().navigate(ProfileFragmentDirections.actionNavigationProfileToNavigationEditProfile(disco))
    }

    private fun goToEvents() {
        findNavController().navigate(ProfileFragmentDirections.actionNavigationProfileToNavigationEvents())
    }

    private fun goToProducts() {
        findNavController().navigate(ProfileFragmentDirections.actionNavigationProfileToNavigationCarta())
    }

    private fun drawProfile() {
        with(profileBinding){
            textViewProfileName.text = disco.name
            aboutUsContent.text = disco.about
            phoneNumberContent.text = disco.phone
            contactEmailContent.text = disco.email
            contactAdressContent.text = disco.address
        }
    }
}