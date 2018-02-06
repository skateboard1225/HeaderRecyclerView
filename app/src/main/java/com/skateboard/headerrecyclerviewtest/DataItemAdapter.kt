package com.skateboard.headerrecyclerviewtest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by skateboard on 2018/2/6.
 */
class DataItemAdapter(private var dataList: MutableList<String>) : RecyclerView.Adapter<ItemViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder
    {
        return ItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_layout, parent, false))
    }

    override fun getItemCount(): Int
    {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int)
    {
        holder?.itemText?.text = dataList[position]

    }
}