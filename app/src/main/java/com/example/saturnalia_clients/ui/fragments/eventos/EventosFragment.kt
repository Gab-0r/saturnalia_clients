package com.example.saturnalia_clients.ui.fragments.eventos

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentEventosBinding
import java.text.SimpleDateFormat
import java.util.*

class EventosFragment : Fragment() {

    private lateinit var eventosBinding: FragmentEventosBinding
    private lateinit var eventosViewModel: EventosViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        eventosBinding = FragmentEventosBinding.inflate(inflater, container, false)
        eventosViewModel = ViewModelProvider(this)[EventosViewModel::class.java]

        val view = eventosBinding.root

        with(eventosBinding){
            addEventButton.setOnClickListener {
                goToCreateEvent()
            }
        }

        return view
    }

    fun goToCreateEvent(){
        findNavController().navigate(EventosFragmentDirections.actionNavigationEventsToNavigationCreateEvent())
    }
}