package com.wipro.factapp.feautres.factmodule.factdetailsActivity

import android.annotation.SuppressLint
import com.wipro.factapp.data.DataManager
import com.wipro.factapp.injection.ConfigPersistent
import com.wipro.factapp.feautres.base.BasePresenter
import com.wipro.factapp.feautres.factmodule.models.RowsItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class FactDetailActivityPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<FactDetailActivityMVPView>() {



}