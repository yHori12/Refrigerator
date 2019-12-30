package com.example.refrigerator.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class Food(
    @PrimaryKey @ColumnInfo(name = "id")
    val foodId: String,
    val name: String,
    val description: String,
    val stockReserveCount: Int = 0,
    val imageUrl: String = ""
)
