package com.example.saturnalia_clients.ui.fragments.editevents

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentEditEventsBinding

class EditEventsFragment : Fragment() {

    private lateinit var editEventsBinding: FragmentEditEventsBinding
    private lateinit var editEventsViewModel: EditEventsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        editEventsBinding = FragmentEditEventsBinding.inflate(inflater, container, false)
        editEventsViewModel = ViewModelProvider(this)[EditEventsViewModel::class.java]

        val view = editEventsBinding.root

        return view

    }
}