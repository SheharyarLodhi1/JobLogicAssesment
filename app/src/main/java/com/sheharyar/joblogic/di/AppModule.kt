package com.sheharyar.joblogic.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sheharyar.joblogic.data.local.AppDatabase
import com.sheharyar.joblogic.data.local.JobLogicDao
import com.sheharyar.joblogic.data.remote.NyRemoteDataSource
import com.sheharyar.joblogic.data.remote.NyService
import com.sheharyar.joblogic.data.repository.JobLogicRepository
import com.sheharyar.joblogic.utils.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(AppConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun ProvideNyService(retrofit: Retrofit) : NyService = retrofit.create(NyService::class.java)

    @Singleton
    @Provides
    fun provideNyRemoteDataSource(nyService: NyService) = NyRemoteDataSource(nyService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideNewsListDao(db: AppDatabase) = db.newsListDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: NyRemoteDataSource,
                          localDataSource: JobLogicDao) =
        JobLogicRepository(remoteDataSource, localDataSource)
}