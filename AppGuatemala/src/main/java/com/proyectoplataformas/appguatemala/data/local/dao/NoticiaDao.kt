package com.proyectoplataformas.appguatemala.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.proyectoplataformas.appguatemala.data.local.entity.NoticiaEntity
import com.proyectoplataformas.appguatemala.data.model.CategoryType

@Dao
interface NoticiaDao {
    @Insert
    suspend fun insertAll(noticias: List<NoticiaEntity>)

    @Query("SELECT * FROM NoticiaEntity")
    suspend fun getAllNoticias(): List<NoticiaEntity>

    @Query("SELECT * FROM NoticiaEntity WHERE type = :type")
    suspend fun getAllTypeNoticias(type: CategoryType): List<NoticiaEntity>

    @Query("SELECT * FROM NoticiaEntity WHERE id = :id")
    suspend fun getNoticiaById(id: String): NoticiaEntity
}
