package com.proyectoplataformas.appguatemala.domain

import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.model.Noticia
import kotlinx.coroutines.flow.Flow

interface NoticiaRepository {
    suspend fun initialSync(): Flow<List<Noticia>>
    suspend fun getNoticias(): List<Noticia>
    suspend fun getNoticiaById(id: String): Noticia
    suspend fun getTypeNoticias(type: CategoryType): List<Noticia>
}

