package com.utildev.androidjetpack.presentation.fragment.user

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.utildev.androidjetpack.BR
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.data.remote.response.user.UserItemResponse
import com.utildev.androidjetpack.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import com.utildev.androidjetpack.databinding.FragmentUserBinding
import com.utildev.androidjetpack.presentation.activity.MainActivity
import com.utildev.androidjetpack.presentation.adapter.MyPagerAdapter
import com.utildev.androidjetpack.presentation.adapter.UserAdapter
import com.utildev.androidjetpack.presentation.base.BaseAdapter
import kotlinx.android.synthetic.main.fragment_user.view.*

@Suppress("UNCHECKED_CAST", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class UserFragment : BaseFragment<FragmentUserBinding, UserViewModel>(), BaseAdapter.AdapterListener,
    MyPagerAdapter.FragmentUpdateListener {

    private val vm: UserViewModel by viewModel()

    private lateinit var userLm: GridLayoutManager
    private lateinit var userAdapter: UserAdapter
    private val users: ArrayList<UserItemResponse> = ArrayList()

    private var page = 0
    private var prePos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.userLive.observe(this, Observer {
            if (it != null) {
                if (it.size == 0) {
                    userAdapter.isEndList = true
                    userAdapter.notifyDataSetChanged()
                } else {
                    users.addAll(it)
                    userAdapter.set(users)
                    userAdapter.isLoading = true
                }
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.fragment_user

    override fun getBindingVariable(): Int? = BR.vm

    override fun getViewModel(): UserViewModel? = vm

    override fun init(view: View) {
        userLm = GridLayoutManager(context, 1)
        userAdapter = UserAdapter(view.fmUser_rv, userLm, this)

        view.fmUser_rv.run {
            layoutManager = userLm
            adapter = userAdapter
            setHasFixedSize(true)
        }

        view.fmUser_srl.setOnRefreshListener {
            page = 1
            users.clear()
            userAdapter.set(users)
            vm.getUser((activity as MainActivity).siteParam, page, true)
            view.fmUser_srl.isRefreshing = false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val position = userLm.findFirstVisibleItemPosition()
        outState.putString("user_site", (activity as MainActivity).siteParam)
        outState.putInt("user_position", position)
        outState.putInt("user_page", page + 1)
        outState.putSerializable("user", users)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            val preSite = savedInstanceState.getString("user_site")
            if (preSite == (activity as MainActivity).siteParam) {
                prePos = savedInstanceState.getInt("user_position")
                page = savedInstanceState.getInt("user_page")
                users.addAll(savedInstanceState.getSerializable("user") as ArrayList<UserItemResponse>)
                userAdapter.set(users)
                view!!.fmUser_rv.smoothScrollToPosition(prePos)
            } else {
                prePos = 0
                page = 1
            }
        } else {
            prePos = 0
            page = 1
        }
        vm.getUser((activity as MainActivity).siteParam, page, true)
    }

    override fun onItemClick(`object`: Any, position: Int) {}

    override fun onItemLongClick(`object`: Any, position: Int): Boolean {
        return false
    }

    override fun onLoadMore() {
        vm.getUser((activity as MainActivity).siteParam, ++page, false)
    }

    override fun onUpdate() {
        page = 1
        users.clear()
        userAdapter.set(users)
        vm.getUser((activity as MainActivity).siteParam, page, true)
        view!!.fmUser_srl.isRefreshing = false
    }
}