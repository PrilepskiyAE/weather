package com.ambrella.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ambrella.weather.model.City
import com.ambrella.weather.R

class ListAdapter(private val list: List<City>) : RecyclerView.Adapter<CityViewHolder>() {//List adapter говно название
    var onCityClickListener: OnCityClickListener? = null// этот интерфейс не должен быть null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CityViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city: City = list[position]
        holder.bild(city)
        holder.itemView.setOnClickListener { onCityClickListener?.onCityClick(city) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnCityClickListener {//вот этот интерфейс много где пофторяется придумай как объединить
        fun onCityClick(city: City)
    }
}

class CityViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_city, parent, false)) {
    private val mCity : TextView = itemView.findViewById(R.id.tvCity)

    fun bild(city: City) {//bild нейминг впринципе норм но лучше init
        mCity.text = city.city
    }
}