package com.umc.clear.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.umc.clear.data.entities.Friend

@Dao
interface FriendDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun ins(friend: Friend)

    @Query("SELECT * FROM FriendTable")
    fun getAll(): List<Friend>
}