package com.example.saturnalia_clients.ui.fragments.createvents

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.saturnalia_clients.databinding.FragmentCreateEventBinding
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

class CreateEventFragment : Fragment() {

    private lateinit var createEventBinding: FragmentCreateEventBinding
    private lateinit var createEventViewModel: CreateEventViewModel
    private var eventDate = ""
    private var eventTime = ""
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        createEventBinding = FragmentCreateEventBinding.inflate(inflater, container, false)
        createEventViewModel = ViewModelProvider(this)[CreateEventViewModel::class.java]

        val view = createEventBinding.root

        createEventViewModel.msg.observe(viewLifecycleOwner){
            showMsg(it)
        }

        createEventViewModel.createEventSuccess.observe(viewLifecycleOwner){
            goToEvents()
        }

        val dateSetListener = DatePickerDialog.OnDateSetListener{ _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val format = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(format)
            eventDate = sdf.format(calendar.time).toString()
            createEventBinding.eventDateButton.text = eventDate
        }

        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)

            val format = "h:m:a"
            val sdf = SimpleDateFormat(format)
            eventTime = sdf.format(calendar.time).toString()
            createEventBinding.eventTimeButton.text = eventTime
        }

        with(createEventBinding){
            eventDateButton.setOnClickListener {
                DatePickerDialog(
                    requireContext(),
                    dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            eventTimeButton.setOnClickListener {
                TimePickerDialog(
                    requireContext(),
                    timeSetListener,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    false
                ).show()
            }

            createEventButton.setOnClickListener {
                createEventViewModel.checkFields(textInputEventName.text.toString(),
                        textInputEventDesc.text.toString(), eventDate, eventTime, inputTextEventCover.text.toString()
                    )
            }

        }

        return view
    }

    private fun goToEvents() {
        findNavController().navigate(CreateEventFragmentDirections.actionNavigationCreateEventToNavigationEvents())
    }

    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }
}