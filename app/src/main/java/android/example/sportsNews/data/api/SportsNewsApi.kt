package android.example.sportsNews.data.api

import android.example.sportsNews.data.api.model.SportsNewsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsNewsApi {
    companion object {
        private const val API_KEY = "578fe33fd3374d5abd70aff595508201"
        private const val PARAMS_API_KEY = "apiKey"
        private const val PARAMS_PAGE = "page"
        private const val PARAMS_COUNTRY = "country"
        private const val VALUE_COUNTRY = "us"
        private const val PARAMS_CATEGORY = "category"
        private const val VALUE_CATEGORY = "sports"
        private const val URL_TOP_HEADLINES = "top-headlines"
    }

    @GET("$URL_TOP_HEADLINES?$PARAMS_API_KEY=$API_KEY&$PARAMS_COUNTRY=$VALUE_COUNTRY&$PARAMS_CATEGORY=$VALUE_CATEGORY")
    fun getSportsNews(
        @Query(PARAMS_PAGE) page: Int
    ): Call<SportsNewsList>
}