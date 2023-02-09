package com.myandrappin.oiu.dataKaverdram.roomKaverdarm

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myandrappin.oiu.domainKaverdram.databaseEntityKaverdarm.DatabaseEntitySaveNewsKaverdarm
import com.myandrappin.oiu.utilKaverdram.UtilKaverdram.Companion.roomSaveTableNameKaverdram

@Dao
interface DaoSavedKaverdarm {

    @Query("SELECT * FROM $roomSaveTableNameKaverdram ORDER BY titleKaverdarm DESC")
    fun getAllSaveItemsKaverdarm(): MutableList<DatabaseEntitySaveNewsKaverdarm>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItemToSaveDatabaseKaverdarm(databaseEntityKaverdarm: DatabaseEntitySaveNewsKaverdarm)
}