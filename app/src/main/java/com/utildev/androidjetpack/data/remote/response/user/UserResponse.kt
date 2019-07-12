package com.utildev.androidjetpack.data.remote.response.user

import com.utildev.androidjetpack.data.BaseModel
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("items") val items: ArrayList<UserItemResponse>? = null,
    @SerializedName("has_more") val hasMore: Boolean? = null,
    @SerializedName("quota_max") val quotaMax: Int? = null,
    @SerializedName("quota_remaining") val quotaRemaining: Int? = null
) : BaseModel()