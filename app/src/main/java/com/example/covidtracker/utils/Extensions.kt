package com.example.covidtracker.utils

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.ClipDrawable
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtracker.activities.MainActivity


fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}


fun RecyclerView.addDecoration(activity: MainActivity) {

    val decoration = DividerItemDecoration(
        activity as MainActivity,
        ClipDrawable.HORIZONTAL
    )


    this.addItemDecoration(decoration)
}