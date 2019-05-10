package com.utildev.androidjetpack.di

import android.content.Context
import androidx.room.Room
import com.utildev.androidjetpack.common.extensions.DB_NAME
import com.utildev.androidjetpack.data.local.db.AppDatabase
import com.utildev.androidjetpack.data.repository.AppRepository
import com.utildev.androidjetpack.data.repository.Repository
import org.koin.dsl.module.module
import org.koin.experimental.builder.create

val repositoryModule = module {
    single<Repository> { create<AppRepository>() }
    single { createDatabaseName() }
    single { createAppDatabase(get(), get()) }
    single { createDBDao(get()) }
}

fun createDatabaseName() = DB_NAME

fun createAppDatabase(dbName: String, context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()

fun createDBDao(appDatabase: AppDatabase) = appDatabase.dbDao()