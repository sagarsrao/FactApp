@file:Suppress("UNCHECKED_CAST")

package com.wipro.factapp.feautres.factmodule

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wipro.factapp.R
import com.wipro.factapp.feautres.base.BaseActivity
import com.wipro.factapp.feautres.factmodule.adapter.FactDataAdapter
import com.wipro.factapp.feautres.factmodule.models.RowsItem
import com.wipro.factapp.feautres.util.NetworkUtil
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.os.Parcelable
import androidx.core.content.ContextCompat
import com.wipro.factapp.data.local.PreferencesHelper
import com.wipro.factapp.feautres.util.CustomScrollListener
import kotlinx.android.synthetic.main.adapter_fact_activity.view.*
import kotlinx.android.synthetic.main.layout_activity_main_additional_fields.*

/*This is the main screen which is responsible for showing facts data from the api*/
class FactActivity : BaseActivity(), FactActivityMVPView {
    override fun showFactResultsForSwipeToRefresh(rows: List<RowsItem?>) {


        viewAdapter = FactDataAdapter(rows as ArrayList<RowsItem>, this, mPreferences)
        (viewAdapter as FactDataAdapter).clear()
        (viewAdapter as FactDataAdapter).addAll(rows)
        swipecontainer.isRefreshing = false


    }


    var mLayoutManager: LinearLayoutManager? = null

    override fun showFactResults(rowsItem: List<RowsItem?>?) {


        if (mPreferences.getDataForInt("SWIPE_CONSTANT") == 1) {

            viewAdapter = FactDataAdapter(rowsItem as ArrayList<RowsItem>, this, mPreferences)
            (viewAdapter as FactDataAdapter).clear()
            (viewAdapter as FactDataAdapter).addAll(rowsItem)
            swipecontainer.isRefreshing = false

        } else {


            viewManager = LinearLayoutManager(this) as RecyclerView.LayoutManager
            viewAdapter = FactDataAdapter(rowsItem as ArrayList<RowsItem>, this, mPreferences)





            rv_fact.apply {

                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
                adapter!!.notifyDataSetChanged()
            }
        }





        rv_fact.addOnScrollListener(object : CustomScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)


            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy < 0) run {

                    val result =
                        recyclerView.findViewHolderForAdapterPosition(mPreferences.getDataForInt("RECYCLER_ADAPTER_POSITION"))
                            ?.itemView?.textView_main_headline?.text.toString()

                    if (result.isNullOrEmpty() or result.equals("null")) {
                        fact_tool_bar.title = "Facts"
                    } else {
                        fact_tool_bar.title =
                            recyclerView.findViewHolderForAdapterPosition(mPreferences.getDataForInt("RECYCLER_ADAPTER_POSITION"))
                                ?.itemView?.textView_main_headline?.text.toString()
                    }


                } else if (dy > 0) {

                    val results =
                        recyclerView.findViewHolderForAdapterPosition(mPreferences.getDataForInt("RECYCLER_ADAPTER_POSITION"))
                            ?.itemView?.textView_main_headline?.text.toString()

                    if (results.isNullOrEmpty() or results.equals("null")) {
                        fact_tool_bar.title = "Facts"
                    } else {
                        fact_tool_bar.title =
                            recyclerView.findViewHolderForAdapterPosition(mPreferences.getDataForInt("RECYCLER_ADAPTER_POSITION"))
                                ?.itemView?.textView_main_headline?.text.toString()
                    }


                }


            }
        })


    }


    @Inject
    lateinit var mPresenter: FactActivityPresenter
    lateinit var mPreferences: PreferencesHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    var mListstate: Parcelable? = null


    override fun layoutId(): Int {

        return R.layout.activity_main
    }

    override fun showProgress() {
        progress_fact.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_fact.visibility = View.GONE
    }

    override fun showError(error: String) {

        Toast.makeText(this@FactActivity, error, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        //set recyclerview position
        if (mListstate != null) {
            mLayoutManager?.onRestoreInstanceState(mListstate)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent().inject(this)
        mPresenter.attachView(this)

        setSupportActionBar(fact_tool_bar)

        mPreferences = PreferencesHelper(applicationContext)


        fact_tool_bar.setTitle("Facts")
        fact_tool_bar.setTitleTextColor(ContextCompat.getColor(this@FactActivity, R.color.white))

        mLayoutManager = LinearLayoutManager(this@FactActivity)

        mPreferences.clear()



        if (NetworkUtil.isNetworkConnected(this@FactActivity)) {
            mPresenter.getFactData()
        } else {
            Toast.makeText(this@FactActivity, "Please check the internet connection", Toast.LENGTH_SHORT).show()
        }

        swipecontainer.setOnRefreshListener {


            if (NetworkUtil.isNetworkConnected(this@FactActivity)) {
                mPreferences.putDataForInt("SWIPE_CONSTANT", 1)

                mPresenter.getFactData()
            } else {
                swipecontainer.isRefreshing = false
                Toast.makeText(this@FactActivity, "Please check the internet connection", Toast.LENGTH_SHORT).show()
            }

        }

        swipecontainer.setColorSchemeResources(
            R.color.colorAccent,
            R.color.colorAccent,
            R.color.colorAccent,
            R.color.colorAccent
        );


    }

    val LIST_STATE_RV = "LIST_STATE_RV"


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.ic_menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mListstate = mLayoutManager?.onSaveInstanceState()
        outState.putParcelable(LIST_STATE_RV, mListstate)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            mListstate = savedInstanceState.getParcelable(LIST_STATE_RV)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.refresh -> {
                if (NetworkUtil.isNetworkConnected(this@FactActivity)) {
                    mPresenter.getFactData()
                } else {
                    Toast.makeText(this@FactActivity, "Please check the internet connection", Toast.LENGTH_SHORT).show()
                }
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
