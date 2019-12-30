package com.example.refrigerator.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class StockedFood(
    val foodId: String,
    val name: String = "",
    val imageUrl: String = "",
    val bestByDateInterval: Int = 7,
    val expirationDateInterval: Int = 30,
    val stockedDate: Calendar = Calendar.getInstance()
){
    @PrimaryKey(autoGenerate = true)
    var stockedId: Long = 0

    //賞味期限以内か
    fun withinBestByDate(since: Calendar, purchaseDate: Calendar) =
        since > purchaseDate.apply { add(Calendar.DAY_OF_YEAR, bestByDateInterval) }

    //消費期限以内か
    fun withinExpirationDate(since: Calendar, purchaseDate: Calendar) =
        since > purchaseDate.apply { add(Calendar.DAY_OF_YEAR, expirationDateInterval) }

}
