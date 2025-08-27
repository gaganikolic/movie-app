package com.example.myapplication.di

import androidx.room.Room
import com.example.myapplication.FavoriteRepository
import com.example.myapplication.IFavoriteRepository
import com.example.myapplication.IMovieRepository
import com.example.myapplication.MovieRepository
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.database.FavoriteMovieDao
import com.example.myapplication.details_screen.DetailsViewModel
import com.example.myapplication.example.IMyService
import com.example.myapplication.example.MyService
import com.example.myapplication.favorites_screen.FavoritesViewModel
import com.example.myapplication.home_screen.HomeViewModel
import com.example.myapplication.setupKtorClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    singleOf(::setupKtorClient)


    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "movies.db"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    single<FavoriteMovieDao> {
        val database = get<AppDatabase>()
        database.favoriteMovieDao()
    }
    single<IMovieRepository> { MovieRepository(get()) }
    single<IFavoriteRepository> { FavoriteRepository(get()) }

    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailsViewModel)
    viewModelOf(::FavoritesViewModel)

    single<IMyService> { MyService() }
}