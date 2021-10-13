package com.ambrella.weather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ambrella.weather.R
import com.ambrella.weather.retrofit.pojo.Hourly
import com.ambrella.weather.utils.convertTimestapToTime

class HoursAdapter(
    private val weatherTemp: List<Hourly>,
    private val rowLayout: Int
) : RecyclerView.Adapter<HourViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return HourViewHolder(view)
    }

    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {
        val current = weatherTemp[position]//здесь все таки котлин а не джава использовал раньше get()
        holder.time.text = convertTimestapToTime(current.dt)
        if (current.temp > 0.0) {
            holder.temp.text = "+${current.temp}"
        } else {
            holder.temp.text = current.temp.toString()
        }

    }

    override fun getItemCount(): Int {
        return weatherTemp.size
    }
}

class HourViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    internal var time: TextView = v.findViewById(R.id.tvTime) //здесь не var а val
    internal var temp: TextView = v.findViewById(R.id.tvTemp) //здесь не var а val
}