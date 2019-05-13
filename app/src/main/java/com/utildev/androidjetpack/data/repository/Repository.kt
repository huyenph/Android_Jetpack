package com.utildev.androidjetpack.data.repository

import com.google.gson.JsonObject
import com.utildev.androidjetpack.data.local.model.UserEntity
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.RequestBody

interface Repository {
    fun getMenu(pageSize: Int): Observable<JsonObject>

    fun getQuestion(site: String, page: Int): Observable<JsonObject>

    fun getAllSite(page: Int): Observable<JsonObject>

    fun requestBody(body: RequestBody): Observable<JsonObject>

    fun insert(userEntity: UserEntity)

    fun getAllUser(): Flowable<MutableList<UserEntity>>
}