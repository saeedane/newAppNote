package com.barmej.notesapp.databinding;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class ImageBindingAdapter {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView view, Uri uri) {
        view.setImageURI(uri);
    }
}
