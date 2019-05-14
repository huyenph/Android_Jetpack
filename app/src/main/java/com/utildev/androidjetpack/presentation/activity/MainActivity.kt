package com.utildev.androidjetpack.presentation.activity

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
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

        actMain_ivNav.setOnClickListener {
            actMain_dl.openDrawer(GravityCompat.START)
        }

        actMain_tvTitle.text = "Stack Overflow"

        actMain_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    when(position) {
                        0 -> {
                            val colorFrom = ContextCompat.getColor(this@MainActivity, R.color.blue)
                            val colorTo = ContextCompat.getColor(this@MainActivity, R.color.colorPrimary)
                            val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
                            colorAnimation.duration = 500
                            colorAnimation.addUpdateListener { animation ->
                                window.statusBarColor = animation!!.animatedValue as Int
                                actMain_cl.setBackgroundColor(animation.animatedValue as Int)
                            }
                            colorAnimation.start()
//                            window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.colorPrimaryDark)
//                            actMain_cl.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorPrimary))
//                            = ContextCompat.getDrawable(this@MainActivity, R.color.colorPrimary)
                        }
                        1 -> {
                            window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.blueDark)
                            actMain_cl.background = ContextCompat.getDrawable(this@MainActivity, R.color.blue)
                        }
                        2 -> {
                            window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.colorAccentDark)
                            actMain_cl.background = ContextCompat.getDrawable(this@MainActivity, R.color.colorAccent)
                        }
                    }
                }
            }
        })
    }
}
