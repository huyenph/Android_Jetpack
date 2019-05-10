package com.utildev.androidjetpack.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.presentation.base.BaseFragment

class ThirdFragment : BaseFragment() {
    companion object {
        fun newInstance(value: Int) = ThirdFragment().apply {
            arguments = Bundle().apply {
                putInt("third", value)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_third, container, false)
    }
}