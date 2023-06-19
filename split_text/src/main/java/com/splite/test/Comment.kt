package com.splite.test

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

object Comment {

    fun <T : Activity> Class<T>.start(context: Context?) {
        context?.apply {
            startActivity(Intent(this, this@start))
        }
    }

    fun oneAdpter(list: List<String>, layoutInflater: LayoutInflater): RecyclerView.Adapter<*> {

        return object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                return object : RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.item_top, parent, false)){}
            }

            override fun getItemCount() = list.size
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            }

        }

    }

    fun twoAdpter(list: List<String>, layoutInflater: LayoutInflater): RecyclerView.Adapter<*> {

        return object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                return object : RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.item_top, parent, false)){}
            }

            override fun getItemCount() = list.size
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            }

        }

    }


}