package com.sheharyar.joblogic.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CallListModel(
    var id: Int,
    var name: String,
    var number: String
) : Parcelable