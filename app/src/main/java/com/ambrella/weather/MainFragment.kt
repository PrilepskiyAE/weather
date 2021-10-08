package com.ambrella.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.ambrella.weather.Adapters.ListAdapter
import com.ambrella.weather.Model.City
import com.ambrella.weather.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers


class MainFragment : Fragment() {
lateinit var binding: FragmentMainBinding
    private val mCityList= listOf(
        City("Новосибирск"),
        City("Москва"),
        City("Санкт-Петербург"),
        City("Томск"),
        City("Омск"),
        City("Бийск"),
        City("Татарстан"),
        City("Ярославль"),
        City("Воронеж"),
        City("Казань"),
    )
    private lateinit var toolbar: Toolbar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentMainBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        val adapter= ListAdapter(mCityList)
        // val adapterhistory



        adapter.onCityClicLisener=object : ListAdapter.OnCityClicLisener
        {
            override fun onCityClick(city: City) {
                findNavController().navigate(R.id.infoFragment,null)
                val bundle = Bundle()
                bundle.putString("title1", city.city)
                navController.navigate(R.id.infoFragment,bundle)


            }

        }

        binding.listRecyclerView.adapter=adapter

        binding.searhicon.setOnClickListener()
        {
            findNavController().navigate(R.id.infoFragment,null)
            val bundle = Bundle()
            bundle.putString("title1",binding.tvaddcity.text.toString())
            navController.navigate(R.id.action_mainFragment_to_infoFragment,bundle)
        }


        /* binding.button.setOnClickListener()
         // {
        //       requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,BlankFragment2.newInstance()).commit()
         // }
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,BlankFragment.newInstance()).commit()
          */


    }



    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}


