package com.wipro.factapp.feautres.base

import com.wipro.factapp.feautres.factmodule.models.RowsItem

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
interface Presenter<in V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()
}
