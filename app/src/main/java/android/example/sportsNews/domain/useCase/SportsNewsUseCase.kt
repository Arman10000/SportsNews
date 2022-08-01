package android.example.sportsNews.domain.useCase

import android.example.sportsNews.domain.item.SportsNewsItem
import androidx.lifecycle.LiveData

interface SportsNewsUseCase {

    suspend fun startLoadingSportsNews(page: Int)

    fun getSportsNewsDB(): LiveData<List<SportsNewsItem>>
}