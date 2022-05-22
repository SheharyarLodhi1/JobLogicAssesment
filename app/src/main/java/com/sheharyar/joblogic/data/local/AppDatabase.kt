package com.sheharyar.joblogic.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sheharyar.joblogic.data.entities.ItemToSell
import com.sheharyar.joblogic.data.entities.ListOfItems

@Database(entities = [ListOfItems::class], version = 6, exportSchema = false)
//@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsListDao(): JobLogicDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "items")
                .fallbackToDestructiveMigration()
                .build()
    }

}