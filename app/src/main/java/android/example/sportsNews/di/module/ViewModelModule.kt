package android.example.sportsNews.di.module

import android.example.sportsNews.di.annotation.ApplicationScope
import android.example.sportsNews.domain.useCase.SportsNewsUseCase
import android.example.sportsNews.presentation.viewModel.SportsNewsItemViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @ApplicationScope
    @Provides
    fun provideSportsNewsViewModel(
        sportsNewsUseCase: SportsNewsUseCase
    ) = SportsNewsItemViewModel(sportsNewsUseCase)
}