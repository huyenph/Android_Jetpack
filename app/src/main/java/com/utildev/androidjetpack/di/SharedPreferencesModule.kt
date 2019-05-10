package com.utildev.androidjetpack.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import org.koin.dsl.module.module

val sharedPrefModule = module {
    single { SharedPrefs(get()) }
}

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SharedPrefs(context: Context) {
    private val sharedPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun putString(key: String, value: String?) {
        sharedPrefs.edit().putString(key, value).apply()
    }

    fun getString(key: String): String = sharedPrefs.getString(key, "")

//    fun putAccountData(key: String, accountDataResponse: AccountDataResponse?) {
//        val json = Gson().toJson(accountDataResponse)
//        sharedPrefs.edit().putString(key, json).apply()
//    }
//
//    fun getAccountData(key: String): AccountDataResponse? =
//        Gson().fromJson(sharedPrefs.getString(key, ""), AccountDataResponse::class.java)

}