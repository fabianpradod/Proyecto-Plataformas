package com.proyectoplataformas.appguatemala.tema


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.proyectoplataformas.appguatemala.R

@Composable
fun TemaScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.tikal1),
            contentDescription = "Lugares Turísticos",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Lugares Turísticos de Guatemala")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Explora los hermosos destinos turísticos de Guatemala, como Tikal.")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTemaScreen() {
    TemaScreen()
}
