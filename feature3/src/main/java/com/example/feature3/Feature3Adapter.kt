package com.example.feature3

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.core.inflate
import kotlinx.android.synthetic.main.feature3_item.view.*

class Feature3Adapter : RecyclerView.Adapter<Feature3Adapter.ViewHolder>() {

    private var entities = listOf<Planet>()
    private var listener: Listener? = null

    fun setListener(listener : Listener?) {
        this.listener = listener
    }

    fun setEntities(entities: List<Planet>) {
        this.entities = entities
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = entities.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.feature3_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(entities[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.textView
        fun bind(planet: Planet) {
            textView.text = planet.name
            itemView.setOnClickListener {
                listener?.onPlanetClick(planet)
            }
        }
    }


    interface Listener {
        fun onPlanetClick(planet: Planet)
    }
}

