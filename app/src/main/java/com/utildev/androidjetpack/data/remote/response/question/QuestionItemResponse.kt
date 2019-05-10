package com.utildev.androidjetpack.data.remote.response.question

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.utildev.androidjetpack.data.BaseModel

class QuestionItemResponse : BaseModel() {
    @SerializedName("tags")
    @Expose
    val tags: MutableList<String>? = null
    @SerializedName("owner")
    @Expose
    val owner: QuestionOwnerResponse? = null
    @SerializedName("is_answered")
    @Expose
    val isAnswered: Boolean = false
    @SerializedName("view_count")
    @Expose
    val viewCount: Int = 0
    @SerializedName("closed_date")
    @Expose
    val closedDate: Long = 0
    @SerializedName("answer_count")
    @Expose
    val answerCount: Long = 0
    @SerializedName("score")
    @Expose
    private val score: Int = 0
    @SerializedName("last_activity_date")
    @Expose
    val lastActivityDate: Long = 0
    @SerializedName("creation_date")
    @Expose
    val creationDate: Long = 0
    @SerializedName("last_edit_date")
    @Expose
    val lastEditDate: Long = 0
    @SerializedName("question_id")
    @Expose
    val questionId: String? = null
        get() = field ?: ""
    @SerializedName("link")
    @Expose
    val link: String? = null
        get() = field ?: ""
    @SerializedName("closed_reason")
    @Expose
    val closedReason: String? = null
        get() = field ?: ""
    @SerializedName("title")
    @Expose
    val title: String? = null
        get() = field ?: ""
    @SerializedName("accepted_answer_id")
    @Expose
    val acceptedAnswerId: Int = 0

    fun convertTags() = tags!!.joinToString()
}