package android.example.sportsNews.data.repository

import android.example.sportsNews.data.api.SportsNewsApi
import android.example.sportsNews.data.db.SportsNewsDao
import android.example.sportsNews.data.mapper.SportsNewsMapper
import android.example.sportsNews.domain.repository.SportsNewsRepository
import android.example.sportsNews.domain.item.SportsNewsItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

class SportsNewsRepositoryImpl(
    private val sportsNewsApi: SportsNewsApi,
    private val sportsNewsDao: SportsNewsDao
) : SportsNewsRepository {

    override fun getSportsNewsApi(
        page: Int
    ): List<SportsNewsItem> = SportsNewsMapper.mapListSportsNewsModelApiToListSportsNewsItem(
        sportsNewsApi.getSportsNews(page).execute()
    )

    override suspend fun addSportsNewsDB(sportsNews: List<SportsNewsItem>) {
        sportsNewsDao.addSportsNews(
            SportsNewsMapper.mapListSportsNewsItemToListEntity(sportsNews)
        )
    }

    override fun getSportsNewsDB(): LiveData<List<SportsNewsItem>> {
        return Transformations.map(
            sportsNewsDao.getSportsNews()
        ) {
            SportsNewsMapper.mapListEntityToListSportsNewsItem(it)
        }
    }

    override suspend fun clearSportsNewsDB() {
        sportsNewsDao.clearSportsNews()
    }
}