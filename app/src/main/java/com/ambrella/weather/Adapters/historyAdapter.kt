package com.ambrella.weather.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ambrella.weather.Model.City
import com.ambrella.weather.R
import com.ambrella.weather.Model.Room.tableCity

class historyAdapter: RecyclerView.Adapter<historyViewHolder>()
{
    private var Citys: List<tableCity> = listOf()
    var onHistoryClicLisener:OnHistoryClicLisener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): historyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_city,parent,false)
        return historyViewHolder(view)
    }

    override fun onBindViewHolder(holder: historyViewHolder, position: Int) {
        val quote=Citys[position]
        holder.bind(quote)
        holder.itemView.setOnClickListener { onHistoryClicLisener?.onHistoryClick(quote) }

    }

    interface OnHistoryClicLisener
    {
        fun onHistoryClick(city: tableCity)
    }



    override fun getItemCount(): Int =Citys.size

    fun setCity(t: List<tableCity>) {
        this.Citys = t
        notifyDataSetChanged()
    }

    fun isEmpty(): Boolean {
        return Citys.isEmpty()
    }


}


class historyViewHolder(view: View): RecyclerView.ViewHolder(view)
{
    private var  mCity: TextView? = null
    init {
        mCity=itemView.findViewById(R.id.tvCity)
        //    mCords=itemView.findViewById(R.id.textView4)
    }
    fun bind(currentCity: tableCity)
    {
        mCity?.text=currentCity.city
    }
}