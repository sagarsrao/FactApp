package com.wipro.factapp.injection.module

import com.wipro.factapp.data.FactRestServiceApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = arrayOf(NetworkModule::class))
class ApiModule {

    @Provides
    @Singleton
    internal fun providePokemonApi(retrofit: Retrofit): FactRestServiceApi =
            retrofit.create(FactRestServiceApi::class.java)
}