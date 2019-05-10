package com.utildev.androidjetpack.data.remote.response.site

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.utildev.androidjetpack.data.BaseModel

class SiteStylingResponse : BaseModel() {
    @SerializedName("tag_background_color")
    @Expose
    val tagBackgroundColor: String? = null
        get() = field ?: ""
    @SerializedName("tag_foreground_color")
    @Expose
    val tagForegroundColor: String? = null
        get() = field ?: ""
    @SerializedName("link_color")
    @Expose
    val linkColor: String? = null
        get() = field ?: ""
}