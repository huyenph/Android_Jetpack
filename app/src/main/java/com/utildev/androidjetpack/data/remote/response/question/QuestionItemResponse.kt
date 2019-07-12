package com.utildev.androidjetpack.data.remote.response.question

import com.google.gson.annotations.SerializedName
import com.utildev.androidjetpack.data.BaseModel
import kotlinx.serialization.Serializable

@Serializable
data class QuestionItemResponse(
    @SerializedName("tags") val tags: List<String>? = null,
    @SerializedName("owner") val owner: QuestionOwnerResponse? = null,
    @SerializedName("is_answered") val isAnswered: Boolean = false,
    @SerializedName("view_count") val viewCount: Int = 0,
    @SerializedName("closed_date") val closedDate: Long = 0,
    @SerializedName("answer_count") val answerCount: Long = 0,
    @SerializedName("score") val score: Int = 0,
    @SerializedName("last_activity_date") val lastActivityDate: Long = 0,
    @SerializedName("creation_date") val creationDate: Long = 0,
    @SerializedName("last_edit_date") val lastEditDate: Long = 0,
    @SerializedName("question_id") val questionId: String? = null,
    @SerializedName("link") val link: String? = null,
    @SerializedName("closed_reason") val closedReason: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("accepted_answer_id") val acceptedAnswerId: Int = 0
) {
    fun convertTags() = tags!!.joinToString()
}