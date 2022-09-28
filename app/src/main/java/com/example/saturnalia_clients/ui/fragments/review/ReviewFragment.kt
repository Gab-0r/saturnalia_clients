package com.example.saturnalia_clients.ui.fragments.review

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentReviewBinding
import com.example.saturnalia_clients.ui.model.Review

class ReviewFragment : Fragment() {

    private lateinit var reviewBinding: FragmentReviewBinding
    private lateinit var reviewViewModel: ReviewViewModel
    private lateinit var reviewAdapater: ReviewAdapter
    private var reviewList: ArrayList<Review> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        reviewBinding = FragmentReviewBinding.inflate(inflater, container, false)
        reviewViewModel = ViewModelProvider(this)[ReviewViewModel::class.java]

        val view = reviewBinding.root

        reviewViewModel.loadReviews()

        reviewViewModel.msg.observe(viewLifecycleOwner){
            showMsg(it)
        }

        reviewViewModel.averageRating.observe(viewLifecycleOwner){
            with(reviewBinding){
                ratingBarAverage.rating = it
            }
        }

        reviewViewModel.reviewsList.observe(viewLifecycleOwner){list ->
            reviewAdapater.appendItems(list)
        }

        reviewAdapater = ReviewAdapter(reviewList)

        reviewBinding.reviewRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ReviewFragment.requireContext())
            adapter = reviewAdapater
            setHasFixedSize(false)
        }

        return view

    }

    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }
}