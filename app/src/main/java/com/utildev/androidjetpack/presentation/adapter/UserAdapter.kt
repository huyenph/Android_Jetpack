package com.utildev.androidjetpack.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utildev.androidjetpack.BR
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.presentation.base.BaseAdapter
import kotlinx.android.synthetic.main.item_user.view.*
import kotlinx.android.synthetic.main.view_loadmore.view.*

class UserAdapter(
    recyclerView: RecyclerView,
    layoutManager: GridLayoutManager,
    adapterListener: AdapterListener
): BaseAdapter(R.layout.item_user, 0, 0, recyclerView, layoutManager, adapterListener) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val item = items[position]
            holder.binding.setVariable(BR.user, item)
            holder.binding.executePendingBindings()
            val v = holder.binding.root
            v.itUser_decorator.visibility = if (position == items.size - 1) View.GONE else View.VISIBLE
        } else if (holder is LoadingViewHolder) {
            holder.binding.root.vLoadMore.visibility = if (isEndList) View.GONE else View.VISIBLE
        }
    }
}