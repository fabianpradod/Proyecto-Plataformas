package com.proyectoplataformas.appguatemala.data.repository

import androidx.room.util.query
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.proyectoplataformas.appguatemala.data.local.dao.NoticiaDao
import com.proyectoplataformas.appguatemala.data.local.entity.mapToEntity
import com.proyectoplataformas.appguatemala.data.local.entity.mapToModel
import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.model.Noticia
import com.proyectoplataformas.appguatemala.data.network.dto.noticia.NoticiaDto
import com.proyectoplataformas.appguatemala.data.network.dto.noticia.mapToModel
import com.proyectoplataformas.appguatemala.data.source.NoticiaDb
import com.proyectoplataformas.appguatemala.domain.NoticiaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await


class FirestoreNoticiaRepository(
    firestore: FirebaseFirestore = Firebase.firestore,
    private val crashlytics: FirebaseCrashlytics = FirebaseCrashlytics.getInstance(),
    private val noticiaDao: NoticiaDao
): NoticiaRepository {

    private val noticiasCollection = firestore.collection("noticias")

    override suspend fun initialSync(): Flow<List<Noticia>> = callbackFlow {
        var query: Query = noticiasCollection

        val subscription = query.addSnapshotListener { snapshot, error ->
            if (error!= null) {
                crashlytics.recordException(error)
                cancel("Error fetching tasks: ${error.message}", error)
                return@addSnapshotListener
            }

            val noticias = snapshot?.documents?.map { doc ->
                val noticiaDto = doc.toObject(NoticiaDto::class.java) ?: NoticiaDto()
                noticiaDto.mapToModel(doc.id)
            } ?: emptyList()


            CoroutineScope(Dispatchers.IO).launch {
                if (noticiaDao.getAllNoticias().isEmpty()) {
                    noticiaDao.insertAll(noticias.map { it.mapToEntity()})
                }
            }
            trySend(noticias)
        }



        awaitClose {
            println("Cancelando flow")
            subscription.remove()
        }


    }

    override suspend fun getNoticias(): List<Noticia> {
        delay(2000L)
        return noticiaDao.getAllNoticias().map { it.mapToModel() }
    }

    override suspend fun getNoticiaById(id: String): Noticia {
        delay(2000L)
        return noticiaDao.getNoticiaById(id).mapToModel()
    }

    override suspend fun getTypeNoticias(type: CategoryType): List<Noticia> {
        delay(2000L)
        return noticiaDao.getAllTypeNoticias(type).map { it.mapToModel() }
    }
}