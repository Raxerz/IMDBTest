package com.raxerz.phonepemc.di

import android.content.Context
import androidx.room.Room
import com.raxerz.phonepemc.data.api.MoviesApiService
import com.raxerz.phonepemc.data.local.MovieLocalDataSource
import com.raxerz.phonepemc.data.remote.MoviesRemoteDataSource
import com.raxerz.phonepemc.data.repository.MoviesRepository
import com.raxerz.phonepemc.usecase.FetchMovies
import com.raxerz.phonepemc.usecase.FetchMoviesUseCase
import com.raxerz.phonepemc.utils.Constants
import com.raxerz.wordsearch.data.repository.local.PlaylistDao
import com.raxerz.wordsearch.data.repository.local.PlaylistDatabase
import com.raxerz.wordsearch.dispatchers.AppDispatchers
import com.raxerz.wordsearch.dispatchers.CoroutineDispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideCoroutineDispatchers() }
    single { provideMoviesApiService(get()) }
    single { provideMoviesRemoteDataSource(get()) }
    single { providePuzzleDao(get()) }
    single { providePlaylistLocalDataSource(get()) }
    single { provideMoviesRepository(get(), get()) }
    single { provideFetchMoviesUseCase(get()) }
}

fun provideCoroutineDispatchers(): CoroutineDispatchers = AppDispatchers()

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
        .callTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)

    // ToDo : Add Debug Check here
    okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)

    return okHttpClientBuilder.build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(Constants.Network.MOVIES_BASE_URL)
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY)).build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun provideMoviesApiService(retrofit: Retrofit): MoviesApiService =
    retrofit.create(MoviesApiService::class.java)

fun provideMoviesRemoteDataSource(moviesApiService: MoviesApiService): MoviesRemoteDataSource =
    MoviesRemoteDataSource(moviesApiService)


fun providePuzzleDao(context: Context): PlaylistDao {
    val playlistDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            PlaylistDatabase::class.java,
            Constants.LocalDB.PLAYLIST_DB
        )
            .fallbackToDestructiveMigration()
            .build()

    return playlistDatabase.getPlaylistDao()
}

fun providePlaylistLocalDataSource(playlistDao: PlaylistDao): MovieLocalDataSource =
    MovieLocalDataSource(playlistDao)

fun provideMoviesRepository(moviesRemoteDataSource: MoviesRemoteDataSource,movieLocalDataSource: MovieLocalDataSource): MoviesRepository =
    MoviesRepository(moviesRemoteDataSource, movieLocalDataSource)

fun provideFetchMoviesUseCase(moviesRepository: MoviesRepository): FetchMovies {
    return FetchMovies(moviesRepository)
}



