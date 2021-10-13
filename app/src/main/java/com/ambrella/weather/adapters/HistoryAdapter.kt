package com.ambrella.weather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ambrella.weather.model.room.TableCity
import com.ambrella.weather.R

class HistoryAdapter : RecyclerView.Adapter<HistoryViewHolder>() {
    private var cities: List<TableCity> = listOf()
    var onHistoryClickListener: OnHistoryClickListener? = null//тоже не должен быть null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val quote = cities[position]
        holder.bind(quote)
        holder.itemView.setOnClickListener { onHistoryClickListener?.onHistoryClick(quote) }
    }

    interface OnHistoryClickListener {
        fun onHistoryClick(city: TableCity)
    }


    override fun getItemCount(): Int = cities.size

    fun setCity(t: List<TableCity>) {
        this.cities = t
        notifyDataSetChanged()
    }
}

class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val mCity: TextView = itemView.findViewById(R.id.tvCity)

    fun bind(currentCity: TableCity) {
        mCity.text = currentCity.city
    }
}