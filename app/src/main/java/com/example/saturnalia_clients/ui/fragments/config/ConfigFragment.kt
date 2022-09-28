package com.example.saturnalia_clients.ui.fragments.config

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentConfigBinding

class ConfigFragment : Fragment() {

    private lateinit var configBinding: FragmentConfigBinding
    private lateinit var configViewModel: ConfigViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        configBinding = FragmentConfigBinding.inflate(inflater, container, false)
        configViewModel = ViewModelProvider(this)[ConfigViewModel::class.java]

        val view = configBinding.root

        return view
    }
}