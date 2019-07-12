package com.utildev.androidjetpack.data.remote.response.site

import com.google.gson.annotations.SerializedName
import com.utildev.androidjetpack.data.BaseModel

data class SiteRelatedResponse(
    @SerializedName("relation") val relation: String? = null,
    @SerializedName("api_site_parameter") val apiSiteParameter: String? = null,
    @SerializedName("site_url") val siteUrl: String? = null,
    @SerializedName("name") val name: String? = null
    ) : BaseModel()