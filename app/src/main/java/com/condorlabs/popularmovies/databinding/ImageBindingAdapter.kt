package com.condorlabs.popularmovies.databinding

import android.databinding.BindingAdapter
import android.widget.ImageView

import com.condorlabs.popularmovies.util.AppConstants
import com.squareup.picasso.Picasso

object ImageBindingAdapter {

    @BindingAdapter(value = ["url"])
    fun loadPictureUrl(view: ImageView, url: String?) {
        if (url != null && url != "")
            Picasso.get()
                    .load(AppConstants.PICTURES_URL + url)
                    .into(view)
    }

}
