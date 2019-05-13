package com.utildev.androidjetpack.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.presentation.base.BaseFragment

class SecondFragment : BaseFragment() {
    companion object {
        fun newInstance(value: Int) = SecondFragment().apply {
            arguments = Bundle().apply {
                putInt("second", value)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }
}