package com.android.example.weatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.android.example.weatherapp.databinding.FragmentTitleBinding
import kotlinx.android.synthetic.main.fragment_title.*
import kotlinx.android.synthetic.main.fragment_weather.*

class TitleFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
            R.layout.fragment_title,container,false)

        binding.checkWeatherButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_weatherFragment)
            //binding.titleTextView.text = "new text"
        }


        return binding.root
    }
}