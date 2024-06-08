package com.dicoding.asclepius.data.local.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class History (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "image")
    var image: String? = null,

    @ColumnInfo(name = "prediction")
    var prediction: String? = null,

    @ColumnInfo(name = "score")
    var score: String? = null,

    @ColumnInfo(name = "created_at")
    var createdAt: String? = null
) : Parcelable