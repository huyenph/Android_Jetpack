package com.utildev.androidjetpack.presentation.fragment.other

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.presentation.base.BaseActivity
import com.utildev.androidjetpack.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_not_connection.view.*

class NotConnectionFragment: BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_not_connection, container, false)
        view.fmNoConnection_btRetry.setOnClickListener {
            val intent = (activity as BaseActivity<*, *>).intent
            (activity as BaseActivity<*, *>).finish()
            (activity as BaseActivity<*, *>).startActivity(intent)
        }
        return view
    }
}