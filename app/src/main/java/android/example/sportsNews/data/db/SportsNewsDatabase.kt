package android.example.sportsNews.data.db

import android.app.Application
import android.example.sportsNews.data.db.model.SportsNewsModelDB
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [SportsNewsModelDB::class],
    version = 1,
    exportSchema = false
)
abstract class SportsNewsDatabase : RoomDatabase() {
    abstract fun getSportsNewsDao(): SportsNewsDao

    companion object {
        private const val DB_NAME = "sportsNews.db"

        fun getInstance(
            application: Application
        ): SportsNewsDatabase = Room.databaseBuilder(application, SportsNewsDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}