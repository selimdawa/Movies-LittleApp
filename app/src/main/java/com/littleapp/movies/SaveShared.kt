package com.littleapp.movies

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SaveShared {

    companion object {
        fun setFavorite(context: Context, key: String, value: Boolean) {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            sharedPreferences.edit {
                putBoolean(key, value)
            }
        }

        fun getFavorite(context: Context, key: String): Boolean {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return sharedPreferences.getBoolean(key, false)
        }
    }
}