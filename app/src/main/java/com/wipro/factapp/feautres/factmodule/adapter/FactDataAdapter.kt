package com.wipro.factapp.feautres.factmodule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.wipro.factapp.R
import com.wipro.factapp.feautres.factmodule.models.RowsItem
import kotlinx.android.synthetic.main.adapter_fact_activity.view.*

class FactDataAdapter(private val factsList: ArrayList<RowsItem>) : RecyclerView.Adapter<FactDataAdapter.FactHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_fact_activity, parent, false)
        return FactHolder(inflatedView)
    }

    override fun getItemCount(): Int = factsList.size

    override fun onBindViewHolder(holder: FactHolder, position: Int) {

        val factData = factsList[position]
        holder.bindData(factData)


    }


    class FactHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(factData: RowsItem) {

            view.textView_main_headline.setText(factData.title)

            view.textView_sub_headline.setText(factData.description)

            if (factData.imageHref != null) {
                Glide.with(view).load(factData.imageHref).into(view.iv_news_content_pic)
            }


        }


        companion object {

            private val PHOTO_KEY = "PHOTO"

        }
    }

}