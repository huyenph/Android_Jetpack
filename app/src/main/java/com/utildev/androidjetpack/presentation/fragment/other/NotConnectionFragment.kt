package com.utildev.androidjetpack.presentation.fragment.other

import android.view.View
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.presentation.activity.MainActivity
import com.utildev.androidjetpack.presentation.base.BaseFragment
import com.utildev.androidjetpack.databinding.FragmentNotConnectionBinding
import kotlinx.android.synthetic.main.fragment_not_connection.view.*

class NotConnectionFragment: BaseFragment<FragmentNotConnectionBinding, NotConnectionViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_not_connection

    override fun getBindingVariable(): Int? = null

    override fun getViewModel(): NotConnectionViewModel? = null

    override fun init(view: View) {
        view.fmNoConnection_btRetry.setOnClickListener {
            val intent = (activity as MainActivity).intent
            (activity as MainActivity).finish()
            (activity as MainActivity).startActivity(intent)
        }
    }
}