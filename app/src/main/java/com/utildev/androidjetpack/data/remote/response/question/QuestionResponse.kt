package com.utildev.androidjetpack.data.remote.response.question

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.utildev.androidjetpack.data.BaseModel

class QuestionResponse: BaseModel() {
    @SerializedName("items")
    @Expose
   val items: ArrayList<QuestionItemResponse>? = null
    @SerializedName("has_more")
    @Expose
    val hasMore: Boolean = false
    @SerializedName("quota_max")
    @Expose
    val quotaMax: Int = 0
    @SerializedName("quota_remaining")
    @Expose
    val quotaRemaining: Int = 0
}