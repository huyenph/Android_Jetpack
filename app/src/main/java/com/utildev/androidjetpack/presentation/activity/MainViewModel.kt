package com.utildev.androidjetpack.presentation.activity

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.utildev.androidjetpack.data.remote.response.site.SiteItemResponse
import com.utildev.androidjetpack.data.remote.response.site.SiteResponse
import com.utildev.androidjetpack.data.repository.Repository
import com.utildev.androidjetpack.presentation.base.BaseViewModel
import java.lang.reflect.Type

class MainViewModel(private val repository: Repository): BaseViewModel(repository) {
    val menuLive: MutableLiveData<MutableList<SiteItemResponse>> = MutableLiveData()

    fun loadMenu() {
        apiClient.request(5, object : TypeToken<SiteResponse>() {}.type, repository.getMenu(10))
    }

    override fun onSuccess(code: Int, type: Type?, response: JsonObject) {
        super.onSuccess(code, type, response)
        val site = Gson().fromJson(response, type) as SiteResponse
        menuLive.value = site.items
    }
}