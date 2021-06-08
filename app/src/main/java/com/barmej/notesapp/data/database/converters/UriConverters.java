package com.barmej.notesapp.data.database.converters;

import android.net.Uri;

import androidx.room.TypeConverter;

public class UriConverters  {

    @TypeConverter
    public Uri fromString(String value){

        if (value == null){
            return null;
        }
        return Uri.parse(value);
    }

    @TypeConverter
    public String toString(Uri uri){

        return uri.toString();

    }


}
