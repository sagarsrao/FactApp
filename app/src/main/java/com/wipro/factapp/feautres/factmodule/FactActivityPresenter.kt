package com.wipro.factapp.feautres.factmodule

import android.annotation.SuppressLint
import com.wipro.factapp.data.DataManager
import com.wipro.factapp.injection.ConfigPersistent
import com.wipro.factapp.feautres.base.BasePresenter
import com.wipro.factapp.feautres.factmodule.models.RowsItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class FactActivityPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<FactActivityMVPView>() {


    @SuppressLint("CheckResult")
    fun getFactData() {
        checkViewAttached()
        mvpView?.showProgress()
        dataManager.getTheDataFacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->

                mvpView?.apply {
                    hideProgress()

                    showFactResults(result.rows)

                }


            }, { throwable ->

                mvpView?.apply {

                    hideProgress()
                    showError("No Results Found")
                }
            })


    }


    @SuppressLint("CheckResult")
    fun getFactDataForSwipeToRefresh() {
        checkViewAttached()
        mvpView?.showProgress()
        dataManager.getTheDataFacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->

                mvpView?.apply {
                    hideProgress()

                    showFactResultsForSwipeToRefresh(result.rows!!)

                }


            }, { throwable ->

                mvpView?.apply {

                    hideProgress()
                    showError("No Results Found")
                }
            })


    }
}