package android.example.sportsNews.presentation.adapter.callback

import android.example.sportsNews.domain.item.SportsNewsItem
import androidx.recyclerview.widget.DiffUtil

class SportsNewsItemDiffCallback : DiffUtil.ItemCallback<SportsNewsItem>() {

    override fun areItemsTheSame(oldItem: SportsNewsItem, newItem: SportsNewsItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: SportsNewsItem, newItem: SportsNewsItem): Boolean {
        return true
    }
}