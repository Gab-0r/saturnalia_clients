package com.example.saturnalia_clients.ui.fragments.eventdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentEventDetailBinding

class EventDetailFragment : Fragment() {

    private lateinit var eventDetailBinding: FragmentEventDetailBinding
    private lateinit var eventDetailViewModel: EventDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        eventDetailBinding = FragmentEventDetailBinding.inflate(inflater, container, false)
        eventDetailViewModel = ViewModelProvider(this)[EventDetailViewModel::class.java]

        val view = eventDetailBinding.root


        return view
    }
}