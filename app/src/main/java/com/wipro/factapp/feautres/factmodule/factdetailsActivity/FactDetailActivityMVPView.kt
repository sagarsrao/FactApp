package com.wipro.factapp.feautres.factmodule.factdetailsActivity

import com.wipro.factapp.feautres.base.MvpView
import com.wipro.factapp.feautres.factmodule.models.RowsItem


/*This class is reponsible for holing view level functions*/
interface FactDetailActivityMVPView : MvpView {

    fun showProgress()

    fun hideProgress()


}