package com.dsm.dataroomexample.context

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dsm.dataroomexample.dao.UserDAO
import com.dsm.dataroomexample.model.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class DBContext: RoomDatabase() {
    abstract fun daoUser(): UserDAO
}