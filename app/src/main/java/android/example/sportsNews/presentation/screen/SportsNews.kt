package android.example.sportsNews.presentation.screen

import android.content.res.Resources
import android.example.sportsNews.R
import android.example.sportsNews.databinding.SportsNewsBinding
import android.example.sportsNews.di.App
import android.example.sportsNews.presentation.adapter.SportsNewsAdapter
import android.example.sportsNews.presentation.viewModel.SportsNewsItemViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class SportsNews : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SportsNewsItemViewModel
    private lateinit var binding: SportsNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).sportsNewsComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = SportsNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.error.observe(this) {
            Snackbar.make(binding.root, R.string.error_internet, Snackbar.LENGTH_SHORT).show()
            stopRefreshLayout()
        }

        viewModel.progressBar.observe(this) {
            binding.progressBar.isVisible = it
        }

        val moviesAdapter = SportsNewsAdapter()

        viewModel.sportsNews.observe(this) {
            if (it.isNotEmpty()) {
                moviesAdapter.submitList(it)
                stopRefreshLayout()
            }
        }

        binding.movies.layoutManager = GridLayoutManager(this, getColumnCount())
        binding.movies.adapter = moviesAdapter
        binding.movies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val showItemCount = layoutManager.findLastVisibleItemPosition()

                viewModel.loadingSportsNewsIfEndList(
                    totalItemCount,
                    visibleItemCount,
                    showItemCount
                )
            }
        })

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refreshLayout()
        }

        viewModel.ifFirstStartSportsNews()
    }

    private fun stopRefreshLayout() {
        binding.refreshLayout.isRefreshing = false
    }

    private fun getColumnCount(): Int {
        val displayMetrics = Resources.getSystem().displayMetrics
        val width = (displayMetrics.widthPixels / displayMetrics.density).toInt()
        val posterSize = 210
        return if (width / posterSize > 2) width / posterSize else 2
    }
}