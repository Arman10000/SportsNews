package android.example.sportsNews.data.api.model

import com.google.gson.annotations.SerializedName

data class SportsNewsModelApi(
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("urlToImage")
    val imagePath: String?
)