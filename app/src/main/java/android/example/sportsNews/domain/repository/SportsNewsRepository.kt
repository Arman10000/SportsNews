package android.example.sportsNews.domain.repository

import android.example.sportsNews.domain.item.SportsNewsItem
import androidx.lifecycle.LiveData

interface SportsNewsRepository {

    fun getSportsNewsApi(page: Int): List<SportsNewsItem>

    suspend fun addSportsNewsDB(sportsNews: List<SportsNewsItem>)

    fun getSportsNewsDB(): LiveData<List<SportsNewsItem>>

    suspend fun clearSportsNewsDB()
}