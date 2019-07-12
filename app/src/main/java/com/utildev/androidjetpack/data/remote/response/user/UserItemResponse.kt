package com.utildev.androidjetpack.data.remote.response.user

import com.utildev.androidjetpack.data.BaseModel
import com.google.gson.annotations.SerializedName

data class UserItemResponse(
    @SerializedName("badge_counts") val badgeCounts: UserBadgeCountResponse? = null,
    @SerializedName("account_id") val accountId: Int? = null,
    @SerializedName("is_employee") val isEmployee: Boolean = false,
    @SerializedName("last_modified_date") val lastModifiedDate: String? = null,
    @SerializedName("last_access_date") val lastAccessDate: String? = null,
    @SerializedName("reputation_change_year") val reputationChangeYear: String? = null,
    @SerializedName("reputation_change_quarter") val reputationChangeQuarter: String? = null,
    @SerializedName("reputation_change_month") val reputationChangeMonth: String? = null,
    @SerializedName("reputation_change_week") val reputationChangeWeek: String? = null,
    @SerializedName("reputation_change_day") val reputationChangeDay: String? = null,
    @SerializedName("reputation") val reputation: String? = null,
    @SerializedName("creation_date") val creationDate: String? = null,
    @SerializedName("user_type") val userType: String? = null,
    @SerializedName("user_id") val userId: Int? = null,
    @SerializedName("accept_rate") val acceptRate: String? = null,
    @SerializedName("location") val location: String? = null,
    @SerializedName("website_url") val websiteUrl: String? = null,
    @SerializedName("link") val link: String? = null,
    @SerializedName("profile_image") val profileImage: String? = null,
    @SerializedName("display_name") val displayName: String? = null
) : BaseModel()