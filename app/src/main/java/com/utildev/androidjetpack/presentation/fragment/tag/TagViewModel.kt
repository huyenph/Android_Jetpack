package com.utildev.androidjetpack.presentation.fragment.tag

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.utildev.androidjetpack.data.remote.response.tag.TagItemResponse
import com.utildev.androidjetpack.data.remote.response.tag.TagResponse
import com.utildev.androidjetpack.data.repository.Repository
import com.utildev.androidjetpack.presentation.base.BaseViewModel
import java.lang.reflect.Type

class TagViewModel(private val repository: Repository) : BaseViewModel(repository) {
    val tagLive: MutableLiveData<ArrayList<TagItemResponse>> = MutableLiveData()

    fun getTag(page: Int, loading: Boolean) {
        if (loading) showLoading()
        apiClient.request(
            2,
            object : TypeToken<TagResponse>() {}.type,
            repository.getTag("desc", "popular", "stackoverflow", page)
        )
    }

    override fun onSuccess(code: Int, type: Type?, response: JsonObject) {
        super.onSuccess(code, type, response)
        val tag = Gson().fromJson(response, type) as TagResponse
        tagLive.value = tag.items
    }
}