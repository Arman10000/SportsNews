package android.example.sportsNews.di

import android.app.Application
import android.example.sportsNews.di.component.DaggerSportsNewsComponent

class App : Application() {
    val sportsNewsComponent by lazy {
        DaggerSportsNewsComponent.factory().create(this)
    }
}