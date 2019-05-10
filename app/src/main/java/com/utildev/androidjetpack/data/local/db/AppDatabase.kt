package com.utildev.androidjetpack.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.utildev.androidjetpack.data.local.dao.DBDao
import com.utildev.androidjetpack.data.local.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dbDao(): DBDao
}