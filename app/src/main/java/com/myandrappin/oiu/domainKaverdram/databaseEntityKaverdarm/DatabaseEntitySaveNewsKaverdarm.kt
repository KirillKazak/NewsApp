package com.myandrappin.oiu.domainKaverdram.databaseEntityKaverdarm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myandrappin.oiu.utilKaverdram.UtilKaverdram.Companion.roomSaveTableNameKaverdram

@Entity(tableName = roomSaveTableNameKaverdram)
data class DatabaseEntitySaveNewsKaverdarm (
    @PrimaryKey @ColumnInfo(name = "titleKaverdarm")
    val titleKaverdarm: String,

    @ColumnInfo(name = "imageKaverdarm")
    val imageKaverdarm: String,

    @ColumnInfo(name = "dateKaverdarm")
    val dateKaverdarm: String,

    @ColumnInfo(name = "descriptionKaverdarm")
    val descriptionKaverdarm: String,

    @ColumnInfo(name = "contentKaverdarm")
    val contentKaverdarm: String,

    @ColumnInfo(name = "boolIsSaveKaverdarm")
    val boolIsSaveKaverdarm: Boolean,
        )