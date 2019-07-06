package com.utildev.androidjetpack.presentation.base

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.utildev.androidjetpack.common.extensions.REQUEST_ERROR
import com.utildev.androidjetpack.data.remote.ApiClient
import com.utildev.androidjetpack.data.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import java.lang.reflect.Type

@Suppress("LeakingThis")
abstract class BaseViewModel(private val repository: Repository): ViewModel(), ApiClient.ResponseListener {
    val loadingView = ObservableInt(View.GONE)
    val msgViewVisible = ObservableInt(View.GONE)
    val message: ObservableField<String> = ObservableField()
    val enabledView = ObservableBoolean(false)

    private val compositeDisposable = CompositeDisposable()
    val apiClient = ApiClient(this, compositeDisposable)

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

    override fun onSuccess(code: Int, type: Type?, response: JsonObject) {
        dismissLoading()
        hideMessage()
    }

    override fun onFailure(code: Int, type: Type?) {
        dismissLoading()
        showMessage(REQUEST_ERROR)
    }

    override fun onNextAction(code: Int) {

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}