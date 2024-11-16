//package com.proyectoplataformas.appguatemala.data.network
//
//import com.proyectoplataformas.appguatemala.data.network.dto.noticia.NoticiaListDto
//import com.proyectoplataformas.appguatemala.data.network.util.safeCall
//import com.proyectoplataformas.appguatemala.domain.network.util.Result
//import com.proyectoplataformas.appguatemala.domain.network.GtApi
//import com.proyectoplataformas.appguatemala.domain.network.util.NetworkError
//import io.ktor.client.HttpClient
//import io.ktor.client.request.get
//
//class KtorGtApi(
//    private val httpClient: HttpClient
//) : GtApi {
//    override suspend fun getAllNoticias(): Result<NoticiaListDto, NetworkError> {
//        return safeCall {
//            httpClient.get("https://rickandmortyapi.com/api/character?limit=20")
//        }
//    }
//}
