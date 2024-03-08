package ru.efremov.booklist

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import ru.efremov.booklist.di.DaggerApplicationComponent

class BookListApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}