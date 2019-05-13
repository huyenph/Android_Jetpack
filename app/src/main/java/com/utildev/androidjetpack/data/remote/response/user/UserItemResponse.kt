package com.utildev.androidjetpack.data.remote.response.user

import com.utildev.androidjetpack.data.BaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class UserItemResponse : BaseModel() {
    @SerializedName("badge_counts")
    @Expose
    val badgeCounts: UserBadgeCountResponse? = null
    @SerializedName("account_id")
    @Expose
    val accountId: Int? = null
    @SerializedName("is_employee")
    @Expose
    val isEmployee: Boolean = false
    @SerializedName("last_modified_date")
    @Expose
    val lastModifiedDate: String? = null
        get() = field ?: ""
    @SerializedName("last_access_date")
    @Expose
    val lastAccessDate: String? = null
        get() = field ?: ""
    @SerializedName("reputation_change_year")
    @Expose
    val reputationChangeYear: String? = null
        get() = field ?: ""
    @SerializedName("reputation_change_quarter")
    @Expose
    val reputationChangeQuarter: String? = null
        get() = field ?: ""
    @SerializedName("reputation_change_month")
    @Expose
    val reputationChangeMonth: String? = null
        get() = field ?: ""
    @SerializedName("reputation_change_week")
    @Expose
    val reputationChangeWeek: String? = null
        get() = field ?: ""
    @SerializedName("reputation_change_day")
    @Expose
    val reputationChangeDay: String? = null
        get() = field ?: ""
    @SerializedName("reputation")
    @Expose
    val reputation: String? = null
        get() = field ?: ""
    @SerializedName("creation_date")
    @Expose
    val creationDate: String? = null
        get() = field ?: ""
    @SerializedName("user_type")
    @Expose
    val userType: String? = null
        get() = field ?: ""
    @SerializedName("user_id")
    @Expose
    val userId: Int? = null
    @SerializedName("accept_rate")
    @Expose
    val acceptRate: String? = null
        get() = field ?: ""
    @SerializedName("location")
    @Expose
    val location: String? = null
        get() = field ?: ""
    @SerializedName("website_url")
    @Expose
    val websiteUrl: String? = null
        get() = field ?: ""
    @SerializedName("link")
    @Expose
    val link: String? = null
        get() = field ?: ""
    @SerializedName("profile_image")
    @Expose
    val profileImage: String? = null
        get() = field ?: ""
    @SerializedName("display_name")
    @Expose
    val displayName: String? = null
        get() = field ?: ""
}