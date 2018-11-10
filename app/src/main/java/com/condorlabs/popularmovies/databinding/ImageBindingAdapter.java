package com.condorlabs.popularmovies.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.condorlabs.popularmovies.util.AppConstants;
import com.squareup.picasso.Picasso;

public final class ImageBindingAdapter {

    @BindingAdapter(value = "url")
    public static void loadPictureUrl(ImageView view, String url) {
        if (url != null && !url.equals(""))
            Picasso.get()
                    .load(AppConstants.PICTURES_URL + url)
                    .into(view);
    }

}
