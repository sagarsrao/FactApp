package com.wipro.factapp

import android.app.Application
import android.content.Context
import com.wipro.factapp.injection.component.AppComponent
import com.wipro.factapp.injection.component.DaggerAppComponent
import com.wipro.factapp.injection.module.AppModule
import com.wipro.factapp.injection.module.NetworkModule

import timber.log.Timber

class FactAppStarterApplication : Application() {

    private var appComponent: AppComponent? = null

    companion object {
        operator fun get(context: Context): FactAppStarterApplication {
            return context.applicationContext as FactAppStarterApplication
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            //Stetho.initializeWithDefaults(this)
            //LeakCanary.install(this)
            //Sherlock.init(this)
            //Traceur.enableLogging()
        }
    }

    // Needed to replace the component with a test specific one
    var component: AppComponent
        get() {
            if (appComponent == null) {
                appComponent = DaggerAppComponent.builder()
                    .appModule(AppModule(this))
                    .networkModule(NetworkModule(this))
                    .build()
            }
            return appComponent as AppComponent
        }
        set(appComponent) {
            this.appComponent = appComponent
        }

}