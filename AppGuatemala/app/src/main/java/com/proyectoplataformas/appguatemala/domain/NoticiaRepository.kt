package com.proyectoplataformas.appguatemala.domain

import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.model.Noticia

interface NoticiaRepository {
    suspend fun initialSync(): Boolean
    suspend fun getNoticias(): List<Noticia>
    suspend fun getNoticiaById(id: Int): Noticia
    suspend fun getTypeNoticias(type: CategoryType): List<Noticia>
}

