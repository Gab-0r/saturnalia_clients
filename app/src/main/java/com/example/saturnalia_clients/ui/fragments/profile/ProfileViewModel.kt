package com.example.saturnalia_clients.ui.fragments.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnalia_clients.ui.data.DiscoRepository
import com.example.saturnalia_clients.ui.data.EventRepository
import com.example.saturnalia_clients.ui.data.ResourceRemote
import com.example.saturnalia_clients.ui.model.Disco
import com.example.saturnalia_clients.ui.model.Event
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val discoRepository = DiscoRepository()
    private val eventRepository = EventRepository()

    private var discoList: ArrayList<Disco> = ArrayList()

    private var eventList: ArrayList<Event> = ArrayList()

    private val _eventsList: MutableLiveData<ArrayList<Event>> = MutableLiveData()
    val eventsList: LiveData<ArrayList<Event>> = _eventsList


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

    fun loadEvents() {
        viewModelScope.launch {
            eventList.clear()
            var result = eventRepository.loadEvents()
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.success ->{
                        result.data?.documents?.forEach { document ->
                            val event = document.toObject<Event>()
                            event?.let { eventList.add(event) }
                        }
                        _eventsList.postValue(eventList)
                    }
                    is ResourceRemote.error ->{
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