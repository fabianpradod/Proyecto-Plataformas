package com.proyectoplataformas.appguatemala.presentation.mainFlow.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.repository.LocalNoticiaRepository
import com.proyectoplataformas.appguatemala.di.AppDependencies
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.proyectoplataformas.appguatemala.domain.NoticiaRepository


class HomeViewModel(
    private val noticiaRepository: NoticiaRepository
) : ViewModel() {

    private var getDataJob: Job? = null
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getNoticias()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.ForceError -> {
                getDataJob?.cancel()
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
            HomeEvent.RetryClick -> {
                getNoticias()
            }
        }
    }

    private fun getNoticias() {
        getDataJob =viewModelScope.launch {
            _state.update { it.copy(isLoading = true, isError = false) }

            val success = noticiaRepository.initialSync()
            println(success)

            if (success) {
                val lugaresNoticias = noticiaRepository.getTypeNoticias(CategoryType.Lugares)
                val artistasNoticias = noticiaRepository.getTypeNoticias(CategoryType.Artistas)
                val comidasNoticias = noticiaRepository.getTypeNoticias(CategoryType.Comidas)
                val instrumentosNoticias = noticiaRepository.getTypeNoticias(CategoryType.Instrumentos)

                _state.update {
                    it.copy(
                        isLoading = false,
                        lugaresNoticias = lugaresNoticias,
                        artistasNoticias = artistasNoticias,
                        comidasNoticias = comidasNoticias,
                        instrumentosNoticias = instrumentosNoticias
                    )
                }
            }
            else {
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
                    noticiaRepository = LocalNoticiaRepository(
                        noticiaDao = appDatabase.noticiaDao()
                    )
                )
            }
        }
    }

}