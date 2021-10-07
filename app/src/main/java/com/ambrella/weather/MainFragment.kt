package com.ambrella.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.ambrella.weather.databinding.FragmentMainBinding


class MainFragment : Fragment() {
lateinit var binding: FragmentMainBinding


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
binding.button.setOnClickListener{
            //findNavController().navigate(R.id.infoFragment,null)
    val bundle = Bundle()
    bundle.putString("title1", "title1")
    navController.navigate(R.id.action_mainFragment_to_infoFragment,bundle)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}


