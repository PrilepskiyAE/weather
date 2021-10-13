package com.ambrella.weather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ambrella.weather.R
import com.ambrella.weather.retrofit.pojo.Daily
import com.ambrella.weather.utils.convertTimestapToDateTime

class DailyAdapter(
    private val weatherTemp: List<Daily>,
    private val rowLayout: Int
) : RecyclerView.Adapter<DailyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return DailyViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val current = weatherTemp[position]
        holder.date.text = convertTimestapToDateTime(current.dt)
        if (current.temp.day > 0.0) {
            holder.temp.text = "+${current.temp.day}"
        } else {
            holder.temp.text = current.temp.day.toString()
        }
    }

    override fun getItemCount(): Int {
        return weatherTemp.size
    }
}

class DailyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    internal var date: TextView = v.findViewById(R.id.tvDate)
    internal var temp: TextView = v.findViewById(R.id.tvTemperature)
}