package android.example.sportsNews.data.db

import android.example.sportsNews.data.db.model.SportsNewsModelDB
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SportsNewsDao {

    @Insert
    suspend fun addSportsNews(sportsNews: List<SportsNewsModelDB>)

    @Query("SELECT * FROM sportsNews")
    fun getSportsNews(): LiveData<List<SportsNewsModelDB>>

    @Query("DELETE FROM sportsNews")
    suspend fun clearSportsNews()
}