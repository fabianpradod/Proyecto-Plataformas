//package com.proyectoplataformas.appguatemala.data.network.dto.noticia
//
//import com.proyectoplataformas.appguatemala.data.local.entity.NoticiaEntity
//import com.proyectoplataformas.appguatemala.data.model.CategoryType
//import kotlinx.serialization.Serializable
//
//@Serializable
//data class NoticiaDto(
//    val id: Int,
//    val type: CategoryType,
//    val name: String,
//    val smallText: String,
//    val actualText1: String,
//    val actualText2: String,
//    val image: String
//)
//
//fun NoticiaDto.toEntity(): NoticiaEntity {
//    return NoticiaEntity(
//        id = id,
//        type = type,
//        name = name,
//        smallText = smallText,
//        actualText1 = actualText1,
//        actualText2 = actualText2,
//        image = image
//    )
//}