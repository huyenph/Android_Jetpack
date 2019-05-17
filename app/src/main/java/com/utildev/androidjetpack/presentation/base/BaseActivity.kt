package com.utildev.androidjetpack.presentation.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.drawerlayout.widget.DrawerLayout
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.common.extensions.isNetworkAvailable
import com.utildev.androidjetpack.presentation.fragment.other.NotConnectionFragment

abstract class BaseActivity<T: ViewDataBinding, V: BaseViewModel> : AppCompatActivity() {
    private lateinit var binding: T

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getBindingVariable(): Int

    abstract fun getViewModel(): V?

    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        if (getViewModel() != null) {
            binding.setVariable(getBindingVariable(), getViewModel())
            binding.executePendingBindings()
        }
        init()
    }

    fun configNavigation(type: Int, elevation: Boolean, scrim: Boolean) {
        val drawer: DrawerLayout = findViewById(R.id.actMain_dl)
        val content: CoordinatorLayout = findViewById(R.id.fmContainer)
        if (!scrim) drawer.setScrimColor(Color.TRANSPARENT)
        if (!elevation) drawer.drawerElevation = 0f
        val toggle =
            object : ActionBarDrawerToggle(this, drawer, R.string.open_navigation, R.string.close_navigation) {
                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    super.onDrawerSlide(drawerView, slideOffset)
                    val slideX = drawerView.width * slideOffset
                    when (type) {
                        // Default
                        0 -> {
                        }
                        // Slide content
                        1 -> {
                            content.translationX = slideX
                        }
                        // Slide + scale full screen
                        2 -> {
                            content.translationX = slideX
                            content.scaleX = 1 - slideOffset
                            content.scaleY = 1 - slideOffset
                        }
                        // Slide + scale 1
                        3 -> {
                            content.translationX = slideX
                            content.scaleX = 1 - (slideOffset / 8f)
                            content.scaleY = 1 - (slideOffset / 8f)
                        }
                    }
                }
            }
        drawer.addDrawerListener(toggle)
    }

    fun configToolbarMain(view: View, title: String?) {
        val drawer: DrawerLayout = findViewById(R.id.actMain_dl)
        val ivNav: ImageView = view.findViewById(R.id.tbMain_ivNav)
        val tvTitle: TextView = view.findViewById(R.id.tbMain_tvTitle)
        tvTitle.text = title
        ivNav.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }
    }

    fun configToolbar(view: View, title: String?, listener: BackStackListener?) {
        val ivBack: ImageView = view.findViewById(R.id.tb_ivBack)
        val tvTitle: TextView = view.findViewById(R.id.tb_tvTitle)
        tvTitle.text = title
        ivBack.setOnClickListener {
            if (listener != null) {
                listener.onBack()
            } else {
                clearStack()
            }
        }
    }

    private fun transactionFragment(
        fragment: BaseFragment,
        replace: Boolean,
        addToBackStack: Boolean,
        animation: Boolean
    ) {
        val fmTransaction = supportFragmentManager.beginTransaction()
        if (animation) {
            fmTransaction.setCustomAnimations(
                R.anim.activity_new_in,
                R.anim.activity_old_out,
                R.anim.activity_old_in,
                R.anim.activity_new_out
            )
        } else {
            fmTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        if (replace) {
            fmTransaction.replace(R.id.fmContainer, fragment, fragment::class.java.simpleName)
        } else {
            val currentFm = supportFragmentManager.findFragmentById(R.id.fmContainer) as BaseFragment?
            if (currentFm != null) {
                fmTransaction.hide(currentFm)
            }
            fmTransaction.add(R.id.fmContainer, fragment, fragment::class.java.simpleName)
        }
        if (addToBackStack) {
            fmTransaction.addToBackStack(fragment::class.java.simpleName)
        }
        fmTransaction.commit()
    }

    fun replaceFragment(fragment: BaseFragment, addToBackStack: Boolean, animation: Boolean) {
        transactionFragment(
            if (isNetworkAvailable(this)) fragment else NotConnectionFragment(),
            true,
            addToBackStack,
            animation
        )
    }

    fun addFragment(fragment: BaseFragment, addToBackStack: Boolean, animation: Boolean) {
        transactionFragment(
            if (isNetworkAvailable(this)) fragment else NotConnectionFragment(),
            false,
            addToBackStack,
            animation
        )
    }

    fun clearAllStack() {
        val fmCount = supportFragmentManager.backStackEntryCount
        for (i in 0..fmCount) {
            supportFragmentManager.popBackStack()
        }
    }

    fun clearStack() {
        supportFragmentManager.popBackStack()
    }

    interface BackStackListener {
        fun onBack()
    }
}