package com.wipro.factapp.data

import com.wipro.factapp.feautres.factmodule.models.ResFactDataList
import io.reactivex.Observable
import retrofit2.http.GET

interface FactRestServiceApi {


    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getTheFactDetails(): Observable<ResFactDataList>


}