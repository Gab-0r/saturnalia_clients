package com.example.saturnalia_clients.ui.fragments.carta

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saturnalia_clients.R

class CartaFragment : Fragment() {

    companion object {
        fun newInstance() = CartaFragment()
    }

    private lateinit var viewModel: CartaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_carta, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CartaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}