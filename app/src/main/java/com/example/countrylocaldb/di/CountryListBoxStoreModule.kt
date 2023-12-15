package com.example.countrylocaldb.di

import android.content.Context
import com.example.countrylocaldb.data.data_source.local.entity.MyObjectBox
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.objectbox.BoxStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountryListBoxStoreModule {

    @Provides
    @Singleton
    fun providesBoxStore(@ApplicationContext context: Context): BoxStore = MyObjectBox.builder()
        .androidContext(context)
        .build()
}