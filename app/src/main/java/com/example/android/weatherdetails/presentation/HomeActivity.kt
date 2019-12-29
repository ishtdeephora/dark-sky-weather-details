package com.example.android.weatherdetails.presentation

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProviders
import com.example.android.weatherdetails.R
import com.example.android.weatherdetails.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*

//Home Activity for selecting the date and getting the weather details for the selected date and place

class HomeActivity : AppCompatActivity(), LifecycleObserver {
    private val cal = Calendar.getInstance()

    //viewmodel reference
    private val viewModel: HomeActivityViewModel by lazy { ViewModelProviders.of(this).get(HomeActivityViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        networkCheck()
        val dateFormat = SimpleDateFormat(getString(R.string.date_format).format(cal.time), Locale.US)
        date_edit_text.hint = getCurrentDateInAskedFormat.invoke(getString(R.string.date_format))
        select_date.text = (getString(R.string.select_date_text))
        location_name_value.hint = getString(R.string.current_city)
        location_name_value.setText(R.string.my_city)
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

        date_edit_text.setSafeOnClickListener {
            this.hideKeyboard(it)
            DatePickerDialog(this@HomeActivity, dateSetListener(),
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        fetch_details.setSafeOnClickListener {
            temperature_value.text = getString(R.string.room_temperature_text)
            temperature_min_value.text = getString(R.string.temp_min_text)
            temperature_max_value.text = getString(R.string.temp_max_text)
            val splitDate = date_edit_text.hint.toString().split("/")
            this.hideKeyboard(it)

            when (isPrimeDate(splitDate[0].toInt())) {
                true -> {
                    val epoch = dateFormat.parse(date_edit_text?.hint.toString())?.time
                    val locationNameValue = location_name_value.text.toString()
                    //for fetching the day on the given date
                    val utcTime = (epoch?.div(1000))?.toInt().toString()
                    val dateForDay = dateFormat.parse(date_edit_text.hint.toString()) as Date
                    day_value.text = getString(R.string.weather_in_text).plus(" " + locationNameValue.toUpperCase() + " on ").plus(SimpleDateFormat(getString(R.string.day_time_format), Locale.US).format(dateForDay))

                    // Don't make the api hit when location name is null or empty
                    if (locationNameValue.isEmpty().not()) {
                        val deferred = GlobalScope.async(block = {
                            geocodeLatLongFetcher(this@HomeActivity, locationNameValue)
                        })

                        runBlocking(block = {
                            val address = deferred.await().getOrNull()
                            viewModel.initialize(latitude = address?.latitude
                                    ?: "", longitude = address?.longitude ?: "", date = utcTime)
                        })
                    } else showRequiredToast(this, getString(R.string.no_location_message))

                }
                //show the toast message for non primary date
                false -> showRequiredToast(this, getString(R.string.non_primary_message))
            }
        }

        //decrement operation of the date on the field
        previous_action.setSafeOnClickListener {
            val splitDate = date_edit_text.hint.toString().split("/")
            Calendar.getInstance().apply {
                set(Calendar.DATE, splitDate[0].toInt() - 1)
                date_edit_text.hint = dateFormat.format(this.time)
            }
        }

        //increment operation of the date on the field
        next_action.setSafeOnClickListener {
            val splitDate = date_edit_text.hint.toString().split("/")
            Calendar.getInstance().apply {
                set(Calendar.DATE, splitDate[0].toInt() + 1)
                date_edit_text.hint = SimpleDateFormat(getString(R.string.date_format), Locale.US).format(this.time)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        networkCheck()
    }

    //Date picker dialog listener
    private fun dateSetListener(): DatePickerDialog.OnDateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                date_edit_text.hint = SimpleDateFormat(getString(R.string.date_format), Locale.US).format(cal.time)
            }

    //network connection check
    private fun networkCheck() {
        NetworkStateUtil.invoke(this).apply {
            when (this) {
                false -> Toast.makeText(this@HomeActivity, getString(R.string.no_network_message), Toast.LENGTH_SHORT).show()
            }
        }
    }

}

