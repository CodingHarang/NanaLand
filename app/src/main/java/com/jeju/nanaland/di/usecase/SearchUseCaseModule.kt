package com.jeju.nanaland.di.usecase

import com.jeju.nanaland.domain.repository.SearchRepository
import com.jeju.nanaland.domain.usecase.search.GetAllSearchResultListUseCase
import com.jeju.nanaland.domain.usecase.search.GetExperienceSearchResultListUseCase
import com.jeju.nanaland.domain.usecase.search.GetFestivalSearchResultListUseCase
import com.jeju.nanaland.domain.usecase.search.GetHotPostsUseCase
import com.jeju.nanaland.domain.usecase.search.GetMarketSearchResultListUseCase
import com.jeju.nanaland.domain.usecase.search.GetNatureSearchResultListUseCase
import com.jeju.nanaland.domain.usecase.search.GetTopKeywordsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SearchUseCaseModule {

    // 검색량 UP 게시물 조회
    @Singleton
    @Provides
    fun provideGetHotPostsUseCase(
        repository: SearchRepository
    ): GetHotPostsUseCase {
        return GetHotPostsUseCase(repository)
    }

    // 인기 검색어 8개 조회
    @Singleton
    @Provides
    fun provideGetTopKeywordsUseCase(
        repository: SearchRepository
    ): GetTopKeywordsUseCase {
        return GetTopKeywordsUseCase(repository)
    }

    // 자연 검색 결과
    @Singleton
    @Provides
    fun provideGetNatureSearchResultListUseCase(
        repository: SearchRepository
    ): GetNatureSearchResultListUseCase {
        return GetNatureSearchResultListUseCase(repository)
    }

    // 전통시장 검색 결과
    @Singleton
    @Provides
    fun provideGetMarketSearchResultListUseCase(
        repository: SearchRepository
    ): GetMarketSearchResultListUseCase {
        return GetMarketSearchResultListUseCase(repository)
    }

    // 축제 검색 결과
    @Singleton
    @Provides
    fun provideGetFestivalSearchResultListUseCase(
        repository: SearchRepository
    ): GetFestivalSearchResultListUseCase {
        return GetFestivalSearchResultListUseCase(repository)
    }

    // 이색체험 검색 결과
    @Singleton
    @Provides
    fun provideGetExperienceSearchResultListUseCase(
        repository: SearchRepository
    ): GetExperienceSearchResultListUseCase {
        return GetExperienceSearchResultListUseCase(repository)
    }

    // 전체 카테고리 검색 결과 2개씩
    @Singleton
    @Provides
    fun provideGetAllSearchResultListUseCase(
        repository: SearchRepository
    ): GetAllSearchResultListUseCase {
        return GetAllSearchResultListUseCase(repository)
    }
}