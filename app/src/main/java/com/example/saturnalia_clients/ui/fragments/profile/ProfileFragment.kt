package com.example.saturnalia_clients.ui.fragments.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.saturnalia_clients.databinding.FragmentProfileBinding
import com.example.saturnalia_clients.ui.fragments.editprofile.EditProfileFragmentArgs
import com.example.saturnalia_clients.ui.fragments.eventos.EventAdapter
import com.example.saturnalia_clients.ui.model.Disco
import com.example.saturnalia_clients.ui.model.Event

class ProfileFragment : Fragment() {

    private lateinit var profileBinding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var eventAdapter: EventAdapter
    private var eventList: ArrayList<Event> = ArrayList()

    private var disco: Disco = Disco()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        val view = profileBinding.root

        profileViewModel.loadEvents()

        profileViewModel.loadProfile()

        profileViewModel.disco.observe(viewLifecycleOwner){
            disco = it
            drawProfile()
        }

        profileViewModel.eventsList.observe(viewLifecycleOwner){list ->
            eventAdapter.appendItems(list)
        }

        eventAdapter = EventAdapter(eventList,
                onItemClicked = {onEventItemClicked(it)},
                onLongItemClicked = {onItemLongClicked(it)}
        )

        profileBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ProfileFragment.requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = eventAdapter
            setHasFixedSize(false)
            val snapHelper: SnapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }

        with(profileBinding){
            productsButton.setOnClickListener { goToProducts() }
            editProfileButton.setOnClickListener { goToEdit(disco) }
            verTodosView.setOnClickListener { goToEvents() }
        }

        return view

    }

    private fun onItemLongClicked(it: Event) {
    }

    private fun onEventItemClicked(it: Event) {
        findNavController().navigate(ProfileFragmentDirections.actionNavigationProfileToNavigationEventDetail(it))
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