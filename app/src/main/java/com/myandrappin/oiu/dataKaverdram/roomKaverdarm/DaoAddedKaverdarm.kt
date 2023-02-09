package com.myandrappin.oiu.dataKaverdram.roomKaverdarm

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myandrappin.oiu.domainKaverdram.databaseEntityKaverdarm.DatabaseEntityAddNewsKaverdarm
import com.myandrappin.oiu.utilKaverdram.UtilKaverdram

@Dao
interface DaoAddedKaverdarm {
    @Query("SELECT * FROM ${UtilKaverdram.roomAddTableNameKaverdram} ORDER BY titleKaverdarm DESC")
    fun getAllAddItemsKaverdarm(): MutableList<DatabaseEntityAddNewsKaverdarm>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItemToAddDatabaseKaverdarm(databaseEntityAddNewsKaverdarm: DatabaseEntityAddNewsKaverdarm)
}