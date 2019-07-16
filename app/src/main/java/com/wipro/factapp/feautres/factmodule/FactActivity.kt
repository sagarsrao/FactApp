@file:Suppress("UNCHECKED_CAST")

package com.wipro.factapp.feautres.factmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wipro.factapp.R
import com.wipro.factapp.feautres.base.BaseActivity
import com.wipro.factapp.feautres.factmodule.adapter.FactDataAdapter
import com.wipro.factapp.feautres.factmodule.models.RowsItem
import com.wipro.factapp.feautres.util.NetworkUtil
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.R.attr.top
import android.os.Parcelable
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.layout_activity_main_additional_fields.*

/*This is the main screen which is responsible for showing facts data from the api*/
class FactActivity : BaseActivity(), FactActivityMVPView {
    override fun showFactResultsForSwipeToRefresh(rows: List<RowsItem?>) {


        viewAdapter = FactDataAdapter(rows as ArrayList<RowsItem>, this)
        (viewAdapter as FactDataAdapter).clear()
        (viewAdapter as FactDataAdapter).addAll(rows)
        swipecontainer.isRefreshing = false


    }


    var mLayoutManager: LinearLayoutManager? = null

    override fun showFactResults(rowsItem: List<RowsItem?>?) {


        viewManager = LinearLayoutManager(this) as RecyclerView.LayoutManager
        viewAdapter = FactDataAdapter(rowsItem as ArrayList<RowsItem>, this)





        rv_fact.apply {

            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            adapter!!.notifyDataSetChanged()
        }


        /*  (viewAdapter as FactDataAdapter).clear()
          (viewAdapter as FactDataAdapter).addAll(rowsItem as ArrayList<RowsItem>)
          swipecontainer.isRefreshing = false*/

    }


    @Inject
    lateinit var mPresenter: FactActivityPresenter

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


        fact_tool_bar.setTitle("Facts")
        fact_tool_bar.setTitleTextColor(ContextCompat.getColor(this@FactActivity, R.color.white))

        mLayoutManager = LinearLayoutManager(this@FactActivity)



        if (NetworkUtil.isNetworkConnected(this@FactActivity)) {
            mPresenter.getFactData()
        } else {
            Toast.makeText(this@FactActivity, "Please check the internet connection", Toast.LENGTH_SHORT).show()
        }

        swipecontainer.setOnRefreshListener {


            if (NetworkUtil.isNetworkConnected(this@FactActivity)) {
                mPresenter.getFactDataForSwipeToRefresh()
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
