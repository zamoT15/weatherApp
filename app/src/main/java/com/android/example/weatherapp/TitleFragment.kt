package com.android.example.weatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.android.example.weatherapp.databinding.FragmentTitleBinding
import kotlinx.android.synthetic.main.fragment_title.*


class TitleFragment : Fragment(), AdapterView.OnItemSelectedListener {

    lateinit var binding: FragmentTitleBinding
    private var spinner: Spinner? = null
    private var arrayAdapter: ArrayAdapter<String>? = null
    private var cities = arrayOf("New York", "Washington", "London")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_title, container, false
        )

        binding.checkWeatherButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_weatherFragment)
        }

        spinner = binding.spinnerCities
        arrayAdapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, cities) }
        spinner?.adapter = arrayAdapter
        spinner?.onItemSelectedListener = this
        return binding.root
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //Toast.makeText(activity, "Nothing Selected", Toast.LENGTH_SHORT).show()
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var items:String = parent?.getItemAtPosition(position) as String
        titleTextView.text = items
        //items = titleTextView.text.toString()
        /*when (items) {
            "New York" -> titleTextView.text = items
            "Washington" -> items = "2"
            "London" -> items = "3"
            else -> items = "Smtng wnt wrung"
        }*/
        Toast.makeText(activity, "${titleTextView.text}", Toast.LENGTH_SHORT).show()
    }
}