package com.utildev.androidjetpack.presentation.fragment.question

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.utildev.androidjetpack.data.remote.response.question.QuestionItemResponse
import com.utildev.androidjetpack.data.remote.response.question.QuestionResponse
import com.utildev.androidjetpack.data.repository.Repository
import com.utildev.androidjetpack.presentation.base.BaseViewModel
import java.lang.reflect.Type

class QuestionViewModel(private val repository: Repository): BaseViewModel(repository) {
    var questionLive: MutableLiveData<MutableList<QuestionItemResponse>> = MutableLiveData()

    fun getQuestion(site: String, page: Int, loading: Boolean) {
        if (loading) showLoading()
        apiClient.request(2, object : TypeToken<QuestionResponse>() {}.type, repository.getQuestion(site, page))
    }

    override fun onSuccess(code: Int, type: Type?, response: JsonObject) {
        super.onSuccess(code, type, response)
        val question = Gson().fromJson(response, type) as QuestionResponse
        questionLive.value = question.items
    }
}