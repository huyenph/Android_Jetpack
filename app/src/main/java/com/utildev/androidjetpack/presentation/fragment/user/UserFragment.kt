package com.utildev.androidjetpack.presentation.fragment.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.data.remote.response.user.UserItemResponse
import com.utildev.androidjetpack.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import com.utildev.androidjetpack.databinding.FragmentUserBinding
import com.utildev.androidjetpack.presentation.adapter.UserAdapter
import com.utildev.androidjetpack.presentation.base.BaseAdapter
import kotlinx.android.synthetic.main.fragment_user.view.*

class UserFragment: BaseFragment(), BaseAdapter.AdapterListener {
    private val vm: UserViewModel by viewModel()
    private lateinit var mView: View

    private var userLm: GridLayoutManager? = null
    private var userAdapter: UserAdapter? = null
    private var users: ArrayList<UserItemResponse>? = null
    private var siteParam = "stackoverflow"
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.userLive.observe(this, Observer {
            if (it != null) {
                if (it.size == 0) {
                    userAdapter!!.isEndList = true
                    userAdapter!!.notifyDataSetChanged()
                } else {
                    users!!.addAll(it)
                    userAdapter!!.set(users!!)
                    userAdapter!!.isLoading = true
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentUserBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        binding.vm = vm
        mView = binding.root
        init()
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getUser(page, true)
    }

    override fun onItemClick(`object`: Any, position: Int) {}

    override fun onItemLongClick(`object`: Any, position: Int): Boolean {
        return false
    }

    override fun onLoadMore() {
        vm.getUser(++page, false)
    }

    private fun init() {
        page = 1
        users = ArrayList()
        userLm = GridLayoutManager(context, 1)
        userAdapter = UserAdapter(mView.fmUser_rv, userLm!!, this)
        mView.fmUser_rv.run {
            layoutManager = userLm
            adapter = userAdapter
            setHasFixedSize(true)
        }
        mView.fmUser_srl.setOnRefreshListener {
            page = 1
            users!!.clear()
            userAdapter!!.set(users!!)
            vm.getUser(page, true)
            mView.fmUser_srl.isRefreshing = false
        }
    }
}