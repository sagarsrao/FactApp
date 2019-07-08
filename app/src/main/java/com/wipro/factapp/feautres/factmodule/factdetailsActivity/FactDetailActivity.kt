package com.wipro.factapp.feautres.factmodule.factdetailsActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.wipro.factapp.R
import com.wipro.factapp.feautres.base.BaseActivity
import kotlinx.android.synthetic.main.activity_fact_detail.*
import javax.inject.Inject


/*This class is responsible for showing  details things like title , description and image*/
@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class FactDetailActivity : BaseActivity(), FactDetailActivityMVPView {
    @Inject
    lateinit var mPresenter: FactDetailActivityPresenter


    override fun showProgress() {
        progress_fact_details.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_fact_details.visibility = View.GONE
    }

    override fun layoutId(): Int {

        return R.layout.activity_fact_detail
    }

    private var mTitle: String? = null
    private var mdesc: String? = null
    private var mImageHref: String? = null


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("TITLE",mTitle)
        outState.putString("DESC",mdesc)
        outState.putString("IMAGE_REF",mImageHref)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fact_detail)
        activityComponent().inject(this)
        mPresenter.attachView(this)
        setSupportActionBar(fact_tool_bar_details)


        if(savedInstanceState!=null){

            mTitle = savedInstanceState.getString("TITLE")
            mdesc = savedInstanceState.getString("DESC")
            mImageHref = savedInstanceState.getString("IMAGE_REF")

        }
        try {


            if (mTitle.isNullOrEmpty()) {
                tv_facts_title_details.text = "No Title"
                hideProgress()
            } else {
                hideProgress()
                tv_facts_title_details.text = mTitle
            }

            if (mdesc.isNullOrEmpty()) {
                hideProgress()
                textView_desc.text = "No description found"
            } else {
                hideProgress()
                textView_desc.text = mdesc
            }


            Glide.with(this).load(mImageHref).placeholder(R.drawable.ic_photo_black_24dp)
                .error(R.drawable.ic_photo_black_24dp).into(iv_fact_details)

        } catch (e: Exception) {
        }


        showProgress()

        ic_detail_nav_left.setOnClickListener {

            onBackPressed()

        }

        mTitle = intent.extras.getString("fact_title")
        mdesc = intent.extras.getString("fact_description")
        mImageHref = intent.extras.getString("fact_images")

        try {


            if (mTitle.isNullOrEmpty()) {
                tv_facts_title_details.text = "No Title"
                hideProgress()
            } else {
                hideProgress()
                tv_facts_title_details.text = mTitle
            }

            if (mdesc.isNullOrEmpty()) {
                hideProgress()
                textView_desc.text = "No description found"
            } else {
                hideProgress()
                textView_desc.text = mdesc
            }


            Glide.with(this).load(mImageHref).placeholder(R.drawable.ic_photo_black_24dp)
                .error(R.drawable.ic_photo_black_24dp).into(iv_fact_details)

        } catch (e: Exception) {
        }


    }
}
