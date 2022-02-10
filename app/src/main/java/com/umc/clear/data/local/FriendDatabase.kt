package com.umc.clear.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.umc.clear.data.entities.Friend

@Database (entities=[Friend::class], version = 1)
abstract class FriendDatabase: RoomDatabase() {

    abstract fun friendDao(): FriendDao

    companion object {
        private var instance: FriendDatabase? = null

        @Synchronized
        fun getInstance(context: Context): FriendDatabase? {
            if (instance == null) {
                synchronized(FriendDatabase::class) {//synchronized block-> 클래스자체를 동기화->클래스 사용해 생성하는 모든 쓰레드 동기화
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FriendDatabase::class.java,
                        "friend_database"
                    ).allowMainThreadQueries().build()
                }
            }
            return instance
        }
    }
}