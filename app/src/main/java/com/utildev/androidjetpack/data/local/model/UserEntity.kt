package com.utildev.androidjetpack.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.utildev.androidjetpack.common.extensions.TB_NAME

@Entity(tableName = TB_NAME)
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "job")
    var job: String?
)