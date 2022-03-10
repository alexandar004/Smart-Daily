package com.example.dailysmarts.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dailysmarts.Constant.Companion.DATABASE_NAME
import com.example.dailysmarts.api.Quote

@Database(entities = [Quote::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as AppDatabase
        }
    }

    abstract fun quoteDao(): QuoteDao
}