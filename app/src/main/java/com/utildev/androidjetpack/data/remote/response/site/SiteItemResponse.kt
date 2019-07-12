package com.utildev.androidjetpack.data.remote.response.site

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.utildev.androidjetpack.data.BaseModel

data class SiteItemResponse(
    @SerializedName("aliases") val aliases: MutableList<String>? = null,
    @SerializedName("styling") val styling: SiteStylingResponse? = null,
    @SerializedName("related_sites") val relatedSites: MutableList<SiteRelatedResponse>? = null,
    @SerializedName("markdown_extensions") val markdownExtensions: MutableList<String>? = null,
    @SerializedName("launch_date") val launchDate: Long = 0,
    @SerializedName("open_beta_date") val openBetaDate: Long = 0,
    @SerializedName("site_state") val siteState: String? = null,
    @SerializedName("high_resolution_icon_url") val highResolutionIconUrl: String? = null,
    @SerializedName("favicon_url") val faviconUrl: String? = null,
    @SerializedName("icon_url") val iconUrl: String? = null,
    @SerializedName("audience") val audience: String? = null,
    @SerializedName("site_url") val siteUrl: String? = null,
    @SerializedName("api_site_parameter") val apiSiteParameter: String? = null,
    @SerializedName("logo_url") val logoUrl: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("site_type") val siteType: String? = null,
    @SerializedName("twitter_account") val twitterAccount: String? = null,
    @SerializedName("closed_beta_date") val closedBetaDate: Long = 0
) : BaseModel()