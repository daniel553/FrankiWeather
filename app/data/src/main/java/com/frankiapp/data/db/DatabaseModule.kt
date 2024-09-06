package com.frankiapp.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Database module
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): Database = Room
        .databaseBuilder(context, Database::class.java, Database.name)
        .fallbackToDestructiveMigration() // or Use migrations like db.addMigrations(MIGR_1_2)
        .build()
}