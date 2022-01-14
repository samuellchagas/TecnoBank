package com.example.tecnobank.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tecnobank.data.remote.model.pix.ExtractEntity


@Database(entities = [ExtractEntity::class], version = 1, exportSchema = false)

abstract class ExtractDatabase : RoomDatabase() {

    abstract fun extractDAO(): ExtractDAO

    companion object {

        private const val NAME_DB = "ExtractCache"

        fun getInstance(context: Context?): ExtractDatabase {
            return Room
                .databaseBuilder(context!!, ExtractDatabase::class.java, NAME_DB)
                .build()
        }

    }

}
