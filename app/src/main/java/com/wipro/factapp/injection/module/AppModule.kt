package com.wipro.factapp.injection.module

import android.app.Application
import android.content.Context
import com.wipro.factapp.injection.ApplicationContext
import com.wipro.factapp.injection.module.ApiModule
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(ApiModule::class))
class AppModule(private val application: Application) {

    @Provides
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return application
    }
}