package com.utildev.androidjetpack.presentation.base

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.utildev.androidjetpack.data.repository.Repository

abstract class BaseViewModel(private val repository: Repository): ViewModel() {
    val loadingView = ObservableInt(View.GONE)
    val msgViewVisible = ObservableInt(View.GONE)
    val message: ObservableField<String> = ObservableField()
    val enabledView = ObservableBoolean(false)

    fun showLoading() {
        if (loadingView.get() != View.VISIBLE) {
            loadingView.set(View.VISIBLE)
            enabledView.set(false)
        }
    }

    fun dismissLoading() {
        if (loadingView.get() != View.GONE) {
            loadingView.set(View.GONE)
            enabledView.set(true)
        }
    }

    fun showMessage(msg: String) {
        if (msgViewVisible.get() != View.VISIBLE) {
            msgViewVisible.set(View.VISIBLE)
            message.set(msg)
        }
    }

    fun hideMessage() {
        if (msgViewVisible.get() != View.GONE) {
            msgViewVisible.set(View.GONE)
        }
    }
}