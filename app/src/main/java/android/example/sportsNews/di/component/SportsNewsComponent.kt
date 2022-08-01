package android.example.sportsNews.di.component

import android.app.Application
import android.example.sportsNews.di.annotation.ApplicationScope
import android.example.sportsNews.di.module.DataModule
import android.example.sportsNews.di.module.DomainModule
import android.example.sportsNews.di.module.ViewModelModule
import android.example.sportsNews.presentation.screen.SportsNews
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class
    ]
)
interface SportsNewsComponent {

    fun inject(sportsNews: SportsNews)

    @Component.Factory
    interface ComponentFactory {
        fun create(
            @BindsInstance application: Application
        ): SportsNewsComponent
    }
}