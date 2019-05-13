package com.utildev.androidjetpack.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.presentation.base.BaseFragment

class FifthFragment: BaseFragment() {
    companion object {
        fun newInstance(value: Int) = FifthFragment().apply {
            arguments = Bundle().apply {
                putInt("fifth", value)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("a", 123)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            Log.d("aaa", "$savedInstanceState")
        }
    }
}