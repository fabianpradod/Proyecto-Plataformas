package com.proyectoplataformas.appguatemala

enum class CategoryType {
    Lugares,
    Artistas,
    Comidas,
    Instrumentos
}

data class Noticia(
    val type: CategoryType,
    val id: Int,
    val name: String,
    val smallText: String,
    val actualText: String,
    val image: String
)

private val noticias = listOf(
    Noticia(type = CategoryType.Lugares, id = 1, name = "Place 1", smallText = "Brief description of Place 1", actualText = "Detailed information about Place 1", image = "tikal1"),
    Noticia(type = CategoryType.Lugares, id = 2, name = "Place 2", smallText = "Brief description of Place 2", actualText = "Detailed information about Place 2", image = "tikal1"),
    Noticia(type = CategoryType.Lugares, id = 3, name = "Place 3", smallText = "Brief description of Place 3", actualText = "Detailed information about Place 3", image = "tikal1"),
    Noticia(type = CategoryType.Lugares, id = 4, name = "Place 4", smallText = "Brief description of Place 4", actualText = "Detailed information about Place 4", image = "tikal1"),

    Noticia(type = CategoryType.Artistas, id = 1, name = "Artist 1", smallText = "Brief description of Artist 1", actualText = "Detailed information about Artist 1", image = "ricardo_arjona1"),
    Noticia(type = CategoryType.Artistas, id = 2, name = "Artist 2", smallText = "Brief description of Artist 2", actualText = "Detailed information about Artist 2", image = "ricardo_arjona1"),
    Noticia(type = CategoryType.Artistas, id = 3, name = "Artist 3", smallText = "Brief description of Artist 3", actualText = "Detailed information about Artist 3", image = "ricardo_arjona1"),
    Noticia(type = CategoryType.Artistas, id = 4, name = "Artist 4", smallText = "Brief description of Artist 4", actualText = "Detailed information about Artist 4", image = "ricardo_arjona1"),

    Noticia(type = CategoryType.Comidas, id = 1, name = "Food 1", smallText = "Brief description of Food 1", actualText = "Detailed information about Food 1", image = "canillita1"),
    Noticia(type = CategoryType.Comidas, id = 2, name = "Food 2", smallText = "Brief description of Food 2", actualText = "Detailed information about Food 2", image = "canillita1"),
    Noticia(type = CategoryType.Comidas, id = 3, name = "Food 3", smallText = "Brief description of Food 3", actualText = "Detailed information about Food 3", image = "canillita1"),
    Noticia(type = CategoryType.Comidas, id = 4, name = "Food 4", smallText = "Brief description of Food 4", actualText = "Detailed information about Food 4", image = "canillita1"),

    Noticia(type = CategoryType.Instrumentos, id = 1, name = "Instrument 1", smallText = "Brief description of Instrument 1", actualText = "Detailed information about Instrument 1", image = "instrumento1"),
    Noticia(type = CategoryType.Instrumentos, id = 2, name = "Instrument 2", smallText = "Brief description of Instrument 2", actualText = "Detailed information about Instrument 2", image = "instrumento1"),
    Noticia(type = CategoryType.Instrumentos, id = 3, name = "Instrument 3", smallText = "Brief description of Instrument 3", actualText = "Detailed information about Instrument 3", image = "instrumento1"),
    Noticia(type = CategoryType.Instrumentos, id = 4, name = "Instrument 4", smallText = "Brief description of Instrument 4", actualText = "Detailed information about Instrument 4", image = "instrumento1")

)
fun getAllNoticias(): List<Noticia> {
    return noticias
}

fun getAllTypeNoticias(type: CategoryType): List<Noticia> {
    return noticias.filter { it.type == type }
}

fun getNoticiaByTypeAndId(type: CategoryType, id: Int): Noticia {
    return noticias.first { it.type == type && it.id == id }
}
