package com.example.randomuser.presentation.ui.binding

import android.annotation.SuppressLint
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.example.randomuser.data.model.Name
import com.example.randomuser.data.model.RandomUser
import com.example.randomuser.data.model.Timezone
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["setFullName"])
fun AppCompatTextView.bindFullName(name: Name) {
    text = "${name.first} ${name.last}"
}

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["setDetails"])
fun AppCompatTextView.bindDetails(randomUser: RandomUser) {
    text = "${randomUser.dob.age} years from ${randomUser.location.country}"
}

@SuppressLint("SetTextI18n", "SimpleDateFormat")
@BindingAdapter(value = ["setCurrentDate"])
fun AppCompatTextView.bindCurrentDate(timezone: Timezone) {
    val hours = timezone.offset.split(":")[0].toInt()
    val minutes = timezone.offset.split(":")[1].toInt()

    val calendar = Calendar.getInstance()
    calendar.add(Calendar.HOUR, hours)
    calendar.add(Calendar.MINUTE, minutes)

    text = SimpleDateFormat("HH:mm").format(calendar.time).toString()
}