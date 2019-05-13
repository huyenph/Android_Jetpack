package com.utildev.androidjetpack.data.remote.response.tag

import com.utildev.androidjetpack.data.BaseModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TagItemResponse : BaseModel() {
    @SerializedName("has_synonyms")
    @Expose
    val hasSynonyms: Boolean? = null
    @SerializedName("is_moderator_only")
    @Expose
    val isModeratorOnly: Boolean? = null
    @SerializedName("is_required")
    @Expose
    val isRequired: Boolean? = null
    @SerializedName("count")
    @Expose
    val count: Int? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
        get() = field ?: ""
}