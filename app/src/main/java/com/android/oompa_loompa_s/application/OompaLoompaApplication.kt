package com.android.oompa_loompa_s.application

import android.app.Application
import com.android.oompa_loompa_s.dagger.AppComponent
import com.android.oompa_loompa_s.dagger.DaggerAppComponent

class OompaLoompaApplication : Application(){
    lateinit var oompaLoompaComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        oompaLoompaComponent = initDagger(this)
    }

    private fun initDagger(app : OompaLoompaApplication): AppComponent =
        DaggerAppComponent.builder()
            .build()
}