package android.example.sportsNews.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sportsNews")
data class SportsNewsModelDB(
    val title: String?,
    val description: String?,
    val imagePath: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
