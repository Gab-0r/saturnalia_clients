package com.example.saturnalia_clients.ui.fragments.review

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentReviewBinding

class ReviewFragment : Fragment() {

    private lateinit var reviewBinding: FragmentReviewBinding
    private lateinit var reviewViewModel: ReviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        reviewBinding = FragmentReviewBinding.inflate(inflater, container, false)
        reviewViewModel = ViewModelProvider(this)[ReviewViewModel::class.java]

        val view = reviewBinding.root



        return view

    }
}