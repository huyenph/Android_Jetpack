package com.utildev.androidjetpack.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utildev.androidjetpack.BR
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.presentation.base.BaseAdapter
import kotlinx.android.synthetic.main.item_menu.view.*
import kotlinx.android.synthetic.main.view_footer_menu.view.*
import kotlinx.android.synthetic.main.view_loadmore.view.*

class MenuAdapter(
    recyclerView: RecyclerView,
    layoutManager: GridLayoutManager?,
    private val adapterListener: AdapterListener?
) : BaseAdapter(R.layout.item_menu, 0, R.layout.view_footer_menu, recyclerView, layoutManager, adapterListener) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
//                val item: Any = items[if (headerRes != 0) position - 1 else position]
                val item: Any = items[position]
                holder.binding.setVariable(BR.menu, item)
                holder.binding.executePendingBindings()
                val v = holder.binding.root
                v.itMenu_clContainer.setOnClickListener {
                    adapterListener?.onItemClick(item, position)
                }
            }
            is FooterViewHolder -> {
                val v = holder.binding.root
                v.vFooterMenu_clContainer.setOnClickListener {
                    adapterListener?.onItemClick("footer", position)
                }
            }
            is HeaderViewHolder -> {

            }
            is LoadingViewHolder -> {
                holder.binding.root.vLoadMore.visibility = if (isEndList) View.GONE else View.VISIBLE
            }
            is BlankViewHolder -> {

            }
        }
    }
}