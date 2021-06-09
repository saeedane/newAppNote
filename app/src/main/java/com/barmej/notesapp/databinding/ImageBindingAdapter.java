package com.barmej.notesapp.databinding;

import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class ImageBindingAdapter {
    @BindingAdapter({"android:imageUri"})
    public static void setImageViewResource(ImageView imageView, Uri uri) {
        imageView.setImageURI(uri);
    }

}
