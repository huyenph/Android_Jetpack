package com.utildev.androidjetpack.data.remote.response.question

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.utildev.androidjetpack.data.BaseModel

class QuestionOwnerResponse : BaseModel() {
    @SerializedName("reputation")
    @Expose
    val reputation: Int = 0
    @SerializedName("user_id")
    @Expose
    private val userId: String? = null
        get() = field ?: ""
    @SerializedName("user_type")
    @Expose
    val userType: String? = null
        get() = field ?: ""
    @SerializedName("profile_image")
    @Expose
    val profileImage: String? = null
        get() = field ?: ""
    @SerializedName("display_name")
    @Expose
    val displayName: String? = null
        get() = field ?: ""
    @SerializedName("link")
    @Expose
    val link: String? = null
        get() = field ?: ""
    @SerializedName("accept_rate")
    @Expose
    val acceptRate: Int = 0
}