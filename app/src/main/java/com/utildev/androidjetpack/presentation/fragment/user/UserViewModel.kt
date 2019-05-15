package com.utildev.androidjetpack.presentation.fragment.user

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.utildev.androidjetpack.data.remote.response.user.UserItemResponse
import com.utildev.androidjetpack.data.remote.response.user.UserResponse
import com.utildev.androidjetpack.data.repository.Repository
import com.utildev.androidjetpack.presentation.base.BaseViewModel
import java.lang.reflect.Type

class UserViewModel(private val repository: Repository): BaseViewModel(repository) {
    val userLive: MutableLiveData<ArrayList<UserItemResponse>> = MutableLiveData()

    fun getUser(site: String, page: Int, loading: Boolean) {
        if (loading) showLoading()
        apiClient.request(
            3,
            object : TypeToken<UserResponse>() {}.type,
            repository.getUser("desc", "reputation", site, page)
        )
    }

    override fun onSuccess(code: Int, type: Type?, response: JsonObject) {
        super.onSuccess(code, type, response)
        val user = Gson().fromJson(response, type) as UserResponse
        userLive.value = user.items
    }
}