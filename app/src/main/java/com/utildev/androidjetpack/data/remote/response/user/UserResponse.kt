package com.utildev.androidjetpack.data.remote.response.user

import com.utildev.androidjetpack.data.BaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse: BaseModel() {
    @SerializedName("items")
    @Expose
    val items: ArrayList<UserDataResponse>? = null
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