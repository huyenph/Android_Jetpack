package com.utildev.androidjetpack.presentation.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.data.remote.response.site.SiteItemResponse
import com.utildev.androidjetpack.databinding.ActivityMainBinding
import com.utildev.androidjetpack.presentation.adapter.MenuAdapter
import com.utildev.androidjetpack.presentation.adapter.MyPagerAdapter
import com.utildev.androidjetpack.presentation.base.BaseActivity
import com.utildev.androidjetpack.presentation.base.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), BaseAdapter.AdapterListener {
    private val vm: MainViewModel by viewModel()
    private val menuLm = GridLayoutManager(this, 1)
    private var menuAdapter: MenuAdapter? = null
    private var menus: MutableList<SiteItemResponse> = ArrayList()

    private lateinit var pagerAdapter: MyPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = vm
//        configNavigation(0, elevation = true, scrim = true)
        init()
    }

    override fun onItemClick(`object`: Any, position: Int) {
    }

    override fun onItemLongClick(`object`: Any, position: Int): Boolean {
        return false
    }

    override fun onLoadMore() {
    }

    private fun init() {
//        menuAdapter = MenuAdapter(actMain_rvOption, null, this)
//        actMain_rvOption.run {
//            layoutManager = menuLm
//            setHasFixedSize(true)
//            adapter = menuAdapter
//        }
//        vm.loadMenu()
//        vm.menuLive.observe(this, Observer {
//            if (it != null) {
//                menus.addAll(it)
//                menuAdapter!!.set(menus)
//                menuAdapter!!.isLoading = true
//            }
//        })

        pagerAdapter = MyPagerAdapter(supportFragmentManager)
        actMain_vp.adapter = pagerAdapter
        actMain_tl.setupWithViewPager(actMain_vp)

    }
}
