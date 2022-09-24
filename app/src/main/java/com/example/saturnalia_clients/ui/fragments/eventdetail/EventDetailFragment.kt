package com.example.saturnalia_clients.ui.fragments.eventdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentEventDetailBinding
import com.example.saturnalia_clients.ui.model.Event
import com.squareup.picasso.Picasso

class EventDetailFragment : Fragment() {

    private lateinit var eventDetailBinding: FragmentEventDetailBinding
    private lateinit var eventDetailViewModel: EventDetailViewModel

    private val args: EventDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        eventDetailBinding = FragmentEventDetailBinding.inflate(inflater, container, false)
        eventDetailViewModel = ViewModelProvider(this)[EventDetailViewModel::class.java]

        val view = eventDetailBinding.root

        val event = args.event

        with(eventDetailBinding){
            eventNameDetail.text = event.name
            eventDescDetail.text = event.description
            eventDateDetail.text = event.date
            eventTimeDetail.text = event.time
            eventCoverDetail.text = event.cover
            if(event.urlPhoto != null)
                Picasso.get().load(event.urlPhoto).into(eventImageDetail)


            buttonEventEdit.setOnClickListener { goToEdit(event) }
        }

        return view
    }

    private fun goToEdit(event: Event) {
        findNavController().navigate(EventDetailFragmentDirections.actionNavigationEventDetailToNavigationEditEvents(event))
    }
}