package com.utildev.androidjetpack.data.remote.response.site

import com.google.gson.annotations.SerializedName
import com.utildev.androidjetpack.data.BaseModel

data class SiteStylingResponse(
    @SerializedName("tag_background_color") val tagBackgroundColor: String? = null,
    @SerializedName("tag_foreground_color") val tagForegroundColor: String? = null,
    @SerializedName("link_color") val linkColor: String? = null
    ) : BaseModel()