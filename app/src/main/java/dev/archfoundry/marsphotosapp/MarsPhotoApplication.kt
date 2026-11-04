package dev.archfoundry.marsphotosapp

import android.app.Application
import dev.archfoundry.marsphotosapp.Data.AppContainer
import dev.archfoundry.marsphotosapp.Data.DefaultAppContainer

class MarsPhotoApplication: Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
