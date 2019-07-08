package com.wipro.factapp.injection.component

import android.app.Application
import android.content.Context
import com.wipro.factapp.data.DataManager
import com.wipro.factapp.data.FactRestServiceApi
import com.wipro.factapp.injection.ApplicationContext
import com.wipro.factapp.injection.module.AppModule
import dagger.Component

import javax.inject.Singleton


/*The below contains all the project level dependecies*/
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager

    fun factApi(): FactRestServiceApi
}
