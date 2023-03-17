package com.dsm.dataroomexample.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User (
    @PrimaryKey var user: String,
    @ColumnInfo(name = "country") var country: String
)