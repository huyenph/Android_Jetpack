package com.utildev.androidjetpack.presentation.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utildev.androidjetpack.R

abstract class BaseAdapter(
    private val mainRes: Int,
    private val headerRes: Int,
    private val footerRes: Int,
    recyclerView: RecyclerView,
    private val layoutManager: GridLayoutManager?,
    private val adapterListener: AdapterListener?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: MutableList<Any> = ArrayList()
    var isLoading = true
    var isEndList = false

    private var mItemCount = 0
    private var isScroll = false

    companion object {
        const val TYPE_BLANK = 666
        const val TYPE_FOOTER = 777
        const val TYPE_HEADER = 888
        const val TYPE_LOADING = 999
    }

    init {
        if (layoutManager != null && adapterListener != null) {
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                var visibleItemCount: Int = 0
                var totalItemCount: Int = 0
                var firstVisibleItem: Int = 0
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        isScroll = true
                        visibleItemCount = layoutManager.childCount
                        totalItemCount = layoutManager.itemCount
                        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                        if (isLoading) {
                            if (visibleItemCount + firstVisibleItem >= totalItemCount) {
                                adapterListener.onLoadMore()
                                isLoading = false
                            }
                        }
                    } else {
                        isScroll = false
                    }
                }
            })
        }
    }

    override fun getItemCount(): Int {
        mItemCount = when {
            headerRes != 0 && footerRes != 0 ->
                if (layoutManager != null) items.size + 3 else items.size + 2
            headerRes != 0 || footerRes != 0 ->
                if (layoutManager != null) items.size + 2 else items.size + 1
            else ->
                if (layoutManager != null) items.size + 1 else items.size
        }
        return mItemCount
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager != null) {
            when (mItemCount) {
                3 -> {
                    when (position) {
                        0 -> when {
                            headerRes != 0 -> TYPE_HEADER
                            footerRes != 0 -> TYPE_FOOTER
                            else -> position
                        }
                        1 -> if (footerRes != 0) TYPE_FOOTER else position
                        else -> TYPE_BLANK
                    }
                }
                2 -> {
                    when (position) {
                        0 -> if (headerRes != 0) TYPE_HEADER else if (footerRes != 0) TYPE_FOOTER else position
                        else -> TYPE_BLANK
                    }
                }
                1 -> TYPE_BLANK
                else -> {
                    when (position) {
                        0 -> if (headerRes != 0) TYPE_HEADER else position
                        mItemCount - 1 -> if (isScroll) TYPE_LOADING else TYPE_BLANK
                        mItemCount - 2 -> if (footerRes != 0) TYPE_FOOTER else position
                        else -> position
                    }
                }
            }
        } else {
            when (mItemCount) {
                2 -> {
                    if (position == 0) {
                        when {
                            headerRes != 0 -> TYPE_HEADER
                            footerRes != 0 -> TYPE_FOOTER
                            else -> position
                        }
                    } else {
                        if (footerRes != 0) TYPE_FOOTER else position
                    }
                }
                1 -> if (headerRes != 0) TYPE_HEADER else if (footerRes != 0) TYPE_FOOTER else position
                else -> {
                    when (position) {
                        0 -> if (headerRes != 0) TYPE_HEADER else position
                        mItemCount - 1 -> if (footerRes != 0) TYPE_FOOTER else position
                        else -> position
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.context), headerRes, viewGroup, false
                )
            )
            TYPE_FOOTER -> FooterViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.context), footerRes, viewGroup, false
                )
            )
            TYPE_LOADING -> LoadingViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.context), R.layout.view_loadmore, viewGroup, false
                )
            )
            TYPE_BLANK -> BlankViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.context), R.layout.view_blank, viewGroup, false
                )
            )
            else -> ItemViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(viewGroup.context), mainRes, viewGroup, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}

    private fun addAll(list: List<Any>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun add(any: Any) {
        items.add(any)
        notifyDataSetChanged()
    }

    fun set(list: List<Any>) {
        items.clear()
        addAll(list)
    }

    fun remove(position: Int) {
        items.removeAt(position)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    interface AdapterListener {
        fun onItemClick(`object`: Any, position: Int)
        fun onItemLongClick(`object`: Any, position: Int): Boolean
        fun onLoadMore()
    }

    class HeaderViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
    class FooterViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
    class ItemViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
    class LoadingViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
    class BlankViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}