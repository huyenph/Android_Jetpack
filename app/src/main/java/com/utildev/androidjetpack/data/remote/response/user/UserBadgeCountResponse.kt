package com.utildev.androidjetpack.data.remote.response.user

import com.utildev.androidjetpack.data.BaseModel
import com.google.gson.annotations.SerializedName

data class UserBadgeCountResponse(
    @SerializedName("bronze") val bronze: Int = 0,
    @SerializedName("silver") val silver: Int = 0,
    @SerializedName("gold") val gold: Int = 0
) : BaseModel()