package android.example.sportsNews.presentation.adapter

import android.example.sportsNews.databinding.ItemSportsNewsBinding
import android.example.sportsNews.domain.item.SportsNewsItem
import android.example.sportsNews.presentation.adapter.callback.SportsNewsItemDiffCallback
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class SportsNewsAdapter :
    ListAdapter<SportsNewsItem, SportsNewsAdapter.SportsNewsViewHolder>(SportsNewsItemDiffCallback()) {

    class SportsNewsViewHolder(
        private val binding: ItemSportsNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(sportsNewsItem: SportsNewsItem) {
            Picasso.get()
                .load(sportsNewsItem.imagePath)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(binding.image)
            binding.title.text = sportsNewsItem.title
            binding.description.text = sportsNewsItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsNewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSportsNewsBinding.inflate(layoutInflater, parent, false)
        return SportsNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SportsNewsViewHolder, position: Int) {
        holder.bind(sportsNewsItem = getItem(position))
    }
}