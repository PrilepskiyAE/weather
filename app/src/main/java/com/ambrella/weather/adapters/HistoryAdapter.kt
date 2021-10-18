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
    lateinit var onHistoryClickListener: OnHistoryClickListener//тоже не должен быть null = исправил
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val quote = cities.get(position)
        holder.init(quote)
        holder.itemView.setOnClickListener { onHistoryClickListener.onHistoryClick(quote) }
    }

    interface OnHistoryClickListener {
        fun onHistoryClick(city: TableCity)
    }


    override fun getItemCount(): Int = cities.size

    fun setCity(t: List<TableCity>) {
        this.cities = t
        notifyDataSetChanged()
    }
    fun getCity(pos: Int): TableCity {
        return cities.get(pos)
    }
}

class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val mCity: TextView = itemView.findViewById(R.id.tvCity)

    fun init(currentCity: TableCity) {
        mCity.text = currentCity.city
    }
}