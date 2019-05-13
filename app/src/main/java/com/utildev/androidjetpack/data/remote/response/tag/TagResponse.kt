package com.utildev.androidjetpack.data.remote.response.tag

import com.utildev.androidjetpack.data.BaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TagResponse: BaseModel() {
    @SerializedName("items")
    @Expose
    val items: ArrayList<TagDataResponse>? = null
    @SerializedName("has_more")
    @Expose
    val hasMore: Boolean? = null
    @SerializedName("quota_max")
    @Expose
    val quotaMax: Int? = null
    @SerializedName("quota_remaining")
    @Expose
    val quotaRemaining: Int? = null
}