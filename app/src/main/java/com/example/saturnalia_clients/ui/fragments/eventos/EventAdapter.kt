package com.example.saturnalia_clients.ui.fragments.eventos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.CardViewEventItemBinding
import com.example.saturnalia_clients.ui.model.Event
import com.squareup.picasso.Picasso

class EventAdapter(
    private val eventList: ArrayList<Event>,
    private val onItemClicked: (Event) -> Unit,
    private val onLongItemClicked: (Event) -> Unit
) : RecyclerView.Adapter<EventAdapter.EventViewHolder> (){

    //Asignar el XML que se va a pintar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_event_item, parent, false)
        return EventViewHolder(view)
    }

    //Coger un elemeto de la lista de productos y lo pinta
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]
        holder.bind(event)
        holder.itemView.setOnClickListener { onItemClicked(eventList[position]) }
        holder.itemView.setOnLongClickListener { onLongItemClicked(eventList[position])
            true
        }
    }

    override fun getItemCount(): Int = eventList.size

    fun appendItems(newList: ArrayList<Event>){
        eventList.clear()
        eventList.addAll(newList)
        notifyDataSetChanged()
    }

    class EventViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private var binding = CardViewEventItemBinding.bind(itemView)

        fun bind(event_ : Event){
            with(binding){
                eventNameCard.text = event_.name
                eventDateCard.text = event_.date
                eventTimeCard.text = event_.time
                //imagen
                if(event_.urlPhoto != null)
                    Picasso.get().load(event_.urlPhoto).into(eventCardImage)
            }
        }

    }



}