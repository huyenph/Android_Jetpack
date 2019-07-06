package com.utildev.androidjetpack.data.remote

import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class ApiClient(private val responseListener: ResponseListener, private val compositeDisposable: CompositeDisposable) {

    fun request(code: Int, type: Type?, observable: Observable<JsonObject>) {
        val disposable = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseListener.onSuccess(code, type, it)
            }, {
                responseListener.onFailure(code, type)
            }, {
                responseListener.onNextAction(code)
            })
        compositeDisposable.add(disposable)
    }

    fun requestSites(code: Int, type: Type?, call: Call<JsonObject>) {
        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                response.body()?.let { responseListener.onSuccess(code, type, it) }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                responseListener.onFailure(code, type)
            }
        })
    }

    interface ResponseListener {
        fun onSuccess(code: Int, type: Type?, response: JsonObject)
        fun onFailure(code: Int, type: Type?)
        fun onNextAction(code: Int)
    }
}