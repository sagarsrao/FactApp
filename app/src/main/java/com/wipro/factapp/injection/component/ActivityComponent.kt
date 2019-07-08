package com.wipro.factapp.injection.component

import com.wipro.factapp.feautres.base.BaseActivity
import com.wipro.factapp.feautres.factmodule.FactActivity
import com.wipro.factapp.feautres.factmodule.factdetailsActivity.FactDetailActivity
import com.wipro.factapp.injection.PerActivity
import com.wipro.factapp.injection.module.ActivityModule
import dagger.Subcomponent




/*The below contains all the activity level components*/
@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(baseActivity: BaseActivity)

    fun inject(factActivity: FactActivity)

    fun inject(factDetailActivity: FactDetailActivity)
}

