package com.myandrappin.oiu.dataKaverdram.roomKaverdarm

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myandrappin.oiu.domainKaverdram.databaseEntityKaverdarm.DatabaseEntityAddNewsKaverdarm

@Database(
    entities = [DatabaseEntityAddNewsKaverdarm::class],
    version = 1,
    exportSchema = true
)
abstract class DatabaseAddNewsKaverdarm: RoomDatabase() {
    abstract fun listAddedNewsDaoKaverdarm(): DaoAddedKaverdarm
}