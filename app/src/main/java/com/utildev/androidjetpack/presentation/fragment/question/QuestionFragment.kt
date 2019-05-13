package com.utildev.androidjetpack.presentation.fragment.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.utildev.androidjetpack.R
import com.utildev.androidjetpack.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import com.utildev.androidjetpack.databinding.FragmentQuestionBinding

class QuestionFragment : BaseFragment() {
    private val vm: QuestionViewModel by viewModel()
    private lateinit var mView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentQuestionBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        binding.vm = vm
        mView = binding.root
        return mView
    }
}