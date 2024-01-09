package com.example.cwtproject.di

import com.example.cwtproject.data.remote.CurrencyApi
import com.example.cwtproject.data.repository.CurrencyRepositoryImpl
import com.example.cwtproject.domain.repository.CurrencyRepository
import com.example.cwtproject.domain.use_case.FilterCurrencyUseCase
import com.example.cwtproject.domain.use_case.MapResponseToListUseCase
import com.example.cwtproject.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    @Singleton
    fun provideApiBuilder(): CurrencyApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(CurrencyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(api: CurrencyApi): CurrencyRepository {
        return CurrencyRepositoryImpl(api)
    }

    @Provides
    fun provideMapResponseToListUseCase(): MapResponseToListUseCase {
        return MapResponseToListUseCase()
    }

    @Provides
    fun provideFilterCurrencyUseCase(): FilterCurrencyUseCase {
        return FilterCurrencyUseCase()
    }
}