package com.wipro.factapp.injection.component

import com.wipro.factapp.injection.PerActivity
import com.wipro.factapp.injection.module.ActivityModule
import dagger.Subcomponent


@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
}