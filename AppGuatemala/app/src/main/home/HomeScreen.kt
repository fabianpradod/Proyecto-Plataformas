package com.proyectoplataformas.appguatemala.home

import androidx.compose.material3.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.Surface
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.proyectoplataformas.appguatemala.CategoryType
import com.proyectoplataformas.appguatemala.Noticia
import kotlinx.serialization.Serializable
import com.proyectoplataformas.appguatemala.R
import com.proyectoplataformas.appguatemala.getAllNoticias
import com.proyectoplataformas.appguatemala.getAllTypeNoticias
import com.proyectoplataformas.appguatemala.getDrawableIdFromName
import com.proyectoplataformas.appguatemala.ui.theme.AppGuatemalaTheme

@Composable
fun HomeRoute(
    onCategoryClick: (CategoryType) -> Unit,
    onNoticiaClick: (Noticia) -> Unit,
    modifier: Modifier = Modifier
) {
    HomeScreen(
        onCategoryClick = onCategoryClick,
        onNoticiaClick = onNoticiaClick,
        modifier = modifier
    )
}

//
//@Preview
//@Composable
//private fun PreviewWelcomeScreen() {
//    AppGuatemalaTheme {
//        Surface {
//            HomeScreen(
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//    }
//}

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    onNoticiaClick: (Noticia) -> Unit,
    onCategoryClick: (CategoryType) -> Unit,
) {
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
                    noticias = getAllTypeNoticias(CategoryType.Lugares),
                    onCategoryClick = onCategoryClick,
                    onNoticiaClick = onNoticiaClick
                )
            }
            item {
                NewsSection(
                    categoryType = CategoryType.Artistas,
                    noticias = getAllTypeNoticias(CategoryType.Artistas),
                    onCategoryClick = onCategoryClick,
                    onNoticiaClick = onNoticiaClick
                )
            }
            item {
                NewsSection(
                    categoryType = CategoryType.Comidas,
                    noticias = getAllTypeNoticias(CategoryType.Comidas),
                    onCategoryClick = onCategoryClick,
                    onNoticiaClick = onNoticiaClick
                )
            }
            item {
                NewsSection(
                    categoryType = CategoryType.Instrumentos,
                    noticias = getAllTypeNoticias(CategoryType.Instrumentos),
                    onCategoryClick = onCategoryClick,
                    onNoticiaClick = onNoticiaClick
                )
            }
        }
    }
}