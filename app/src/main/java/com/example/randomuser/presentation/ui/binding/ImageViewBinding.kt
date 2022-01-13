package com.example.randomuser.presentation.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["setImageUrl"])
fun ImageView.bindImageUrl(url: String?) {
    if (url.isNullOrBlank().not()) {
        Glide.with(this)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    }
}