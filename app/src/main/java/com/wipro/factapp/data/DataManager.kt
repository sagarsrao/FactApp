package com.wipro.factapp.data

import com.wipro.factapp.feautres.factmodule.models.ResFactDataList
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton



/*This class is responsible for managaing the api sources from external sources i.e., FactRestServiceApi*/
@Singleton
class DataManager @Inject constructor(private val factRestServiceApi: FactRestServiceApi) {

    fun getTheDataFacts(): Observable<ResFactDataList> {

        return factRestServiceApi.getTheFactDetails()
    }
}