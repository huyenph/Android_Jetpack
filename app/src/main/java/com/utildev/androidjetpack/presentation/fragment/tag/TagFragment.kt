package com.utildev.androidjetpack.presentation.fragment.tag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.data.remote.response.tag.TagItemResponse
import com.utildev.androidjetpack.presentation.base.BaseFragment
import com.utildev.androidjetpack.databinding.FragmentTagBinding
import com.utildev.androidjetpack.presentation.adapter.TagAdapter
import com.utildev.androidjetpack.presentation.base.BaseAdapter
import kotlinx.android.synthetic.main.fragment_tag.view.*
import org.koin.android.viewmodel.ext.android.viewModel

@Suppress("UNCHECKED_CAST")
class TagFragment: BaseFragment(), BaseAdapter.AdapterListener {
    private val vm: TagViewModel by viewModel()
    private lateinit var mView: View

    private var tagLm: GridLayoutManager? = null
    private var tagAdapter: TagAdapter? = null
    private var tags: ArrayList<TagItemResponse>? = null
    private var page = 0
    private var prePos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.tagLive.observe(this, Observer {
            if (it != null) {
                if (it.size == 0) {
                    tagAdapter!!.isEndList = true
                    tagAdapter!!.notifyDataSetChanged()
                } else {
                    tags!!.addAll(it)
                    tagAdapter!!.set(tags!!)
                    tagAdapter!!.isLoading = true
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentTagBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_tag, container, false)
        binding.vm = vm
        mView = binding.root
        init()
        return mView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val position = tagLm!!.findFirstVisibleItemPosition()
        outState.putInt("tag_position", position)
        outState.putInt("tag_page", page + 1)
        outState.putSerializable("tag", tags)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            prePos = savedInstanceState.getInt("tag_position")
            page = savedInstanceState.getInt("tag_page")
            tags!!.addAll(savedInstanceState.getSerializable("tag") as ArrayList<TagItemResponse>)
            tagAdapter!!.set(tags!!)
            mView.fmTag_rv.smoothScrollToPosition(prePos)
        } else {
            prePos = 0
            page = 1
        }
        vm.getTag(page, true)
    }

    override fun onItemClick(`object`: Any, position: Int) {}

    override fun onItemLongClick(`object`: Any, position: Int): Boolean {
        return false
    }

    override fun onLoadMore() {
        vm.getTag(++page, false)
    }

    private fun init() {
        tags = ArrayList()
        tagLm = GridLayoutManager(context, 1)
        tagAdapter = TagAdapter(mView.fmTag_rv, tagLm!!, this)
        mView.fmTag_rv.run {
            layoutManager = tagLm
            adapter = tagAdapter
            setHasFixedSize(true)
        }
        mView.fmTag_srl.setOnRefreshListener {
            page = 1
            tags!!.clear()
            tagAdapter!!.set(tags!!)
            vm.getTag(page, true)
            mView.fmTag_srl.isRefreshing = false
        }
    }
}