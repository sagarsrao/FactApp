package com.wipro.factapp.feautres.factmodule

import com.wipro.factapp.feautres.base.MvpView
import com.wipro.factapp.feautres.factmodule.models.RowsItem


/*This class acts as an view interface class which contains  view related and the API response view functions
* coming from the presenter*/
interface FactActivityMVPView : MvpView {

    /*fun showProgress()

    fun hideProgress()*/

    fun showError(error: String)

    fun showFactResults(rowsItem: List<RowsItem?>?)
    fun showFactResultsForSwipeToRefresh(rows: List<RowsItem?>)


}