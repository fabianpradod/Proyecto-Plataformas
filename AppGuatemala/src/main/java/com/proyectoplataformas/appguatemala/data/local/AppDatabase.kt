package com.proyectoplataformas.appguatemala.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.proyectoplataformas.appguatemala.data.local.dao.NoticiaDao
import com.proyectoplataformas.appguatemala.data.local.entity.NoticiaEntity

@Database(
    entities = [
        NoticiaEntity::class
    ],
    version = 1
)
abstract class AppDatabase(): RoomDatabase() {
    abstract fun noticiaDao(): NoticiaDao
}