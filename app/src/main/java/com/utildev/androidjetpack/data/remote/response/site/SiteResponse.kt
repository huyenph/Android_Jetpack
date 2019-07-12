package com.utildev.androidjetpack.data.remote.response.site

import com.google.gson.annotations.SerializedName
import com.utildev.androidjetpack.data.BaseModel

data class SiteResponse(
    @SerializedName("items") val items: MutableList<SiteItemResponse>? = null,
    @SerializedName("has_more") val hasMore: Boolean? = null,
    @SerializedName("quota_max") val quotaMax: Int? = null,
    @SerializedName("quota_remaining") val quotaRemaining: Int? = null
) : BaseModel()