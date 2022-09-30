package com.example.eventmanager.viewmodel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


// View mode class to get date and time picked by user
class DateAndTimeViewModel : ViewModel() {
    // Variables
    private val _time = MutableLiveData("")
    private val _date = MutableLiveData("")
    var time: LiveData<String> = _time
    var date: LiveData<String> = _date

    /**
     * @param context
     * Main to logic to obtain date and time
     */
    fun selectDateTime(context: Context) {
        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)

        DatePickerDialog(context, { _, year, month, day ->
            //val datePickerDialog = DatePickerDialog(context)
            //datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000;
            TimePickerDialog(context, { _, hour, minute ->
                val pickedDateTime = Calendar.getInstance()
                pickedDateTime.set(year, month, day, hour, minute)
                val monthAsString = if ((month + 1).toString().length == 1) {
                    "0${month + 1}"
                } else {
                    month.toString()
                }
                //Log.d("user", "from date $day - $monthAsString - $year $hour:$minute\"")
                // Setting date and time values
                _date.value = "$day - $monthAsString - $year"
                if (minute <= 9) {
                    _time.value = "$hour:0$minute"
                } else {
                    _time.value = "$hour:$minute"
                }
            }, startHour, startMinute, false).show()
        }, startYear, startMonth, startDay).show()
    }
}