package com.utildev.androidjetpack.data.remote.response.error

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.utildev.androidjetpack.data.BaseModel

class ErrorResponse : BaseModel() {
    @SerializedName("error_id")
    @Expose
    val errorId: Int = 0
    @SerializedName("error_message")
    @Expose
    val errorMessage: String? = null
        get() = field ?: ""
    @SerializedName("error_name")
    @Expose
    val errorName: String? = null
        get() = field ?: ""
}