package com.proyectoplataformas.appguatemala.presentation.mainFlow.tema

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.proyectoplataformas.appguatemala.data.repository.FirestoreNoticiaRepository
import com.proyectoplataformas.appguatemala.di.AppDependencies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.proyectoplataformas.appguatemala.domain.NoticiaRepository
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.createSavedStateHandle
import androidx.navigation.toRoute


class TemaViewModel(
    private val noticiaRepository: NoticiaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val categoryType = savedStateHandle.toRoute<TemaDestination>()
    private val _state = MutableStateFlow(TemaState())
    val state = _state.asStateFlow()

    init {
        getNoticiasType()
    }

    fun onEvent(event: TemaEvent) {
        when (event) {
            TemaEvent.RetryClick -> {
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        isError = true
                    )
                }
                getNoticiasType()

            }
        }
    }

    private fun getNoticiasType() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, isError = false) }

            val noticia = noticiaRepository.getTypeNoticias(categoryType.type)

            _state.update {
                it.copy(
                    noticias = noticia,
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
                TemaViewModel(
                    noticiaRepository = FirestoreNoticiaRepository(
                        noticiaDao = appDatabase.noticiaDao()
                    ),
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }
}