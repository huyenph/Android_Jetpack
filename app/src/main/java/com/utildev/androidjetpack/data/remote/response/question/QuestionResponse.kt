package com.utildev.androidjetpack.data.remote.response.question

import com.google.gson.annotations.SerializedName
import com.utildev.androidjetpack.data.BaseModel

data class QuestionResponse(
    @SerializedName("items") val items: ArrayList<QuestionItemResponse>? = null,
    @SerializedName("has_more") val hasMore: Boolean = false,
    @SerializedName("quota_max") val quotaMax: Int = 0,
    @SerializedName("quota_remaining") val quotaRemaining: Int = 0
) : BaseModel()