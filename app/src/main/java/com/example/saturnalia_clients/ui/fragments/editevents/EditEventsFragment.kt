package com.example.saturnalia_clients.ui.fragments.editevents

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.navigation.fragment.navArgs
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentEditEventsBinding
import java.util.*

class EditEventsFragment : Fragment() {

    private lateinit var editEventsBinding: FragmentEditEventsBinding
    private lateinit var editEventsViewModel: EditEventsViewModel
    private var eventDate: String? = ""
    private var eventTime: String? =  ""

    private val calendar = Calendar.getInstance()

    private val args: EditEventsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        editEventsBinding = FragmentEditEventsBinding.inflate(inflater, container, false)
        editEventsViewModel = ViewModelProvider(this)[EditEventsViewModel::class.java]

        val view = editEventsBinding.root

        val event = args.eventToEdit

        val dateSetListener = DatePickerDialog.OnDateSetListener{ _, year, month, dayOfmonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfmonth)

            val format = "dd/MM/yyyy"
            val sdf = java.text.SimpleDateFormat(format)
            eventDate = sdf.format(calendar.time).toString()
            editEventsBinding.eventEditDateButton.text = eventDate
        }

        val timeSetListener = TimePickerDialog.OnTimeSetListener{ _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)

            val format = "h:m:a"
            val sdf = java.text.SimpleDateFormat(format)
            eventTime = sdf.format(calendar.time).toString()
            editEventsBinding.eventEditTimeButton.text = eventTime
        }

        with(editEventsBinding){
            textInputEventEditName.setText(event.name)
            textInputEventEditDesc.setText(event.description)
            inputTextEventEditCover.setText(event.cover)
            eventEditDateButton.text = event.date
            eventEditTimeButton.text = event.time
            eventDate = event.date
            eventTime = event.time

            eventEditDateButton.setOnClickListener {
                DatePickerDialog(
                    requireContext(),
                    dateSetListener,
                    event.date?.subSequence(6,10).toString().toInt(),
                    event.date?.subSequence(3,5).toString().toInt()-1,
                    event.date?.subSequence(0,2).toString().toInt()
                ).show()
            }

            eventEditTimeButton.setOnClickListener {
                TimePickerDialog(
                    requireContext(),
                    timeSetListener,
                    event.time?.subSequence(0,1).toString().toInt(),
                    event.time?.subSequence(2,4).toString().toInt(),
                    false,
                ).show()
            }

        }

        return view

    }
}