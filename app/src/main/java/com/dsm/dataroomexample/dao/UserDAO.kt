package com.dsm.dataroomexample.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dsm.dataroomexample.model.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM Users")
    suspend fun getUsers(): MutableList<User>

    @Insert
    suspend fun addUser(User: User)

    @Query("UPDATE Users SET country=:Country WHERE user=:Username")
    suspend fun updateUser(Username: String, Country: String)

    @Query("DELETE FROM Users WHERE user=:Username")
    suspend fun deleteUser(Username: String)
}