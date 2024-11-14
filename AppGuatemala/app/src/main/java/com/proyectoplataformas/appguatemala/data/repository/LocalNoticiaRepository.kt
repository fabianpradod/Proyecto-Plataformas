package com.proyectoplataformas.appguatemala.data.repository

import com.proyectoplataformas.appguatemala.data.local.dao.NoticiaDao
import com.proyectoplataformas.appguatemala.data.local.entity.mapToEntity
import com.proyectoplataformas.appguatemala.data.local.entity.mapToModel
import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.model.Noticia
import com.proyectoplataformas.appguatemala.data.source.NoticiaDb
import com.proyectoplataformas.appguatemala.domain.NoticiaRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.yield
import kotlin.coroutines.coroutineContext


class LocalNoticiaRepository(
    private val noticiaDao: NoticiaDao
): NoticiaRepository {

    override suspend fun initialSync(): Boolean {
        return try {
            println(noticiaDao.getAllNoticias().size)
            if (noticiaDao.getAllNoticias().isEmpty()) {
                val noticiaDb = NoticiaDb()
                val noticiasToInsert = noticiaDb.getAllNoticias().map { it.mapToEntity() }
                noticiaDao.insertAll(noticiasToInsert)
            }
            true
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            println(e)
            false
        }
    }

    override suspend fun getNoticias(): List<Noticia> {
        delay(2000L)
        return noticiaDao.getAllNoticias().map { it.mapToModel() }
    }

    override suspend fun getNoticiaById(id: Int): Noticia {
        delay(2000L)
        return noticiaDao.getNoticiaById(id).mapToModel()
    }

    override suspend fun getTypeNoticias(type: CategoryType): List<Noticia> {
        delay(2000L)
        return noticiaDao.getAllTypeNoticias(type).map { it.mapToModel() }
    }
}