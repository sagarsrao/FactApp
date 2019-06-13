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

class FactActivity : BaseActivity(), FactActivityMVPView {
    override fun showFactResults(rowsItem: List<RowsItem?>?) {

        Log.d("rowsItem", "" + rowsItem)

        viewManager = LinearLayoutManager(this)
        viewAdapter = FactDataAdapter(rowsItem as ArrayList<RowsItem>)


        rv_fact.apply {

            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            adapter!!.notifyDataSetChanged()
        }


    }


    @Inject
    lateinit var mPresenter: FactActivityPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent().inject(this)
        mPresenter.attachView(this)

        setSupportActionBar(fact_tool_bar)


        if (NetworkUtil.isNetworkConnected(this@FactActivity)) {
            mPresenter.getFactData()
        } else {
            Toast.makeText(this@FactActivity, "Please check the internet connection", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.ic_menu_main, menu)
        return super.onCreateOptionsMenu(menu)
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
