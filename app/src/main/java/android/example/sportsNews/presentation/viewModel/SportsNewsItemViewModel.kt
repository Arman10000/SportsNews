package android.example.sportsNews.presentation.viewModel

import android.example.sportsNews.domain.item.SportsNewsItem
import android.example.sportsNews.domain.useCase.SportsNewsUseCase
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SportsNewsItemViewModel(
    private val sportsNewsUseCase: SportsNewsUseCase
) : ViewModel() {

    private var _error: MutableLiveData<Boolean> = MutableLiveData()
    val error: LiveData<Boolean> = _error
    private var _progressBar: MutableLiveData<Boolean> = MutableLiveData()
    val progressBar: LiveData<Boolean> = _progressBar
    val sportsNews: LiveData<List<SportsNewsItem>> = sportsNewsUseCase.getSportsNewsDB()
    private var isLoading = false
    private var isErrorInternet = false
    private var page = 1
    private var firstStart = true

    private val exceptionHandler = CoroutineExceptionHandler { w, w2 ->
        _error.postValue(true)
        isErrorInternet = true
        _progressBar.postValue(false)
        Log.d("wow", "$w2")
    }

    private fun startLoadingSportsNews() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _progressBar.postValue(true)
            isLoading = true
            sportsNewsUseCase.startLoadingSportsNews(page)
            isErrorInternet = false
            isLoading = false
            _progressBar.postValue(false)
        }
    }

    fun ifFirstStartSportsNews() {
        if (firstStart) {
            firstStart = false
            startLoadingSportsNews()
        }
    }

    fun loadingSportsNewsIfEndList(
        totalItemCount: Int,
        visibleItemCount: Int,
        showItemCount: Int
    ) {
        if (
            showItemCount > 0 &&
            (visibleItemCount + showItemCount) >= totalItemCount &&
            !isLoading
        ) {
            if (!isErrorInternet) page++
            startLoadingSportsNews()
        }
    }

    fun refreshLayout() {
        page = 1
        startLoadingSportsNews()
    }
}