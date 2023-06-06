package com.example.myapplication

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class DataBindingViewHolder<D:ViewDataBinding>(var dataBinding:D):ViewHolder(dataBinding.root) {

}