package com.proyectoplataformas.appguatemala.noticia

import androidx.compose.foundation.Image
import androidx.compose.material3.Surface
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.model.Noticia
import com.proyectoplataformas.appguatemala.data.source.getDrawableIdFromName
import com.proyectoplataformas.appguatemala.presentation.mainFlow.articulo.ArticuloViewModel
import com.proyectoplataformas.appguatemala.presentation.theme.AppGuatemalaTheme
import androidx.compose.material3.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.proyectoplataformas.appguatemala.presentation.common.ErrorView
import com.proyectoplataformas.appguatemala.presentation.common.LoadingView
import com.proyectoplataformas.appguatemala.presentation.mainFlow.articulo.ArticuloEvent
import com.proyectoplataformas.appguatemala.presentation.mainFlow.articulo.ArticuloState



@Composable
fun ArticuloRoute(
    onArticuloBackClick: () -> Unit,
    viewModel: ArticuloViewModel = viewModel(factory = ArticuloViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ArticuloScreen(
        state = state,
        onArticuloBackClick = onArticuloBackClick,
        forceError = { viewModel.onEvent(ArticuloEvent.ForceError) },
        onRetryClick = { viewModel.onEvent(ArticuloEvent.RetryClick) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArticuloScreen(
    state: ArticuloState,
    forceError: () -> Unit,
    onRetryClick: () -> Unit,
    onArticuloBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        when {
            state.isLoading -> {
                LoadingView(
                    loadingText = "Obteniendo articulo",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable { forceError() }
                )
            }

            state.isError -> {
                ErrorView(
                    errorText = "Uh, oh. Error al obtener articulo",
                    onRetryClick = onRetryClick,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            else -> {
                state.data?.let { noticia ->
                    val context = LocalContext.current
                    val drawableId = getDrawableIdFromName(context, noticia.image)

                    Column(modifier = Modifier.fillMaxSize()) {
                        TopAppBar(
                            title = { Text(text = noticia.name) },
                            navigationIcon = {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back",
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier
                                        .clickable { onArticuloBackClick() }
                                        .padding(start = 16.dp)
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            item {
                                if (drawableId != 0) {
                                    Image(
                                        bitmap = ImageBitmap.imageResource(id = drawableId),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp)
                                            .padding(horizontal = 8.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }

                                Spacer(modifier = Modifier.height(32.dp))

                                Text(
                                    text = noticia.smallText,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 8.dp),
                                    textAlign = TextAlign.Justify,
                                )

                                Spacer(modifier = Modifier.height(32.dp))

                                if (drawableId != 0) {
                                    Image(
                                        bitmap = ImageBitmap.imageResource(id = drawableId),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp)
                                            .padding(horizontal = 8.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }

                                Spacer(modifier = Modifier.height(32.dp))

                                Text(
                                    text = noticia.actualText1,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 8.dp),
                                    textAlign = TextAlign.Justify,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}



//
//@Preview
//@Composable
//private fun PreviewArticuloScreen() {
//    AppGuatemalaTheme {
//        Surface {
//            ArticuloScreen(
//                noticia = Noticia(
//                    type = CategoryType.Lugares,
//                    id = 1,
//                    name = "Place 1",
//                    smallText = "Brief description of Place 1",
//                    actualText = "Detailed information about Place 1",
//                    image = "tikal1"
//                ),
//                onArticuloBackClick = { /* Dummy back action for preview */ },
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//    }
//}
