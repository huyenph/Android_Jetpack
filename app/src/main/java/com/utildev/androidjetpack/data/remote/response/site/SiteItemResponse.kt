package com.utildev.androidjetpack.data.remote.response.site

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.utildev.androidjetpack.data.BaseModel

class SiteItemResponse : BaseModel() {
    @SerializedName("aliases")
    @Expose
    val aliases: MutableList<String>? = null
    @SerializedName("styling")
    @Expose
    val styling: SiteStylingResponse? = null
    @SerializedName("related_sites")
    @Expose
    val relatedSites: MutableList<SiteRelatedResponse>? = null
    @SerializedName("markdown_extensions")
    @Expose
    val markdownExtensions: MutableList<String>? = null
    @SerializedName("launch_date")
    @Expose
    val launchDate: Long = 0
    @SerializedName("open_beta_date")
    @Expose
    val openBetaDate: Long = 0
    @SerializedName("site_state")
    @Expose
    val siteState: String? = null
        get() = field ?: ""
    @SerializedName("high_resolution_icon_url")
    @Expose
    val highResolutionIconUrl: String? = null
        get() = field ?: ""
    @SerializedName("favicon_url")
    @Expose
    val faviconUrl: String? = null
        get() = field ?: ""
    @SerializedName("icon_url")
    @Expose
    val iconUrl: String? = null
        get() = field ?: ""
    @SerializedName("audience")
    @Expose
    val audience: String? = null
        get() = field ?: ""
    @SerializedName("site_url")
    @Expose
    val siteUrl: String? = null
        get() = field ?: ""
    @SerializedName("api_site_parameter")
    @Expose
    val apiSiteParameter: String? = null
        get() = field ?: ""
    @SerializedName("logo_url")
    @Expose
    val logoUrl: String? = null
        get() = field ?: ""
    @SerializedName("name")
    @Expose
    val name: String? = null
        get() = field ?: ""
    @SerializedName("site_type")
    @Expose
    val siteType: String? = null
        get() = field ?: ""
    @SerializedName("twitter_account")
    @Expose
    val twitterAccount: String? = null
        get() = field ?: ""
    @SerializedName("closed_beta_date")
    @Expose
    val closedBetaDate: Long = 0
}