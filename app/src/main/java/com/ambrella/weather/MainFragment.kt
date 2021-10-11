package com.ambrella.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.ambrella.weather.Adapters.ListAdapter
import com.ambrella.weather.Adapters.historyAdapter
import com.ambrella.weather.Model.City
import com.ambrella.weather.Model.Room.tableCity
import com.ambrella.weather.Repository.CityRepositoryImpl
import com.ambrella.weather.ViewModel.CityListViewModel
import com.ambrella.weather.ViewModel.CityListViewModelFactory
import com.ambrella.weather.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
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
    private lateinit var cityListViewModel: CityListViewModel
    private lateinit var viewModelFactory: ViewModelProvider.Factory
    private val historyadapter = historyAdapter()



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
        var isHistory:Boolean=true
        binding.historyList.setVisibility(View.GONE);
        binding.history.setOnClickListener{
            if (isHistory)
            {
                binding.historyList.setVisibility(View.GONE)
                binding.listRecyclerView.setVisibility(View.VISIBLE)


                isHistory=false
            }else
            {
                binding.historyList.setVisibility(View.VISIBLE)
                binding.listRecyclerView.setVisibility(View.GONE)
                isHistory=true
            }
        }



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

            if(binding.tvaddcity.text.toString() == "")
            {
                Toast.makeText(getActivity(), "А какого хрена поля пустые а? ", Toast.LENGTH_LONG).show()
            }else {
                cityListViewModel.insertCity(tableCity(city = binding.tvaddcity.text.toString()))
                historyadapter.notifyDataSetChanged()
                findNavController().navigate(R.id.infoFragment, null)
                val bundle = Bundle()
                bundle.putString("title1", binding.tvaddcity.text.toString())
                navController.navigate(R.id.infoFragment, bundle)
            }

        }

        viewModelFactory = CityListViewModelFactory(
            CityRepositoryImpl(requireContext(), Dispatchers.IO)
        )
        cityListViewModel = ViewModelProvider(this,viewModelFactory).get(CityListViewModel::class.java)
        cityListViewModel.getAllCity().observe(viewLifecycleOwner, Observer<List<tableCity>> {
            updateResults(it)
        })

        historyadapter.onHistoryClicLisener=object :historyAdapter.OnHistoryClicLisener
        {
            override fun onHistoryClick(city: tableCity) {
                findNavController().navigate(R.id.infoFragment,null)
                val bundle = Bundle()
                bundle.putString("title1",city.city )
                navController.navigate(R.id.infoFragment,bundle)
            }

        }


        historyadapter.notifyDataSetChanged()

    }


    private fun updateResults(cityt: List<tableCity>) {
        historyadapter.setCity(cityt)
        binding.historyList.apply {
            adapter = historyadapter
        }

    }



    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}


