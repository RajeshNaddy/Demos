package com.rajesh.recyclerviewpagination

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rajesh.recyclerviewpagination.pagination.PaginationAdapter

/**
 * Created by Rajesh on 25/06/2019
 **/

class DataAdapter(context: Context) : PaginationAdapter<Data>(context) {

    private val datas: MutableList<Data> = ArrayList()

    override fun addLoadingFooter() {

        isLoadingAdded = true
        add(Data())
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val data = datas.get(position)

        when (getItemViewType(position)) {
            ITEM -> {
                val dataVH = holder as DataVH

                dataVH.title.text = data.title

            }
            LOADING -> {
                //  Do nothing
            }
        }

    }

    override fun getList(): MutableList<Data> {
        return datas
    }

    override fun getViewHolder(parent: ViewGroup?, inflater: LayoutInflater?): RecyclerView.ViewHolder {

        val viewHolder: RecyclerView.ViewHolder
        val v1 = inflater?.inflate(R.layout.item_view, parent, false)
        viewHolder = DataVH(v1!!)
        return viewHolder
    }

    inner class DataVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tvTitle)

    }
}