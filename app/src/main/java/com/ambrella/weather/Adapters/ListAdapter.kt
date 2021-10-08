package com.ambrella.weather.Adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ambrella.weather.Model.City
import com.ambrella.weather.R



class ListAdapter(private val list: List<City>):RecyclerView.Adapter<CityViewHolder>()
{
    var onCityClicLisener: OnCityClicLisener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        var inflater=LayoutInflater.from(parent.context)
        return CityViewHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city: City =list[position]
        holder.bild(city)
        holder.itemView.setOnClickListener { onCityClicLisener?.onCityClick(city)}
    }

    override fun getItemCount(): Int {
      return list.size
    }

    interface OnCityClicLisener
    {
        fun onCityClick(city: City)
    }

}

class CityViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_city, parent, false)) {
    private var  mCity: TextView? = null
    // private var  mCords: TextView? = null

    init {
        mCity=itemView.findViewById(R.id.tvCity)
        //    mCords=itemView.findViewById(R.id.textView4)
    }
    fun bild(city: City)
    {
        mCity?.text=city.city
        //    mCords?.text=city.cord
    }

}