package com.example.employeedirectory.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import androidx.room.Room
import com.example.employeedirectory.core.dataBase.AppDataBase
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "CountryDB").build()
    }

    @Provides
    @Singleton
    fun provideEmployeeDao(appDataBase: AppDataBase) = appDataBase.getEmployeeDao()
}