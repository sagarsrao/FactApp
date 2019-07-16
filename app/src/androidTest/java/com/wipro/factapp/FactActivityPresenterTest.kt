package com.wipro.factapp

import com.wipro.factapp.data.DataManager
import com.wipro.factapp.data.FactRestServiceApi
import com.wipro.factapp.feautres.factmodule.FactActivityMVPView
import com.wipro.factapp.feautres.factmodule.FactActivityPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import javax.inject.Inject

class FactActivityPresenterTest {


    @Inject
    lateinit var mPresenter: FactActivityPresenter

    @Inject
    lateinit var mView: FactActivityMVPView

    @Inject
    lateinit var mDataManager: DataManager

    @Inject
    lateinit var factApi: FactRestServiceApi


    @Before
    fun setUp() {


        mDataManager = Mockito.mock(mDataManager::class.java)
        factApi = Mockito.mock(FactRestServiceApi::class.java)
        mView = Mockito.mock(FactActivityMVPView::class.java)
        mPresenter = FactActivityPresenter(mDataManager)

    }

    @Test
    fun testSuccessAndFailureFactResults() {

        Mockito.verify(mPresenter.getFactData())

    }


}