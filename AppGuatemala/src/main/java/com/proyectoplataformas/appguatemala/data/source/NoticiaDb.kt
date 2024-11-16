package com.proyectoplataformas.appguatemala.data.source

import android.content.Context
import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.model.Noticia


class NoticiaDb {

    private val noticias: List<Noticia> = emptyList()

    fun getAllNoticias(): List<Noticia> {
        return noticias
    }

    fun getAllTypeNoticias(type: CategoryType): List<Noticia> {
        return noticias.filter { it.type == type }
    }

    fun getNoticiaById(id: String): Noticia {
        return noticias.first { it.id.contentEquals(id) }
    }
}

fun getDrawableIdFromName(context: Context, imageName: String): Int {
    return context.resources.getIdentifier(imageName, "drawable", context.packageName)
}
