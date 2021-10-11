package com.ambrella.weather.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ambrella.weather.Model.City
import com.ambrella.weather.R
import com.ambrella.weather.retrofit.pojo.Hourly
import com.ambrella.weather.utils.convertTimestapToTime

class hourlyAdapter (
    private val weatherTemp: List<Hourly>,
    private val rowLayout: Int
): RecyclerView.Adapter<hourlyAdapter.hourlyViewHolder>() {

    class hourlyViewHolder(v: View): RecyclerView.ViewHolder(v){
        internal var time: TextView =v.findViewById(R.id.tvTime)
        internal var temp: TextView =v.findViewById(R.id.tvTemp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): hourlyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return hourlyViewHolder(view)
    }

    override fun onBindViewHolder(holder: hourlyViewHolder, position: Int) {
        val current= weatherTemp.get(position)

        holder.time.text= convertTimestapToTime(current.dt.toLong())
        if(current.temp > 0.0){
            holder.temp.text = "+${current.temp}"
        }else
        {
            holder.temp.text = current.temp.toString()
        }

    }

    override fun getItemCount(): Int {
        return weatherTemp.size
    }



}