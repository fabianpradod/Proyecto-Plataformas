package com.proyectoplataformas.appguatemala.tema

import androidx.compose.material3.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import com.proyectoplataformas.appguatemala.R
import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.model.Noticia
import com.proyectoplataformas.appguatemala.data.source.getDrawableIdFromName
import com.proyectoplataformas.appguatemala.presentation.mainFlow.tema.TemaViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.proyectoplataformas.appguatemala.presentation.common.ErrorView
import com.proyectoplataformas.appguatemala.presentation.common.LoadingView
import com.proyectoplataformas.appguatemala.presentation.mainFlow.tema.TemaEvent
import com.proyectoplataformas.appguatemala.presentation.mainFlow.tema.TemaState


@Composable
fun TemaRoute(
    onNoticiaBackClick: () -> Unit,
    onNoticiaClick: (Noticia) -> Unit,
    viewModel: TemaViewModel = viewModel(factory = TemaViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TemaScreen(
        state = state,
        onNoticiaBackClick = onNoticiaBackClick,
        onNoticiaClick = onNoticiaClick,
        onRetryClick = { viewModel.onEvent(TemaEvent.RetryClick) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TemaScreen(
    state: TemaState,
    onRetryClick: () -> Unit,
    onNoticiaBackClick: () -> Unit,
    onNoticiaClick: (Noticia) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        when {
            state.isLoading -> {
                LoadingView(
                    loadingText = "Obteniendo articulos",
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            state.isError -> {
                ErrorView(
                    errorText = "Uh, oh. Error al obtener articulos",
                    onRetryClick = onRetryClick,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            else -> {
                Column (modifier = Modifier.fillMaxSize())
                {
                    TopAppBar(
                        title = {
                            Text(text = "Articulos")
                        },
                        navigationIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier
                                    .clickable { onNoticiaBackClick() }
                                    .padding(start = 16.dp)
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                    )
                    TemaGrid(
                        noticias = state.noticias,
                        onNoticiaClick = onNoticiaClick
                    )
                }

            }
        }
    }
}



@Composable
fun TemaGrid(
    noticias: List<Noticia>,
    onNoticiaClick: (Noticia) -> Unit
) {
    val isInPreview = LocalInspectionMode.current
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(noticias) { noticia ->
            val drawableId = getDrawableIdFromName(context, noticia.image1)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Text(
                        text = noticia.name,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(0.45f)
                                .aspectRatio(1f)
                        ) {
                            AsyncImage(
                                model = noticia.image1,
                                contentDescription = noticia.name,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(
                            modifier = Modifier.weight(0.55f)
                        ) {
                            Text(
                                text = noticia.smallText,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Conoce más sobre ${noticia.name}",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .clickable { onNoticiaClick(noticia) }
                        )
                    }
                }
            }
        }
    }
}

