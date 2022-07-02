package com.mcmouse88.harrypotter.data.di

import android.app.Application
import android.content.Context
import com.mcmouse88.harrypotter.data.network.ApiModel
import com.mcmouse88.harrypotter.data.repository.UseCaseRepositoryImpl
import com.mcmouse88.harrypotter.data.room.repository.RoomRepository
import com.mcmouse88.harrypotter.domain.usecaserepository.UseCaseRepository
import com.mcmouse88.harrypotter.presentation.fragments.DetailFragment
import com.mcmouse88.harrypotter.presentation.fragments.FavoriteFragment
import com.mcmouse88.harrypotter.presentation.fragments.MainFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ApiModel::class, AppModule::class])
interface AppComponent {
    fun inject(fragment: MainFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: DetailFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}