package android.example.sportsNews.data.api.model

import com.google.gson.annotations.SerializedName

data class SportsNewsList(
    @SerializedName("articles")
    val sportsNews: List<SportsNewsModelApi>
)