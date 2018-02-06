package com.skateboard.headerrecyclerviewtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.activity_main.*
import routertest.skateboard.com.headerrecyclerview.AdapterWraper

class MainActivity : AppCompatActivity()
{

    private  var dataList:MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generateData()
        recyclerList.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        val adapter=AdapterWraper(DataItemAdapter(dataList))
        adapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.head_view_layout,null))
        adapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.head_view_layout,null))
        adapter.addFootView(LayoutInflater.from(this).inflate(R.layout.head_view_layout,null))
        adapter.addFootView(LayoutInflater.from(this).inflate(R.layout.head_view_layout,null))
        recyclerList.adapter=adapter
    }

    private fun generateData()
    {
        (0..100)
                .map { "item $it" }
                .forEach { dataList.add(it) }


    }


}
