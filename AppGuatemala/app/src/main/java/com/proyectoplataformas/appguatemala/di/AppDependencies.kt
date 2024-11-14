package com.proyectoplataformas.appguatemala.di


import android.content.Context
import com.proyectoplataformas.appguatemala.data.local.AppDatabase
import com.proyectoplataformas.appguatemala.data.local.AppDatabaseFactory


object AppDependencies {
    private var database: AppDatabase? = null

    fun provideDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            database ?: AppDatabaseFactory.create(context).also { database = it }
        }
    }
}