package com.android.example.weatherapp


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.http.GET
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    fun getWeather(@Query("q")CITY:String="London", @Query("appid")appid:String="fdc584ed4620d4dc652b1eabfeef3e72"):Deferred<WeatherApi>
    companion object{
        operator fun invoke(/*connectivityInterceptor: ConnectivityInterceptor*/): WeatherApiService {

            val okHttpClient= OkHttpClient.Builder()
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiService::class.java)
        }
    }
}