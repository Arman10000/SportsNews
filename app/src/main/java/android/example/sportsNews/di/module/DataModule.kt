package android.example.sportsNews.di.module

import android.app.Application
import android.example.sportsNews.data.repository.SportsNewsRepositoryImpl
import android.example.sportsNews.data.api.SportsNewsApi
import android.example.sportsNews.data.db.SportsNewsDao
import android.example.sportsNews.data.db.SportsNewsDatabase
import android.example.sportsNews.domain.repository.SportsNewsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun provideSportsNewsApi(): SportsNewsApi = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SportsNewsApi::class.java)

    @Provides
    fun provideSportsNewsDao(
        application: Application
    ) = SportsNewsDatabase.getInstance(application).getSportsNewsDao()

    @Provides
    fun provideSportsNewsRepository(
        sportsNewsApi: SportsNewsApi,
        sportsNewsDao: SportsNewsDao
    ): SportsNewsRepository = SportsNewsRepositoryImpl(
        sportsNewsApi,
        sportsNewsDao
    )
}