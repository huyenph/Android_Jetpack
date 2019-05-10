package com.utildev.androidjetpack.data.repository

import com.google.gson.JsonObject
import com.utildev.androidjetpack.data.remote.ApiService
import io.reactivex.Observable
import okhttp3.RequestBody

class AppRepository(private val apiService: ApiService): Repository {
    override fun getMenu(pageSize: Int): Observable<JsonObject> = apiService.requestMenu(pageSize)

    override fun getQuestion(site: String, page: Int): Observable<JsonObject> = apiService.requestQuestion(site, page)

    override fun getAllSite(page: Int): Observable<JsonObject> = apiService.requestAllSite(page)

    override fun requestBody(body: RequestBody): Observable<JsonObject> = apiService.requestBody(body)
}