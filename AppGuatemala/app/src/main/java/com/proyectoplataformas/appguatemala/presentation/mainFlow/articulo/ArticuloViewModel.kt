package com.proyectoplataformas.appguatemala.presentation.mainFlow.articulo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.toRoute
import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.repository.LocalNoticiaRepository
import com.proyectoplataformas.appguatemala.di.AppDependencies
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.proyectoplataformas.appguatemala.domain.NoticiaRepository

class ArticuloViewModel(
    private val noticiaRepository: NoticiaRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val articulo = savedStateHandle.toRoute<ArticuloDestination>()
    private val _state: MutableStateFlow<ArticuloState> = MutableStateFlow(ArticuloState())
    val state = _state.asStateFlow()

    init {
        getArticuloData()
    }

    fun onEvent(event: ArticuloEvent) {
        when (event) {
            ArticuloEvent.ForceError -> {
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        isError = true
                    )
                }
            }
            ArticuloEvent.RetryClick -> {
                getArticuloData()
            }
        }
    }

    private fun getArticuloData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, isError = false) }

            val noticia = noticiaRepository.getNoticiaById(articulo.id)

            _state.update {
                it.copy(
                    data = noticia,
                    isLoading = false
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val context = checkNotNull(this[APPLICATION_KEY])
                val appDatabase = AppDependencies.provideDatabase(context)
                ArticuloViewModel(
                    noticiaRepository = LocalNoticiaRepository(
                        noticiaDao = appDatabase.noticiaDao()
                    ),
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }
}