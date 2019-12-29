package com.example.android.weatherdetails.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

/**
 * hideKeyboard to hide the visible keyboard when some action is performed
 * @param [view] - any android.view.View subclass/View
 */
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

//Utility for showing toast messages takes context and toast message as a string
fun showRequiredToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

//Utility usage on any View (button, links, textView etc.) will not trigger multiple api requests
fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) = this.setOnClickListener(SafeClickListener(onSafeClick = onSafeClick))