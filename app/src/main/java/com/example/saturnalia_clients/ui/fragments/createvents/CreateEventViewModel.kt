package com.example.saturnalia_clients.ui.fragments.createvents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnalia_clients.ui.data.EventRepository
import com.example.saturnalia_clients.ui.data.ResourceRemote
import com.example.saturnalia_clients.ui.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateEventViewModel : ViewModel() {

    private val eventRepository = EventRepository()

    private val _msg: MutableLiveData<String> = MutableLiveData()
    val msg: LiveData<String> = _msg

    private val _createEventSuccess: MutableLiveData<String?> = MutableLiveData()
    val createEventSuccess: LiveData<String?> = _createEventSuccess

    fun checkFields(name_: String, desc_: String, date_: String, time_: String, cover_: String) {
        if(name_.isEmpty() || desc_.isEmpty() || date_.isEmpty() || time_.isEmpty() || cover_.isEmpty())
            _msg.value = "Todos los campos deben ser llenados"
        else
            viewModelScope.launch (Dispatchers.IO){
                val event = Event(name = name_, description = desc_, date = date_, time = time_, cover = cover_)
                val result = eventRepository.createEvent(event)
                result.let { resourceRemote ->
                    when(resourceRemote){
                        is ResourceRemote.success -> {
                            _createEventSuccess.postValue(result.data)
                            _msg.postValue("Evento creado exitosamente")
                        }
                        is ResourceRemote.error -> {
                            //TODO Mensajes de error con la traducción
                        }
                        else ->{}
                    }
                }
            }
    }
}