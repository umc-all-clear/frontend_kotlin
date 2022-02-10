package com.umc.clear.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FriendTable")
data class Friend (
    var name: String,
    @PrimaryKey var mail: String,
    var avgRate: String
    )