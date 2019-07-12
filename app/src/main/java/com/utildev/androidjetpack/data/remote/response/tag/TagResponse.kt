package com.utildev.androidjetpack.data.remote.response.tag

import com.utildev.androidjetpack.data.BaseModel
import com.google.gson.annotations.SerializedName

data class TagResponse(
    @SerializedName("items") val items: ArrayList<TagItemResponse>? = null,
    @SerializedName("has_more") val hasMore: Boolean? = null,
    @SerializedName("quota_max") val quotaMax: Int? = null,
    @SerializedName("quota_remaining") val quotaRemaining: Int? = null
) : BaseModel()