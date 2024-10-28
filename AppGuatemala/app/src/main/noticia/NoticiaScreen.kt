package com.proyectoplataformas.appguatemala.noticia

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.proyectoplataformas.appguatemala.CategoryType
import com.proyectoplataformas.appguatemala.Noticia
import kotlinx.serialization.Serializable
import com.proyectoplataformas.appguatemala.R
import com.proyectoplataformas.appguatemala.getAllNoticias
import com.proyectoplataformas.appguatemala.getAllTypeNoticias
import com.proyectoplataformas.appguatemala.getDrawableIdFromName
import com.proyectoplataformas.appguatemala.getNoticiaByTypeAndId
import com.proyectoplataformas.appguatemala.ui.theme.AppGuatemalaTheme

@Composable
fun NoticiaRoute(
    id: Int,
    type: CategoryType,
    onNoticiaBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val noticia = getNoticiaByTypeAndId(type, id)
    NoticiaScreen(
        noticia = noticia,
        onNoticiaBackClick = onNoticiaBackClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun CustomRow(
    leftContent: @Composable () -> Unit,
    rightContent: @Composable () -> Unit
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            leftContent()
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            rightContent()
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoticiaScreen(
    noticia: Noticia,
    onNoticiaBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val drawableId = getDrawableIdFromName(context, noticia.image)

    Column(modifier = Modifier.fillMaxSize())
    {

        TopAppBar(
            title = {
                Text(text = noticia.name)
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
                    text = noticia.actualText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    textAlign = TextAlign.Justify,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewNoticiaScreen() {
    AppGuatemalaTheme {
        Surface {
            NoticiaScreen(
                noticia = Noticia(
                    type = CategoryType.Lugares,
                    id = 1,
                    name = "Place 1",
                    smallText = "Brief description of Place 1",
                    actualText = "Detailed information about Place 1",
                    image = "tikal1"
                ),
                onNoticiaBackClick = { /* Dummy back action for preview */ },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
