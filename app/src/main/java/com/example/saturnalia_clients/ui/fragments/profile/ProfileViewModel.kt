package com.example.saturnalia_clients.ui.fragments.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnalia_clients.ui.data.DiscoRepository
import com.example.saturnalia_clients.ui.data.ResourceRemote
import com.example.saturnalia_clients.ui.model.Disco
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val discoRepository = DiscoRepository()

    private var discoList: ArrayList<Disco> = ArrayList()

    private val _disco: MutableLiveData<Disco> = MutableLiveData()
    val disco: LiveData<Disco> = _disco

    private val _msg: MutableLiveData<String?> = MutableLiveData()
    val msg: LiveData<String?> = _msg

    fun loadProfile() {
        viewModelScope.launch {
            val result = discoRepository.loadProfile()
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.success -> {
                        result.data?.documents?.forEach { document ->
                            val disco = document.toObject<Disco>()
                            disco?.let { discoList.add(disco) }
                        }
                        _disco.postValue(discoList[0])
                    }
                    is ResourceRemote.error ->{
                        val msg = result.message
                        _msg.postValue(msg)
                    }
                    else -> {
                        //Don't use
                    }
                }
            }
        }
    }
}