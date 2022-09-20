package com.example.saturnalia_clients.ui.fragments.eventos

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentEventosBinding
import com.example.saturnalia_clients.ui.model.Event
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EventosFragment : Fragment() {

    private lateinit var eventosBinding: FragmentEventosBinding
    private lateinit var eventosViewModel: EventosViewModel
    private lateinit var eventAdapter: EventAdapter
    private var eventList: ArrayList<Event> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        eventosBinding = FragmentEventosBinding.inflate(inflater, container, false)
        eventosViewModel = ViewModelProvider(this)[EventosViewModel::class.java]

        val view = eventosBinding.root

        eventosViewModel.loadEvents()

        eventosViewModel.msg.observe(viewLifecycleOwner){
            showMsg(it)
        }

        eventosViewModel.eventsList.observe(viewLifecycleOwner){list->
            eventAdapter.appendItems(list)
        }

        eventosViewModel.deleteEventSuccess.observe(viewLifecycleOwner){
            showMsg(it)
        }

        eventAdapter = EventAdapter(eventList,
            onItemClicked = {onEventItemClicked(it)},
            onLongItemClicked = {onItemLongClicked(it)}
        )

        eventosBinding.eventsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@EventosFragment.requireContext())
            adapter = eventAdapter
            setHasFixedSize(false)
        }

        with(eventosBinding){
            addEventButton.setOnClickListener {
                goToCreateEvent()
            }
        }

        return view
    }

    private fun onItemLongClicked(event: Event) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage(String.format("¿Desea eliminar al evento %s ?", event.name))
                setPositiveButton(R.string.accept){dialog, id->
                    eventosViewModel.deleteItem(event)
                }
                setNegativeButton(R.string.cancel){dialog, id->
                }
            }
            builder.create()
        }
        alertDialog?.show()
    }

    private fun onEventItemClicked(it: Event) {
        findNavController().navigate(EventosFragmentDirections.actionNavigationEventsToNavigationEventDetail(it))
    }

    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

    fun goToCreateEvent(){
        findNavController().navigate(EventosFragmentDirections.actionNavigationEventsToNavigationCreateEvent())
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}