package com.utildev.androidjetpack.presentation.fragment.tag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.presentation.base.BaseFragment
import com.utildev.androidjetpack.databinding.FragmentTagBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TagFragment: BaseFragment() {
    private val vm: TagViewModel by viewModel()
    private lateinit var mView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentTagBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_tag, container, false)
        binding.vm = vm
        mView = binding.root
        return mView
    }
}