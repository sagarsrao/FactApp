package com.wipro.factapp.feautres.factmodule

import android.annotation.SuppressLint
import com.wipro.factapp.data.DataManager
import com.wipro.factapp.data.local.PreferencesHelper
import com.wipro.factapp.injection.ConfigPersistent
import com.wipro.factapp.feautres.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/*This is the presenter class which contains api functions or the external source functions which connects
 to the view*/
@ConfigPersistent
class FactActivityPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<FactActivityMVPView>() {


    @SuppressLint("CheckResult")
    fun getFactData(mPreferences: PreferencesHelper) {
        checkViewAttached()
        if (mPreferences.getDataForInt("SWIPE_CONSTANT") == 1) {

            mvpView?.hideProgress()
        } else {

            mvpView?.showProgress()
        }

        dataManager.getTheDataFacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->

                mvpView?.apply {
                    hideProgress()


                    showFactResults(result.rows)

                }


            }, { _ ->

                mvpView?.apply {

                    hideProgress()
                    showError("No Results Found")
                }
            })


    }


}