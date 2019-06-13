package com.wipro.factapp.feautres.factmodule.models

import com.squareup.moshi.Json

data class RowsItem(

    @Json(name = "imageHref")
    val imageHref: String? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "title")
    val title: String? = null
)