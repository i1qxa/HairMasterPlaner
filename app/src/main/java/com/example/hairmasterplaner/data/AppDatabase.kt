package com.example.hairmasterplaner.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hairmasterplaner.data.customer.CustomerItemDBModel
import com.example.hairmasterplaner.data.customer.CustomerItemDBModelDao
import com.example.hairmasterplaner.data.job.JobItemDBModel
import com.example.hairmasterplaner.data.job.JobItemDBModelDao
import com.example.hairmasterplaner.data.jobBody.JobBodyItemDBModel
import com.example.hairmasterplaner.data.jobBody.JobBodyItemDBModelDao
import com.example.hairmasterplaner.data.jobElement.JobElementItemDBModel
import com.example.hairmasterplaner.data.jobElement.JobElementItemDBModelDao
import com.example.hairmasterplaner.data.PriceRegister.PriceRegisterDao
import com.example.hairmasterplaner.data.PriceRegister.PriceRegisterItemDBModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [
        CustomerItemDBModel::class,
        JobItemDBModel::class,
        JobBodyItemDBModel::class,
        JobElementItemDBModel::class,
        PriceRegisterItemDBModel::class,
        ],
    version = 1, exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun customerItemDBModelDao(): CustomerItemDBModelDao
    abstract fun jobItemDBModelDao(): JobItemDBModelDao
    abstract fun jobBodyItemDBModelDao(): JobBodyItemDBModelDao
    abstract fun jobElementItemDBModelDao(): JobElementItemDBModelDao
    abstract fun jobElementPriceRegisterDao(): PriceRegisterDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "beauty_master_db"

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
}