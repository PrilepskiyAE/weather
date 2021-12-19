package com.ambrella.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ambrella.weather.adapters.HistoryAdapter
import com.ambrella.weather.adapters.CityListAdapter
import com.ambrella.weather.databinding.FragmentMainBinding
import com.ambrella.weather.model.City
import com.ambrella.weather.model.room.TableCity
import com.ambrella.weather.repository.CityRepositoryImpl
import com.ambrella.weather.viewModel.CityListViewModel
import com.ambrella.weather.viewModel.CityListViewModelFactory
import kotlinx.coroutines.Dispatchers


class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    private val mCityList = listOf(
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
    private val historyadapter = HistoryAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        val adapter = CityListAdapter(mCityList)
        var isHistory: Boolean = true
        val swapHelper = getSwapMg()
        swapHelper.attachToRecyclerView(binding.historyList)
        binding.historyList.setVisibility(View.GONE);
        binding.history.setOnClickListener {
            if (isHistory) {
                binding.historyList.setVisibility(View.GONE)
                binding.listRecyclerView.setVisibility(View.VISIBLE)


                isHistory = false
            } else {
                binding.historyList.setVisibility(View.VISIBLE)
                binding.listRecyclerView.setVisibility(View.GONE)
                isHistory = true
            }
        }
  adapter.onCityClickListener = object : CityListAdapter.OnCityClickListener{
            override fun onCityClick(city: City) {
                navigationFrag(navController,city.city)
            }

        }

        binding.listRecyclerView.adapter = adapter

        binding.searhicon.setOnClickListener()
        {

            if (binding.tvaddcity.text.toString() == "") {
                Toast.makeText(getActivity(), "А почему поля пустые а? ", Toast.LENGTH_LONG)
                    .show()
            } else {
                cityListViewModel.insertCity(TableCity(city = binding.tvaddcity.text.toString()))
                navigationFrag(navController,binding.tvaddcity.text.toString())
            }

        }

        viewModelFactory = CityListViewModelFactory(
            CityRepositoryImpl(requireContext(), Dispatchers.IO)
        )
        cityListViewModel =
            ViewModelProvider(this, viewModelFactory).get(CityListViewModel::class.java)
        cityListViewModel.getAllCity().observe(viewLifecycleOwner, Observer<List<TableCity>> {
            updateResults(it)
        })
/*
        historyadapter.onHistoryClicLisener = object : HistoryAdapter.OnHistoryClicLisener {
            override fun onHistoryClick(city: TableCity) {
                navigationFrag(navController,city.city)

            }

        }

 */

        historyadapter.onHistoryClickListener =object : HistoryAdapter.OnHistoryClickListener{
            override fun onHistoryClick(city: TableCity) {
                navigationFrag(navController,city.city)
            }

        }

    }

    private fun navigationFrag(navController: NavController, message:String) {

        //findNavController().navigate(R.id.infoFragment, null)
        val bundle = Bundle()
        bundle.putString("title1", message)
        //navController.navigate(R.id.infoFragment, bundle)
        navController.navigate(R.id.action_mainFragment_to_infoFragment, bundle)
    }

    private fun getSwapMg(): ItemTouchHelper {
        return ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                   cityListViewModel.deleteCity(historyadapter.getCity(viewHolder.adapterPosition))

                }
            }
        )
    }

    private fun updateResults(cityt: List<TableCity>) {
        historyadapter.setCity(cityt)
        binding.historyList.apply {
            adapter = historyadapter
        }

    }

}
