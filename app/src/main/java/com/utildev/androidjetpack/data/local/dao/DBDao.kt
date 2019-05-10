package com.utildev.androidjetpack.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.utildev.androidjetpack.common.extensions.TB_NAME
import com.utildev.androidjetpack.data.local.model.UserEntity
import io.reactivex.Flowable

@Dao
interface DBDao {
    @Query("SELECT COUNT(*) FROM `$TB_NAME`")
    fun getUserCount(): Flowable<Int>

    @Query("SELECT * FROM `$TB_NAME`")
    fun getAllUser(): Flowable<MutableList<UserEntity>>

    @Insert
    fun insertUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)

//  @Query("DELETE FROM $TB_NAME WHERE id = :id")
//  fun deleteMovie(id: String)

    @Query("DELETE FROM `$TB_NAME`")
    fun deleteAll()

    @Query("SELECT * FROM `$TB_NAME` LIMIT :pageSize OFFSET :pageIndex")
    fun getMoviePage(pageSize: Int, pageIndex: Int): Flowable<List<UserEntity>>

//  @Query("SELECT * FROM $TB_NAME WHERE $TB_NAME.sth = 1 LIMIT :pageSize OFFSET ((:pageIndex-1)*:pageSize) ")
//  fun getFavorite(pageSize: Int, pageIndex: Int): Flowable<List<UserEntity>>
}