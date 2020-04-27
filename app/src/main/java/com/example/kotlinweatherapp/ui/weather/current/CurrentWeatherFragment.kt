package com.example.kotlinweatherapp.ui.weather.current

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinweatherapp.R
import com.example.kotlinweatherapp.internal.glide.GlideApp
import com.example.kotlinweatherapp.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class CurrentWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private  val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch {
        val currentWeather = viewModel.weather.await()

        val weatherLocation = viewModel.weatherLocation.await()

        weatherLocation.observe(this@CurrentWeatherFragment, Observer { location ->
            if(location == null) return@Observer
            updateLocatiom(location.name)
        })

        currentWeather.observe(this@CurrentWeatherFragment, Observer {
            if (it == null) {
                return@Observer
            }

            group_loading.visibility = View.GONE
            updateDateToToday()
            updateTemperatures(it.temperature, it.feelslike)
            updateDescription(it.weatherDescriptions)
            updatePrecipitation(it.precip)
            updateWind(it.windDir, it.windSpeed)
            updateVisibility(it.visibility)

            GlideApp.with(this@CurrentWeatherFragment)
                .load(it.weatherIcons)
                .into(imageView_condition_icon)
        })
    }

    private fun chooseLocalizedUnitAbbreviation(metric: String, imperial: String): String {
        return if (viewModel.isMetric) metric else imperial
    }

    private fun getValue(unitAbreviation: String, value: Double): Double {
        if (!viewModel.isMetric) {
            if (unitAbreviation == "°F") {
                return (value * (9/5)) + 32
            }
            if (unitAbreviation == "in") {
                return value / 25.4
            }
            if (unitAbreviation == "mph") {
                return value / 1.609
            }
            if (unitAbreviation == "mi") {
                return  value / 1.609
            }
        }

        return value
    }

    private fun updateLocatiom(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateDateToToday() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }

    private fun updateTemperatures(temperature: Double, feelsLike: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("°C", "°F")
        val temp = getValue(unitAbbreviation, temperature)
        val feelsLikeTemp = getValue(unitAbbreviation, feelsLike)

        textView_temperature.text = "$temp$unitAbbreviation"
        textView_feels_like_temperature.text = "Feels like $feelsLikeTemp$unitAbbreviation"
    }

    private fun updateDescription(description: String) {
        textView_condition.text = description
    }

    private fun updatePrecipitation(precipitationVolume: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("mm", "in")
        val precip = getValue(unitAbbreviation, precipitationVolume)

        textView_precipitation.text = "Precipitation: $precip$unitAbbreviation"
    }

    private fun updateWind(windDirection: String, windSpeed: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("kph", "mph")
        val windSp = getValue(unitAbbreviation, windSpeed)

        textView_wind.text = "Wind: $windDirection, $windSp $unitAbbreviation"
    }

    private fun updateVisibility(visibilityDistance: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("km", "mi")
        val visib = getValue(unitAbbreviation, visibilityDistance)

        textView_visibility.text = "Visibility: $visib $unitAbbreviation"
    }
}
