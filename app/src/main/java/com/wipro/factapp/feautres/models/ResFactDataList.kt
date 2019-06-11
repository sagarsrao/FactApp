package com.wipro.factapp.feautres.models

import com.squareup.moshi.Json

data class ResFactDataList(

	@Json(name="title")
	val title: String? = null,

	@Json(name="rows")
	val rows: List<RowsItem?>? = null
)