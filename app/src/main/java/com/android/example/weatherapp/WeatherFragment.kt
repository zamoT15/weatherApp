package com.android.example.weatherapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.example.weatherapp.databinding.FragmentWeatherBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlin.math.roundToInt

lateinit var binding: FragmentWeatherBinding
val currentDateTime = LocalDateTime.now()
var City: String = "London"
fun setCITY(_city: String) {
    City = _city
}

class WeatherFragment: Fragment() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather, container, false
        )
        val apiService = WeatherApiService()

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val weatherResponse = apiService.getWeather(City).await()
                val updatedAt = currentDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM))
                binding.address.text = City
                binding.temp.text =
                    ((weatherResponse.main.temp - 273.15).roundToInt()).toString() + "°C"
                binding.status.text = weatherResponse.weather[0].description.capitalize()
                binding.wind.text = weatherResponse.wind.speed.toString() + " m/s"
                binding.updatedAt.text = updatedAt
                binding.pressure.text = weatherResponse.main.pressure.toString() + " hPa"
                binding.humidity.text = weatherResponse.main.humidity.toString() + "%"
            }
            catch (e: Exception){
                Log.d("WeatherFragment", e.toString())
            }
        }
        return binding.root
    }
}















/*
package com.android.example.weatherapp

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.example.weatherapp.databinding.FragmentWeatherBinding
import kotlinx.android.synthetic.main.fragment_title.*
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


var City: String = "London"
fun setCITY(_city: String) {
    City = _city

}


class WeatherFragment : Fragment() {

    lateinit var binding: FragmentWeatherBinding

    val API: String = "fdc584ed4620d4dc652b1eabfeef3e72"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather, container, false
        )
      //  CITY = "London"
        //CITY = titleTextView.text as String
        //CITY = titleTextView.text.toString()
        weatherTask().execute()
        return binding.root

    }


    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            */
/* Showing the ProgressBar, Making the main design GONE *//*

            binding.loader.visibility = View.VISIBLE
            binding.mainContainer.visibility = View.GONE
            binding.errorText.visibility = View.GONE
        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$City&units=metric&appid=$API").toString()
                Log.i("WeatherFragment", response)
            } catch (e: Exception) {
                response = null
                Log.i("WeatherFragment", "exception caught")
                Log.i("WeatherFragment", City)
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                */
/* Extracting JSON returns from the API *//*

                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt: Long = jsonObj.getLong("dt")
                val updatedAtText =
                    "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                        Date(updatedAt * 1000)
                    )
                val temp = main.getString("temp") + "°C"
                val tempMin = "Min Temp: " + main.getString("temp_min") + "°C"
                val tempMax = "Max Temp: " + main.getString("temp_max") + "°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")

                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")

                val address = jsonObj.getString("name") + ", " + sys.getString("country")

                */
/* Populating extracted data into our views *//*

                binding.address.text = address
                binding.updatedAt.text = updatedAtText
                binding.status.text = weatherDescription.capitalize()
                binding.temp.text = temp
                binding.tempMin.text = tempMin
                binding.tempMax.text = tempMax
                binding.sunrise.text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
                binding.sunset.text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
                binding.wind.text = windSpeed
                binding.pressure.text = pressure
                binding.humidity.text = humidity

                */
/* Views populated, Hiding the loader, Showing the main design *//*

                binding.loader.visibility = View.GONE
                binding.mainContainer.visibility = View.VISIBLE
                Log.i("WeatherFragment", "try done2")

            } catch (e: Exception) {
                binding.loader.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
               // binding.mainContainer.visibility = View.VISIBLE
                Log.i("WeatherFragment", "exception caught2")
            }

        }
    }
}

*/
