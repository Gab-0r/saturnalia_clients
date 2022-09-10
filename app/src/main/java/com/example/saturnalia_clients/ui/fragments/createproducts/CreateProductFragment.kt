package com.example.saturnalia_clients.ui.fragments.createproducts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentCreateProductBinding

class CreateProductFragment : Fragment() {

    private lateinit var createProductBinding: FragmentCreateProductBinding
    private lateinit var createProductViewModel: CreateProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        createProductBinding = FragmentCreateProductBinding.inflate(inflater, container, false)
        createProductViewModel = ViewModelProvider(this)[CreateProductViewModel::class.java]

        return createProductBinding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}