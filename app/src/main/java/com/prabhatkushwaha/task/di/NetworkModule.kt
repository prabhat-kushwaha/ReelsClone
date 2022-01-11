package com.prabhatkushwaha.task.di

import com.prabhatkushwaha.task.business.data.network.abs.ReelsNetworkDataSource
import com.prabhatkushwaha.task.business.data.network.impl.ReelsNetworkDataSourceImpl
import com.prabhatkushwaha.task.business.interactors.reels.GetReelsInteractor
import com.prabhatkushwaha.task.framework.datasource.network.abs.ReelsNetworkService
import com.prabhatkushwaha.task.framework.datasource.network.impl.ReelsNetworkServiceImpl
import com.prabhatkushwaha.task.framework.datasource.network.service.ReelService
import com.prabhatkushwaha.task.framework.datasource.network.utils.ReelsMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(value = [SingletonComponent::class])
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit {
        return Retrofit.Builder().baseUrl("https://max-masti.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideReelService(retrofit: Retrofit): ReelService {
        return retrofit.create(ReelService::class.java)
    }

    @Provides
    @Singleton
    fun provideReelMapper(): ReelsMapper {
        return ReelsMapper()
    }

    @Provides
    @Singleton
    fun provideNetworkService(
        reelService: ReelService,
        reelsMapper: ReelsMapper
    ): ReelsNetworkService {
        return ReelsNetworkServiceImpl(reelService, reelsMapper)
    }

    @Provides
    @Singleton
    fun provideNetworkDataSource(
        reelService: ReelsNetworkService,
    ): ReelsNetworkDataSource {
        return ReelsNetworkDataSourceImpl(reelService)
    }

    @Provides
    @Singleton
    fun provideReelInteractor(
        reelService: ReelsNetworkDataSource,
    ): GetReelsInteractor {
        return GetReelsInteractor(reelService)
    }
}