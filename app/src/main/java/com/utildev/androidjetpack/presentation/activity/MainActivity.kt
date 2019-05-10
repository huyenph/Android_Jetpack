package com.utildev.androidjetpack.presentation.activity

import android.os.Bundle
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.presentation.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configNavigation(2, elevation = true, scrim = true)
    }
}
