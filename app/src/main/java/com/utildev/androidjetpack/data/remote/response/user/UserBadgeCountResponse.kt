package com.utildev.androidjetpack.data.remote.response.user

import com.utildev.androidjetpack.data.BaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserBadgeCountResponse: BaseModel() {
    @SerializedName("bronze")
    @Expose
    val bronze: Int = 0
    @SerializedName("silver")
    @Expose
    val silver: Int = 0
    @SerializedName("gold")
    @Expose
    val gold: Int = 0
}