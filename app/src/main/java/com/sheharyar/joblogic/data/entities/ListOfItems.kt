package com.sheharyar.joblogic.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "items")
data class ListOfItems(
    @PrimaryKey
    var id: Long,
    var name: String,
    var price: Int,
    var quantity: Int,
    var type: Int,
) : Parcelable
