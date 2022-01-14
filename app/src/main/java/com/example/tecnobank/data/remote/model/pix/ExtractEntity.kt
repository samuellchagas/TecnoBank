package com.example.tecnobank.data.remote.model.pix

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExtractEntity(
    val status: String,
    val time: String,
    val type: String,
    val typeDescription: String,
    val value: String,
    val date: String){

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}