package com.example.saturnalia_clients.ui.fragments.eventdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnalia_clients.ui.data.EventRepository
import com.example.saturnalia_clients.ui.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventDetailViewModel : ViewModel() {

    private val eventRepository = EventRepository()

    private val _deleteEventSuccess: MutableLiveData<String> = MutableLiveData()
    val deleteEventSuccess: LiveData<String> = _deleteEventSuccess

    fun deleteItem(event: Event) {
        viewModelScope.launch(Dispatchers.IO){
            eventRepository.deleteEvent(event)
        }
        _deleteEventSuccess.value = "Evento eliminado"
    }
}