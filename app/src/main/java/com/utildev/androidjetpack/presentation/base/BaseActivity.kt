package com.utildev.androidjetpack.presentation.base

import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import com.utildev.androidjetpack.R

abstract class BaseActivity: AppCompatActivity() {
    fun configNavigation(type: Int, elevation: Boolean, scrim: Boolean) {
        val drawer: DrawerLayout = findViewById(R.id.actMain_dl)
        val content: FrameLayout = findViewById(R.id.fmContainer)
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
}