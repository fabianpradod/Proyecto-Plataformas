package com.proyectoplataformas.appguatemala.presentation.mainFlow.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.repository.FirestoreNoticiaRepository
import com.proyectoplataformas.appguatemala.di.AppDependencies
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.proyectoplataformas.appguatemala.domain.NoticiaRepository

//
//class HomeViewModel(
//    private val noticiaRepository: NoticiaRepository
//) : ViewModel() {
//
//    private var getDataJob: Job? = null
//    private val _state = MutableStateFlow(HomeState())
//    val state = _state.asStateFlow()
//
//    init {
//        getNoticias()
//    }
//
//    fun onEvent(event: HomeEvent) {
//        when (event) {
//            HomeEvent.RetryClick -> {
//                _state.update { state ->
//                    state.copy(
//                        isLoading = false,
//                        isError = true
//                    )
//                }
//                getNoticias()
//            }
//        }
//    }
//
//    private fun getNoticias() {
//        getDataJob =viewModelScope.launch {
//            _state.update { it.copy(isLoading = true, isError = false) }
//
//            val success = noticiaRepository.initialSync()
//            val lugaresNoticias = noticiaRepository.getTypeNoticias(CategoryType.Lugares)
//            val personasNoticias = noticiaRepository.getTypeNoticias(CategoryType.Personas)
//            val comidasNoticias = noticiaRepository.getTypeNoticias(CategoryType.Comidas)
//            val arteNoticias = noticiaRepository.getTypeNoticias(CategoryType.Arte)
//
//            _state.update {
//                it.copy(
//                    isLoading = false,
//                    lugaresNoticias = lugaresNoticias,
//                    personasNoticias = personasNoticias,
//                    comidasNoticias = comidasNoticias,
//                    arteNoticias = arteNoticias
//                )
//            }
//
//            else {
//                _state.update { it.copy(isLoading = false, isError = true) }
//            }
//        }
//    }
//
//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val context = checkNotNull(this[APPLICATION_KEY])
//                val appDatabase = AppDependencies.provideDatabase(context)
//                HomeViewModel(
//                    noticiaRepository = FirestoreNoticiaRepository(
//                        noticiaDao = appDatabase.noticiaDao()
//                    )
//                )
//            }
//        }
//    }
//
//}

class HomeViewModel(
    private val noticiaRepository: NoticiaRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        syncNoticias()
        loadNoticias()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.RetryClick -> {
                _state.update { state ->
                    state.copy(
                        isLoading = true,
                        isError = false
                    )
                }
                loadNoticias()
            }
        }
    }

    private fun syncNoticias() {
        viewModelScope.launch {
            noticiaRepository.initialSync().collect { noticias ->
                _state.update {
                    it.copy(
                        allNoticias = noticias,
                        isError = false
                    )
                }
            }
        }
    }

    private fun loadNoticias() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, isError = false) }

            try {
                val lugaresNoticias = noticiaRepository.getTypeNoticias(CategoryType.Lugares)
                val personasNoticias = noticiaRepository.getTypeNoticias(CategoryType.Personas)
                val comidasNoticias = noticiaRepository.getTypeNoticias(CategoryType.Comidas)
                val arteNoticias = noticiaRepository.getTypeNoticias(CategoryType.Arte)

                _state.update {
                    it.copy(
                        isLoading = false,
                        lugaresNoticias = lugaresNoticias,
                        personasNoticias = personasNoticias,
                        comidasNoticias = comidasNoticias,
                        arteNoticias = arteNoticias
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = checkNotNull(this[APPLICATION_KEY])
                val appDatabase = AppDependencies.provideDatabase(context)
                HomeViewModel(
                    noticiaRepository = FirestoreNoticiaRepository(
                        noticiaDao = appDatabase.noticiaDao()
                    )
                )
            }
        }
    }
}
