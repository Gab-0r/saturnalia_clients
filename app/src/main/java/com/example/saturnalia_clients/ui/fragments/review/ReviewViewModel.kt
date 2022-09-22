package com.example.saturnalia_clients.ui.fragments.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnalia_clients.ui.data.ResourceRemote
import com.example.saturnalia_clients.ui.data.ReviewRepository
import com.example.saturnalia_clients.ui.model.Review
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class ReviewViewModel : ViewModel() {

    private var reviewRepository = ReviewRepository()
    private var reviewList: ArrayList<Review> = ArrayList()

    private val _reviewsList: MutableLiveData<ArrayList<Review>> = MutableLiveData()
    val reviewsList: LiveData<ArrayList<Review>> = _reviewsList

    private val _msg: MutableLiveData<String?> = MutableLiveData()
    val msg: LiveData<String?> = _msg

    fun loadReviews() {
        viewModelScope.launch {
            reviewList.clear()
            var result = reviewRepository.loadReviews()
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.success -> {
                        result?.data?.documents?.forEach { document ->
                            val review = document.toObject<Review>()
                            review?.let { reviewList.add(review) }
                        }
                        _reviewsList.postValue(reviewList)
                    }
                    is ResourceRemote.error -> {
                        val msg = result.message
                        _msg.postValue(msg)
                    }
                    else -> {
                        //don't use
                    }
                }
            }
        }
    }
}