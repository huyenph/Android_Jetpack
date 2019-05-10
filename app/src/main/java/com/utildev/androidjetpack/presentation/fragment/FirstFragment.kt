package com.utildev.androidjetpack.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.presentation.base.BaseFragment

class FirstFragment: BaseFragment() {
    companion object {
        fun newInstance(value: Int) = FirstFragment().apply {
            arguments = Bundle().apply {
                putInt("first", value)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }
}