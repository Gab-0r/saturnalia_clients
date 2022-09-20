package com.example.saturnalia_clients.ui.fragments.eventos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentEventosBinding

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


        return view

    }
}