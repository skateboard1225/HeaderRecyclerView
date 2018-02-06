package com.skateboard.headerrecyclerviewtest

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

/**
 * Created by skateboard on 2018/2/6.
 */
class ItemViewHolder(itemView:View): RecyclerView.ViewHolder(itemView)
{
    var itemText:TextView = itemView.findViewById(R.id.itemText)


}