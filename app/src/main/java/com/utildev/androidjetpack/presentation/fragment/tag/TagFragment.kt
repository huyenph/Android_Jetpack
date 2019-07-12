package com.utildev.androidjetpack.presentation.fragment.tag

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.utildev.androidjetpack.BR
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.data.remote.response.tag.TagItemResponse
import com.utildev.androidjetpack.presentation.base.BaseFragment
import com.utildev.androidjetpack.databinding.FragmentTagBinding
import com.utildev.androidjetpack.presentation.activity.MainActivity
import com.utildev.androidjetpack.presentation.adapter.MyPagerAdapter
import com.utildev.androidjetpack.presentation.adapter.TagAdapter
import com.utildev.androidjetpack.presentation.base.BaseAdapter
import kotlinx.android.synthetic.main.fragment_tag.view.*
import org.koin.android.viewmodel.ext.android.viewModel

@Suppress("UNCHECKED_CAST", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TagFragment : BaseFragment<FragmentTagBinding, TagViewModel>(), BaseAdapter.AdapterListener,
    MyPagerAdapter.FragmentUpdateListener {

    private val vm: TagViewModel by viewModel()

    private lateinit var tagLm: GridLayoutManager
    private lateinit var tagAdapter: TagAdapter
    private val tags: ArrayList<TagItemResponse> = ArrayList()

    private var page = 0
    private var prePos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.tagLive.observe(this, Observer {
            if (it != null) {
                if (it.size == 0) {
                    tagAdapter.isEndList = true
                    tagAdapter.notifyDataSetChanged()
                } else {
                    tags.addAll(it)
                    tagAdapter.set(tags)
                    tagAdapter.isLoading = true
                }
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_tag

    override fun getBindingVariable(): Int? = BR.vm

    override fun getViewModel(): TagViewModel? = vm

    override fun init(view: View) {
        tagLm = GridLayoutManager(context, 1)
        tagAdapter = TagAdapter(view.fmTag_rv, tagLm, this)

        view.fmTag_rv.run {
            layoutManager = tagLm
            adapter = tagAdapter
            setHasFixedSize(true)
        }
        view.fmTag_srl.setOnRefreshListener {
            view.fmTag_srl.isRefreshing = false
            page = 1
            tags.clear()
            tagAdapter.set(tags)
            vm.getTag((activity as MainActivity).siteParam, page, true)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val position = tagLm.findFirstVisibleItemPosition()
        outState.putString("tag_site", (activity as MainActivity).siteParam)
        outState.putInt("tag_position", position)
        outState.putInt("tag_page", page + 1)
        outState.putSerializable("tag", tags)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            val preSite = savedInstanceState.getString("tag_site")
            if (preSite == (activity as MainActivity).siteParam) {
                prePos = savedInstanceState.getInt("tag_position")
                page = savedInstanceState.getInt("tag_page")
                tags.addAll(savedInstanceState.getSerializable("tag") as ArrayList<TagItemResponse>)
                tagAdapter.set(tags)
                view!!.fmTag_rv.smoothScrollToPosition(prePos)
            } else {
                prePos = 0
                page = 1
            }
        } else {
            prePos = 0
            page = 1
        }
        vm.getTag((activity as MainActivity).siteParam, page, true)
    }

    override fun onItemClick(`object`: Any, position: Int) {}

    override fun onItemLongClick(`object`: Any, position: Int): Boolean {
        return false
    }

    override fun onLoadMore() {
        vm.getTag((activity as MainActivity).siteParam, ++page, false)
    }

    override fun onUpdate() {
        if (activity is MainActivity) {
            page = 1
            tags.clear()
            tagAdapter.set(tags)
            vm.getTag((activity as MainActivity).siteParam, page, true)
            view!!.fmTag_srl.isRefreshing = false
        }
    }
}