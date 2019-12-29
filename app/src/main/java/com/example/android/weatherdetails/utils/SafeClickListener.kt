package com.example.android.weatherdetails.utils

import android.os.SystemClock
import android.view.View

//Class that helps to achieve safeClick Listener
internal class SafeClickListener(private val defaultInterval: Int = 1000, private val onSafeClick: (View) -> Unit) : View.OnClickListener {
    private var lastTimeClicked: Long = 0

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) return

        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeClick(v)
    }
}