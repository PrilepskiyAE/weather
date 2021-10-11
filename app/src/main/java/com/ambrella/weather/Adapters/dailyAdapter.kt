package com.ambrella.weather.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ambrella.weather.R
import com.ambrella.weather.retrofit.pojo.Daily
import com.ambrella.weather.utils.convertTimestapToDateTime

class dailyAdapter(
    private val weatherTemp: List<Daily>,
    private val rowLayout: Int
): RecyclerView.Adapter<dailyAdapter.dailyViewHolder>() {
    class dailyViewHolder(v: View): RecyclerView.ViewHolder(v){
        internal var date:TextView=v.findViewById(R.id.tvDate)
        internal var temp:TextView=v.findViewById(R.id.tvTemperature)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dailyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return dailyViewHolder(view)
    }

    override fun onBindViewHolder(holder: dailyViewHolder, position: Int) {
        val current= weatherTemp.get(position)

        holder.date.text=convertTimestapToDateTime(current.dt.toLong())

        if(current.temp?.day!! > 0.0){
            holder.temp.text = "+${current.temp!!.day}"
        }else
        {
            holder.temp.text = current.temp!!.day.toString()
        }


    }

    override fun getItemCount(): Int {
        return weatherTemp.size
    }


}
