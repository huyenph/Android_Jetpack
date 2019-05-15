package com.utildev.androidjetpack.presentation.fragment.other

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.presentation.base.BaseFragment

class SettingFragment: BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_setting, container, false)
        configToolbar(v, "Setting", null)
        return v
    }
}