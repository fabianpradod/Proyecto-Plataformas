package com.proyectoplataformas.appguatemala.tema

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
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.text.font.FontWeight
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
fun TemaRoute(
    type: CategoryType,
    onNoticiaBackClick: () -> Unit,
    onNoticiaClick: (Noticia) -> Unit,
    modifier: Modifier = Modifier
) {
    TemaScreen(
        onNoticiaBackClick = onNoticiaBackClick,
        onNoticiaClick = onNoticiaClick,
        modifier = modifier,
        type = type
    )
}

//@Preview
//@Composable
//private fun PreviewTemaScreen() {
//    AppGuatemalaTheme {
//        Surface {
//            TemaScreen(
//                type = CategoryType.Instrumentos,
//                onNoticiaBackClick = {},
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//    }
//}

@Composable
fun TemaGrid(
    categoryType: CategoryType,
    onNoticiaClick: (Noticia) -> Unit
) {
    val isInPreview = LocalInspectionMode.current
    val noticias = getAllTypeNoticias(categoryType)
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(noticias) { noticia ->
            val drawableId = getDrawableIdFromName(context, noticia.image)
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
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
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
                            if (isInPreview) {
                                Image(
                                    painter = painterResource(id = R.drawable.tikal1),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Image(
                                    bitmap = ImageBitmap.imageResource(id = drawableId),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(
                            modifier = Modifier.weight(0.55f)
                        ) {
                            Text(
                                text = noticia.actualText,
                                style = MaterialTheme.typography.bodyMedium,
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
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier
                                .clickable { onNoticiaClick(noticia) }
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TemaScreen(
    type: CategoryType,
    onNoticiaBackClick: () -> Unit,
    onNoticiaClick: (Noticia) -> Unit,
    modifier: Modifier = Modifier
) {
    Column (modifier = Modifier.fillMaxSize())
    {
        TopAppBar(
            title = {
                Text(text = type.name)
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
            categoryType = type,
            onNoticiaClick = onNoticiaClick
        )
    }
}

