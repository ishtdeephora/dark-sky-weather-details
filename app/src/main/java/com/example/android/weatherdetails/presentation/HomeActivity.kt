package com.example.android.weatherdetails.presentation

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.android.weatherdetails.R
import com.example.android.weatherdetails.utils.geocoderLatLongFetcher
import com.example.android.weatherdetails.utils.getCurrentDateInAskedFormat
import com.example.android.weatherdetails.utils.hideKeyboard
import com.example.android.weatherdetails.utils.isPrimeDate
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class HomeActivity : AppCompatActivity() {
    private val cal = Calendar.getInstance()
    private val viewModel: HomeActivityViewModel by lazy { ViewModelProviders.of(this).get(HomeActivityViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateFormat = SimpleDateFormat(getString(R.string.date_format).format(cal.time))
        date_edit_text.hint = getCurrentDateInAskedFormat.invoke(getString(R.string.date_format))
        select_date.text = (getString(R.string.select_date_text))
        location_name_value.hint = getString(R.string.current_city)
        fetch_details.text = getString(R.string.proceed_text)
        day_value.text = getString(R.string.start_text)

        viewModel.weatherDetailsLiveData.observe(this@HomeActivity, androidx.lifecycle.Observer { data ->
            temperature_value.text = getString(R.string.room_temperature_text).plus(" " + data.temp)
            temperature_min_value.text = getString(R.string.temp_min_text).plus(" " + data.minTemp)
            temperature_max_value.text = getString(R.string.temp_max_text).plus(" " + data.maxTemp)
            content_loader.visibility = View.GONE
        })

        viewModel.errorLiveData.observe(this@HomeActivity, androidx.lifecycle.Observer {
            //error handling from live data channel
        })

        date_edit_text.setOnClickListener {
            this.hideKeyboard(it)
            DatePickerDialog(this@HomeActivity, dateSetListener(),
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        fetch_details.setOnClickListener {
            temperature_value.text = getString(R.string.room_temperature_text)
            temperature_min_value.text = getString(R.string.temp_min_text)
            temperature_max_value.text = getString(R.string.temp_max_text)
            val splitDate = date_edit_text.hint.toString().split("/")
            this.hideKeyboard(it)
            if (isPrimeDate(splitDate[0].toInt())) {
                val epoch = dateFormat.parse(date_edit_text.hint.toString()).time
                day_value.text = getString(R.string.weather_in_text).plus(" " + location_name_value.text.toString().toUpperCase() + " on ").plus(SimpleDateFormat(getString(R.string.day_time_format), Locale.US).format(dateFormat.parse(date_edit_text.hint.toString())))
                val utcTime = (epoch / 1000).toInt()
                if (location_name_value.text.isNullOrEmpty().not()) {
                    content_loader.visibility = View.VISIBLE
                    viewModel.initialize(weatherRepo = WEATHER_REPO(), latitude = geocoderLatLongFetcher(this, location_name_value.text.toString()).latitude, longitude = geocoderLatLongFetcher(this, location_name_value.text.toString()).longitude, date = utcTime.toString())
                    content_loader.visibility = View.GONE
                } else {
                    Toast.makeText(this, getString(R.string.no_location_message), Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, getString(R.string.non_primary_message), Toast.LENGTH_LONG).show()
            }
        }

        previous_action.setOnClickListener {
            val splitDate = date_edit_text.hint.toString().split("/")
            Calendar.getInstance().apply {
                set(Calendar.DATE, splitDate[0].toInt() - 1)
                date_edit_text.hint = dateFormat.format(this.time)
            }
        }

        next_action.setOnClickListener {
            val splitDate = date_edit_text.hint.toString().split("/")
            Calendar.getInstance().apply {
                set(Calendar.DATE, splitDate[0].toInt() + 1)
                date_edit_text.hint = SimpleDateFormat(getString(R.string.date_format), Locale.US).format(this.time)
            }
        }

    }

    private fun dateSetListener(): DatePickerDialog.OnDateSetListener {
        return DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            date_edit_text.hint = SimpleDateFormat(getString(R.string.date_format), Locale.US).format(cal.time)
        }
    }

}

