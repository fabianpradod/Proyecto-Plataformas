package com.proyectoplataformas.appguatemala.home

import androidx.compose.material3.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.proyectoplataformas.appguatemala.R
import com.proyectoplataformas.appguatemala.data.model.CategoryType
import com.proyectoplataformas.appguatemala.data.model.Noticia
import com.proyectoplataformas.appguatemala.data.source.NoticiaDb
import com.proyectoplataformas.appguatemala.data.source.getDrawableIdFromName
import com.proyectoplataformas.appguatemala.presentation.mainFlow.home.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.proyectoplataformas.appguatemala.presentation.common.ErrorView
import com.proyectoplataformas.appguatemala.presentation.common.LoadingView
import com.proyectoplataformas.appguatemala.presentation.mainFlow.home.HomeEvent
import com.proyectoplataformas.appguatemala.presentation.mainFlow.home.HomeState
import androidx.compose.runtime.getValue



@Composable
fun HomeRoute(
    onCategoryClick: (CategoryType) -> Unit,
    onNoticiaClick: (Noticia) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        onCategoryClick = onCategoryClick,
        forceError = { viewModel.onEvent(HomeEvent.ForceError) },
        onRetryClick = { viewModel.onEvent(HomeEvent.RetryClick) },
        onNoticiaClick = onNoticiaClick,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    state: HomeState,
    forceError: () -> Unit,
    onRetryClick: () -> Unit,
    onNoticiaClick: (Noticia) -> Unit,
    onCategoryClick: (CategoryType) -> Unit,
    modifier: Modifier = Modifier
) {

    Box(modifier) {
        when {
            state.isLoading -> {
                LoadingView(
                    loadingText = "Obteniendo noticias",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable { forceError() }
                )
            }

            state.isError -> {
                ErrorView(
                    errorText = "Uh, oh. Error al obtener noticias",
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
                            Text("¡Conozcamos a Guatemala!")
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                    LazyColumn (
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                    ) {
                        item {
                            NewsSection(
                                categoryType = CategoryType.Lugares,
                                noticias = state.lugaresNoticias,
                                onCategoryClick = onCategoryClick,
                                onNoticiaClick = onNoticiaClick
                            )
                        }
                        item {
                            NewsSection(
                                categoryType = CategoryType.Artistas,
                                noticias = state.artistasNoticias,
                                onCategoryClick = onCategoryClick,
                                onNoticiaClick = onNoticiaClick
                            )
                        }
                        item {
                            NewsSection(
                                categoryType = CategoryType.Comidas,
                                noticias = state.comidasNoticias,
                                onCategoryClick = onCategoryClick,
                                onNoticiaClick = onNoticiaClick
                            )
                        }
                        item {
                            NewsSection(
                                categoryType = CategoryType.Instrumentos,
                                noticias = state.instrumentosNoticias,
                                onCategoryClick = onCategoryClick,
                                onNoticiaClick = onNoticiaClick
                            )
                        }
                    }
                }

            }
        }
    }
}


@Composable
fun CategoryTitleRow(categoryType: CategoryType, onCategoryClick: (CategoryType) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCategoryClick(categoryType) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = categoryType.name, style = MaterialTheme.typography.titleLarge)
        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = categoryType.name)
    }
}

@Composable
fun NewsLazyRow(noticias: List<Noticia>, onNoticiaClick: (Noticia) -> Unit) {
    val isInPreview = LocalInspectionMode.current
    val context = LocalContext.current

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(noticias) { noticia ->
            val drawableId = getDrawableIdFromName(context, noticia.image)
            Card(
                modifier = Modifier
                    .width(180.dp)
                    .clickable { onNoticiaClick(noticia) }
                    .padding(8.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Gray)
                    ) {
                        if (isInPreview) {
                            Image(
                                painter = painterResource(id = R.drawable.tikal1),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Image(
                                bitmap = ImageBitmap.imageResource(id = drawableId),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = noticia.name,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Composable
fun NewsSection(
    categoryType: CategoryType,
    noticias: List<Noticia>,
    onCategoryClick: (CategoryType) -> Unit,
    onNoticiaClick: (Noticia) -> Unit
) {
    Column {
        CategoryTitleRow(
            categoryType = categoryType,
            onCategoryClick = { clickedCategoryType ->
                onCategoryClick(clickedCategoryType)
            }
        )
        NewsLazyRow(
            noticias = noticias,
            onNoticiaClick = { clickedNoticia ->
                onNoticiaClick(clickedNoticia)
            }
        )
    }
}
