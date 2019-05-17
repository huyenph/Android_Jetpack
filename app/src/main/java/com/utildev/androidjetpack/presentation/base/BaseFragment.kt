package com.utildev.androidjetpack.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T: ViewDataBinding, V: BaseViewModel>: Fragment() {
    private var fmResultListener: FragmentResultListener? = null
    private var requestCode = 0

    private lateinit var binding: ViewDataBinding
    private lateinit var rootView: View

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getBindingVariable(): Int

    abstract fun getViewModel(): V?

    abstract fun init(view: View)

    fun getViewDataBinding(): ViewDataBinding = binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        if (getViewModel() != null) {
            binding.setVariable(getBindingVariable(), getViewModel())
            binding.executePendingBindings()
        }
        rootView = binding.root
        init(rootView)
        return rootView
    }

    fun configToolbarMain(view: View, title: String?) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).configToolbarMain(view, title)
        }
    }

    fun configToolbar(view: View, title: String?, listener: BaseActivity.BackStackListener?) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).configToolbar(view, title, listener)
        }
    }

    fun setFragmentResult(requestCode: Int, listener: FragmentResultListener?) {
        this.requestCode = requestCode
        this.fmResultListener = listener
    }

    fun callBackFragmentResult(bundle: Bundle) {
        if (fmResultListener != null) {
            fmResultListener!!.onFragmentResult(requestCode, bundle)
        }
    }

    fun replaceFragment(fragment: BaseFragment<*, *>, addToBackStack: Boolean, animation: Boolean) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).replaceFragment(fragment, addToBackStack, animation)
        }
    }

    fun addFragment(fragment: BaseFragment<*, *>, addToBackStack: Boolean, animation: Boolean) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).addFragment(fragment, addToBackStack, animation)
        }
    }

    fun clearAllStack() {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).clearAllStack()
        }
    }

    fun clearStack() {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).clearStack()
        }
    }

    interface FragmentResultListener {
        fun onFragmentResult(code: Int, bundle: Bundle)
    }
}