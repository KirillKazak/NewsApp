package com.myandrappin.oiu.dataKaverdram.roomKaverdarm

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myandrappin.oiu.domainKaverdram.databaseEntityKaverdarm.DatabaseEntitySaveNewsKaverdarm

@Database(
    entities = [DatabaseEntitySaveNewsKaverdarm::class],
    version = 1,
    exportSchema = true
)
abstract class DatabaseSaveNewsKaverdarm: RoomDatabase() {
    abstract fun listSavedNewsDaoKaverdarm(): DaoSavedKaverdarm
}