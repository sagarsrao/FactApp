package com.wipro.factapp.feautres.factmodule.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wipro.factapp.R
import com.wipro.factapp.feautres.factmodule.factdetailsActivity.FactDetailActivity
import com.wipro.factapp.feautres.factmodule.models.RowsItem
import kotlinx.android.synthetic.main.adapter_fact_activity.view.*

class FactDataAdapter(private val factsList: ArrayList<RowsItem>, private var context: Context) :
    RecyclerView.Adapter<FactDataAdapter.FactHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_fact_activity, parent, false)
        return FactHolder(inflatedView)
    }

    override fun getItemCount(): Int = factsList.size

    override fun onBindViewHolder(holder: FactHolder, position: Int) {

        val factData = factsList[position]
        holder.bindData(factData, context)


    }


    class FactHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(factData: RowsItem, context: Context) {


            if (factData.title.isNullOrEmpty()) {
                view.textView_main_headline.visibility = View.GONE
            }
            view.textView_main_headline.setText(factData.title)


            if (factData.description.isNullOrEmpty()) {
                view.textView_sub_headline.visibility = View.GONE
            }
            view.textView_sub_headline.setText(factData.description)



            if (factData.imageHref != null) {
                Glide.with(view).load(factData.imageHref).into(view.iv_news_content_pic)
            } else {
                view.iv_news_content_pic.visibility = View.GONE
            }

            if (factData.title.isNullOrEmpty() && factData.description.isNullOrEmpty() && factData.imageHref.isNullOrEmpty()) {

                view.card_all_cases.visibility = View.GONE
            }

            view.card_all_cases.setOnClickListener {

                val factDetailsIntent = Intent(context, FactDetailActivity::class.java)
                factDetailsIntent.putExtra("fact_title", factData.title)
                factDetailsIntent.putExtra("fact_description", factData.description)
                factDetailsIntent.putExtra("fact_images", factData.imageHref)
                context.startActivity(factDetailsIntent)

            }

        }


        companion object {

            private val PHOTO_KEY = "PHOTO"

        }
    }


    fun clear(){
        factsList.clear()
        notifyDataSetChanged()
    }

    fun addAll(factsList: ArrayList<RowsItem>){
        factsList.addAll(factsList)
    }
}