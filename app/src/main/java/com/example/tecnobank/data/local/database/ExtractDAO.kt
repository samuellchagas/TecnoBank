package com.example.tecnobank.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tecnobank.data.remote.model.extract.ExtractResponse
import com.example.tecnobank.data.remote.model.pix.ExtractEntity

@Dao
interface ExtractDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCache(extractEntity: ExtractEntity?)

    @Query("SELECT * FROM ExtractEntity")
    fun returnCache(): List<ExtractResponse>

    @Query("DELETE FROM ExtractEntity")
    fun deleteCache()

}
