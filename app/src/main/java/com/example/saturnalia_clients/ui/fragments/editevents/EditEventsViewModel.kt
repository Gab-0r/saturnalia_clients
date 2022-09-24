package com.example.saturnalia_clients.ui.fragments.editevents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnalia_clients.ui.data.EventRepository
import com.example.saturnalia_clients.ui.data.ResourceRemote
import com.example.saturnalia_clients.ui.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditEventsViewModel : ViewModel() {

    private val eventRepository = EventRepository()

    private val _msg: MutableLiveData<String> = MutableLiveData()
    val msg: LiveData<String> = _msg

    private val _editEventSuccess: MutableLiveData<String?> = MutableLiveData()
    val editEventSuccess: LiveData<String?> = _editEventSuccess

    fun checkFields(id_: String, name_: String, desc_: String, date_: String?, time_: String?, cover_: String) {
        if(name_.isEmpty() || desc_.isEmpty() || cover_.isEmpty())
            _msg.value = "Todos los campos deben ser llenados"
        else
            viewModelScope.launch(Dispatchers.IO){
                val event = Event(id = id_,name_, description = desc_, date = date_, time = time_, cover = cover_)
                val result = eventRepository.editEvent(event)
                result.let { resourceRemote ->
                    when(resourceRemote){
                        is ResourceRemote.success -> {
                            _editEventSuccess.postValue(result.data)
                            _msg.postValue("Cambios guardados")
                        }
                        is ResourceRemote.error -> {
                            //TODO mensajes de error con la traducción
                        }
                        else -> {
                            //Don't use
                        }
                    }
                }
            }
    }
}