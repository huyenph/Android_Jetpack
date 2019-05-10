package com.utildev.androidjetpack.data.repository

import com.google.gson.JsonObject
import io.reactivex.Observable
import okhttp3.RequestBody

interface Repository {
    fun getMenu(page: Int, pageSize: Int): Observable<JsonObject>

    fun getQuestion(site: String, page: Int): Observable<JsonObject>

    fun getAllSite(page: Int): Observable<JsonObject>

    fun requestBody(body: RequestBody): Observable<JsonObject>
}