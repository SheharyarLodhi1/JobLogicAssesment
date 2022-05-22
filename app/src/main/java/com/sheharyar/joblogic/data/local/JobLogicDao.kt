package com.sheharyar.joblogic.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sheharyar.joblogic.data.entities.ItemToSell
import com.sheharyar.joblogic.data.entities.ListOfItems

@Dao
interface JobLogicDao {
    @Query("SELECT * FROM items")
    fun getData() : LiveData<List<ListOfItems>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<ListOfItems>)
}