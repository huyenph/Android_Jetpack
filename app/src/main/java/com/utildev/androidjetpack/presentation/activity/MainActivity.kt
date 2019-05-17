package com.utildev.androidjetpack.presentation.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.view.MotionEvent
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.utildev.androidjetpack.BR
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.data.remote.response.site.SiteItemResponse
import com.utildev.androidjetpack.databinding.ActivityMainBinding
import com.utildev.androidjetpack.presentation.adapter.MenuAdapter
import com.utildev.androidjetpack.presentation.adapter.MyPagerAdapter
import com.utildev.androidjetpack.presentation.base.BaseActivity
import com.utildev.androidjetpack.presentation.base.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), BaseAdapter.AdapterListener {
    private val vm: MainViewModel by viewModel()
    private val menuLm = GridLayoutManager(this, 1)
    private var menuAdapter: MenuAdapter? = null
    private var menus: MutableList<SiteItemResponse> = ArrayList()

    private lateinit var pagerAdapter: MyPagerAdapter

    var siteParam = "stackoverflow"

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getBindingVariable(): Int = BR.vm

    override fun getViewModel(): MainViewModel? = vm

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun init() {
        configNavigation(0, elevation = true, scrim = true)
        menuAdapter = MenuAdapter(actMain_rvOption, null, this)
        actMain_rvOption.run {
            layoutManager = menuLm
            setHasFixedSize(true)
            adapter = menuAdapter
        }
        vm.loadMenu()
        vm.menuLive.observe(this, Observer {
            if (it != null) {
                menus.addAll(it)
                menuAdapter!!.set(menus)
                menuAdapter!!.isLoading = true
            }
        })

        pagerAdapter = MyPagerAdapter(supportFragmentManager)
        actMain_vp.adapter = pagerAdapter
        actMain_tl.setupWithViewPager(actMain_vp)

        actMain_ivNav.setOnClickListener {
            actMain_dl.openDrawer(GravityCompat.START)
        }

        actMain_tvTitle.text = getString(R.string.stack_overflow)

        val tab1 = (actMain_tl.getChildAt(0) as ViewGroup).getChildAt(0)
        tab1.setOnTouchListener { _, event ->
            if (event!!.action == MotionEvent.ACTION_UP) {
                reveal(0, event.rawX, event.rawY)
            }
            false
        }

        val tab2 = (actMain_tl.getChildAt(0) as ViewGroup).getChildAt(1)
        tab2.setOnTouchListener { _, event ->
            if (event!!.action == MotionEvent.ACTION_UP) {
                reveal(1, event.rawX, event.rawY)
            }
            false
        }

        val tab3 = (actMain_tl.getChildAt(0) as ViewGroup).getChildAt(2)
        tab3.setOnTouchListener { _, event ->
            if (event!!.action == MotionEvent.ACTION_UP) {
                reveal(2, event.rawX, event.rawY)
            }
            false
        }
    }

    override fun onItemClick(`object`: Any, position: Int) {
        if (`object` is SiteItemResponse) {
            siteParam = `object`.apiSiteParameter.toString()
            actMain_tvTitle.text = `object`.name
            pagerAdapter.notifyDataSetChanged()
        }
        actMain_dl.closeDrawer(GravityCompat.START)
    }

    override fun onItemLongClick(`object`: Any, position: Int): Boolean {
        return false
    }

    override fun onLoadMore() {
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun reveal(position: Int, x: Float, y: Float) {
        actMain_reveal.visibility = View.VISIBLE
        val revealX = actMain_reveal.width
        val revealY = actMain_reveal.height
        val radius = Math.max(revealX, revealY) * 1.2f
        val reveal = ViewAnimationUtils.createCircularReveal(actMain_reveal, x.toInt(), y.toInt(), 0f, radius)

        reveal.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                actMain_bg.setBackgroundColor(
                    ResourcesCompat.getColor(
                        resources, when (position) {
                            0 -> R.color.colorPrimary
                            1 -> R.color.blue
                            else -> R.color.colorAccent
                        }, theme
                    )
                )
                actMain_reveal.visibility = View.INVISIBLE
            }
        })

        actMain_reveal.setBackgroundColor(
            ResourcesCompat.getColor(
                resources, when (position) {
                    0 -> R.color.colorPrimary
                    1 -> R.color.blue
                    else -> R.color.colorAccent
                }, theme
            )
        )

        reveal.start()
    }
}
