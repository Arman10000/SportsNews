package android.example.sportsNews.domain.useCase

import android.example.sportsNews.domain.repository.SportsNewsRepository
import android.example.sportsNews.domain.item.SportsNewsItem
import android.util.Log
import androidx.lifecycle.LiveData

class SportsNewsUseCaseImpl(
    private val sportsNewsRepository: SportsNewsRepository
) : SportsNewsUseCase {

    override suspend fun startLoadingSportsNews(page: Int) {
        val sportsNews = sportsNewsRepository.getSportsNewsApi(page)

        if (sportsNews.isNotEmpty()) {
            if (page == 1) sportsNewsRepository.clearSportsNewsDB()

            val newList = arrayListOf<SportsNewsItem>()

            sportsNews.forEach {
                if (it.title != null &&
                    it.description != null &&
                    it.imagePath != null) newList.add(it)
            }
            sportsNewsRepository.addSportsNewsDB(sportsNews)
        }
    }

    override fun getSportsNewsDB(): LiveData<List<SportsNewsItem>> =
        sportsNewsRepository.getSportsNewsDB()
}