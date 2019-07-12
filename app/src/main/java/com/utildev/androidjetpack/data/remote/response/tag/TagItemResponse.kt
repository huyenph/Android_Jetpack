package com.utildev.androidjetpack.data.remote.response.tag

import com.utildev.androidjetpack.data.BaseModel
import com.google.gson.annotations.SerializedName

data class TagItemResponse(
    @SerializedName("has_synonyms") val hasSynonyms: Boolean? = null,
    @SerializedName("is_moderator_only") val isModeratorOnly: Boolean? = null,
    @SerializedName("is_required") val isRequired: Boolean? = null,
    @SerializedName("count") val count: Int? = null,
    @SerializedName("name") val name: String? = null
    ) : BaseModel()