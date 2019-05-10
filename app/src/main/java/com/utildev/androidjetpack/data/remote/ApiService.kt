package com.utildev.androidjetpack.data.remote

import com.google.gson.JsonObject
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("sites?key=KCTJhLJ5*JRozzNhBK20og((")
    fun requestSite(
        @Query("page") order: Int,
        @Query("pagesize") sort: Int
    ): Observable<JsonObject>

    @GET("questions?key=KCTJhLJ5*JRozzNhBK20og((&pagesize=10")
    fun requestQuestion(@Query("site") site: String, @Query("page") page: Int): Observable<JsonObject>

    @GET("sites?key=KCTJhLJ5*JRozzNhBK20og((")
    fun requestAllSite(@Query("page") page: Int): Observable<JsonObject>

    @POST("")
    fun requestBody(@Body body: RequestBody): Observable<JsonObject>

//    @GET("users")
//    fun requestUser(
//        @Query("order") order: String,
//        @Query("sort") sort: String,
//        @Query("site") site: String,
//        @Query("page") page: Int
//    ): Call<JsonObject>
//
//    @FormUrlEncoded
//    @POST("key, object")
//    fun requestNormal(@FieldMap body: Map<String, Any>): Observable<JsonObject>
//
//    @POST("storeList")
//    fun requestList(@Body list: List<BaseViewModel>): Observable<JsonObject>
//
//    @GET("not params")
//    fun requestNotParams(): Observable<JsonObject>
//
//    @POST("upload file")
//    fun requestFile(@Body file: RequestBody): Observable<JsonObject>
}