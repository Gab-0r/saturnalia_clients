package com.example.saturnalia_clients.ui.fragments.editproducts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentEditProductsBinding

class EditProductsFragment : Fragment() {

    private lateinit var editProductBinding: FragmentEditProductsBinding
    private lateinit var editProductViewModel: EditProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        editProductBinding = FragmentEditProductsBinding.inflate(layoutInflater, container, false)
        editProductViewModel = ViewModelProvider(this)[EditProductsViewModel::class.java]

        val view = editProductBinding.root

        return view
    }
}