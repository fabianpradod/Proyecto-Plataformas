package com.proyectoplataformas.appguatemala.data.source

import android.content.Context
import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.model.Noticia


class NoticiaDb {

    private val noticias = listOf(
        Noticia(
            type = CategoryType.Lugares,
            id = 1,
            name = "Place 1",
            smallText = "Brief description of Place 1",
            actualText1 = "Detailed information about Place 1",

    actualText2 = "Details 2",
        image = "tikal1"
        ),
        Noticia(
            type = CategoryType.Lugares,
            id = 2,
            name = "Place 2",
            smallText = "Brief description of Place 2",
            actualText1 = "Detailed information about Place 2",

    actualText2 = "Details 2",
        image = "tikal1"
        ),
        Noticia(
            type = CategoryType.Lugares,
            id = 3,
            name = "Place 3",
            smallText = "Brief description of Place 3",
            actualText1 = "Detailed information about Place 3",

    actualText2 = "Details 2",
        image = "tikal1"
        ),
        Noticia(
            type = CategoryType.Lugares,
            id = 4,
            name = "Place 4",
            smallText = "Brief description of Place 4",
            actualText1 = "Detailed information about Place 4",

    actualText2 = "Details 2",
        image = "tikal1"
        ),

        Noticia(
            type = CategoryType.Artistas,
            id = 5,
            name = "Artist 1",
            smallText = "Brief description of Artist 1",
            actualText1 = "Detailed information about Artist 1",

   actualText2 = "Details 2",
         image = "ricardo_arjona1"
        ),
        Noticia(
            type = CategoryType.Artistas,
            id = 6,
            name = "Artist 2",
            smallText = "Brief description of Artist 2",
            actualText1 = "Detailed information about Artist 2",

   actualText2 = "Details 2",
         image = "ricardo_arjona1"
        ),
        Noticia(
            type = CategoryType.Artistas,
            id = 7,
            name = "Artist 3",
            smallText = "Brief description of Artist 3",
            actualText1 = "Detailed information about Artist 3",

   actualText2 = "Details 2",
         image = "ricardo_arjona1"
        ),
        Noticia(
            type = CategoryType.Artistas,
            id = 8,
            name = "Artist 4",
            smallText = "Brief description of Artist 4",
            actualText1 = "Detailed information about Artist 4",

   actualText2 = "Details 2",
         image = "ricardo_arjona1"
        ),

        Noticia(
            type = CategoryType.Comidas,
            id = 9,
            name = "Food 1",
            smallText = "Brief description of Food 1",
            actualText1 = "Detailed information about Food 1",

     actualText2 = "Details 2",
       image = "canillita1"
        ),
        Noticia(
            type = CategoryType.Comidas,
            id = 10,
            name = "Food 2",
            smallText = "Brief description of Food 2",
            actualText1 = "Detailed information about Food 2",

     actualText2 = "Details 2",
       image = "canillita1"
        ),
        Noticia(
            type = CategoryType.Comidas,
            id = 11,
            name = "Food 3",
            smallText = "Brief description of Food 3",
            actualText1 = "Detailed information about Food 3",

     actualText2 = "Details 2",
       image = "canillita1"
        ),
        Noticia(
            type = CategoryType.Comidas,
            id = 12,
            name = "Food 4",
            smallText = "Brief description of Food 4",
            actualText1 = "Detailed information about Food 4",

     actualText2 = "Details 2",
       image = "canillita1"
        ),

        Noticia(
            type = CategoryType.Instrumentos,
            id = 13,
            name = "Instrument 1",
            smallText = "Brief description of Instrument 1",
            actualText1 = "Detailed information about Instrument 1",
            actualText2 = "Details 2",

            image = "marimba1"
        ),
        Noticia(
            type = CategoryType.Instrumentos,
            id = 14,
            name = "Instrument 2",
            smallText = "Brief description of Instrument 2",
            actualText1 = "Detailed information about Instrument 2",
            actualText2 = "Details 2",

            image = "marimba1"
        ),
        Noticia(
            type = CategoryType.Instrumentos,
            id = 15,
            name = "Instrument 3",
            smallText = "Brief description of Instrument 3",
            actualText1 = "Detailed information about Instrument 3",
            actualText2 = "Details 2",

            image = "marimba1"
        ),
        Noticia(
            type = CategoryType.Instrumentos,
            id = 16,
            name = "Instrument 4",
            smallText = "Brief description of Instrument 4",
            actualText1 = "Detailed information about Instrument 4",
            actualText2 = "Details 2",

            image = "marimba1"
        )
    )

    fun getAllNoticias(): List<Noticia> {
        return noticias
    }

    fun getAllTypeNoticias(type: CategoryType): List<Noticia> {
        return noticias.filter { it.type == type }
    }

    fun getNoticiaById(id: Int): Noticia {
        return noticias.first { it.id == id }
    }
}

fun getDrawableIdFromName(context: Context, imageName: String): Int {
    return context.resources.getIdentifier(imageName, "drawable", context.packageName)
}
