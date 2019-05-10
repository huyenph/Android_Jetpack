package com.utildev.androidjetpack.data.remote

import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.reflect.Type

class ApiClient(private val responseListener: ResponseListener) {
    private val compositeDisposable = CompositeDisposable()

    fun request(code: Int, type: Type?, observable: Observable<JsonObject>) {
        val disposable = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseListener.onSuccess(code, type, it)
            }, {
                responseListener.onFailure()
            })
        compositeDisposable.add(disposable)
    }

    fun dispose() {
        compositeDisposable.dispose()
    }

    interface ResponseListener {
        fun onSuccess(code: Int, type: Type?, response: JsonObject)
        fun onFailure()
    }
}