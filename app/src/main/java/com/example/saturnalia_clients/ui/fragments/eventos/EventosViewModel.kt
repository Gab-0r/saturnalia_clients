package com.example.saturnalia_clients.ui.fragments.eventos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnalia_clients.ui.data.EventRepository
import com.example.saturnalia_clients.ui.data.ResourceRemote
import com.example.saturnalia_clients.ui.model.Event
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventosViewModel : ViewModel() {

    private val eventRepository = EventRepository()

    private var eventList: ArrayList<Event> = ArrayList()

    private val _eventsList: MutableLiveData<ArrayList<Event>> = MutableLiveData()
    val eventsList: LiveData<ArrayList<Event>> = _eventsList

    private val _deleteEventSuccess: MutableLiveData<String?> = MutableLiveData()
    val deleteEventSuccess: LiveData<String?> = _deleteEventSuccess

    private val _msg: MutableLiveData<String?> = MutableLiveData()
    val msg: LiveData<String?> = _msg

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

    fun deleteItem(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            eventRepository.deleteEvent(event)
            _deleteEventSuccess.postValue("Evento eliminado")
        }
    }
}