package android.example.sportsNews.di.module

import android.example.sportsNews.domain.repository.SportsNewsRepository
import android.example.sportsNews.domain.useCase.SportsNewsUseCase
import android.example.sportsNews.domain.useCase.SportsNewsUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideSportsNewsUseCase(
        sportsNewsRepository: SportsNewsRepository
    ): SportsNewsUseCase = SportsNewsUseCaseImpl(sportsNewsRepository)
}