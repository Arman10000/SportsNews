package android.example.sportsNews.data.mapper

import android.example.sportsNews.data.api.model.SportsNewsList
import android.example.sportsNews.data.db.model.SportsNewsModelDB
import android.example.sportsNews.domain.item.SportsNewsItem
import android.util.Log
import retrofit2.Response

class SportsNewsMapper {

    companion object {

        fun mapListSportsNewsModelApiToListSportsNewsItem(
            response: Response<SportsNewsList>
        ): List<SportsNewsItem> {
            response.body()?.let {
                return it.sportsNews.map { sportsNewsModelApi ->
                    SportsNewsItem(
                        sportsNewsModelApi.title,
                        sportsNewsModelApi.description,
                        sportsNewsModelApi.imagePath
                    )
                }
            }
            return listOf()
        }

        fun mapListSportsNewsItemToListEntity(
            sportsNews: List<SportsNewsItem>
        ): List<SportsNewsModelDB> = sportsNews.map {
            SportsNewsModelDB(
                it.title,
                it.description,
                it.imagePath
            )
        }

        fun mapListEntityToListSportsNewsItem(
            sportsNews: List<SportsNewsModelDB>
        ): List<SportsNewsItem> = sportsNews.map {
            SportsNewsItem(
                it.title,
                it.description,
                it.imagePath
            )
        }
    }
}