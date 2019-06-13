package com.wipro.factapp.feautres.factmodule

import com.wipro.factapp.feautres.base.MvpView
import com.wipro.factapp.feautres.factmodule.models.RowsItem

interface FactActivityMVPView : MvpView {

    fun showProgress()

    fun hideProgress()

    fun showError(error: String)

    fun showFactResults(rowsItem: List<RowsItem?>?)


}