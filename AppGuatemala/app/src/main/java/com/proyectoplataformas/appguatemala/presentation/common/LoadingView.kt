package com.proyectoplataformas.appguatemala.presentation.common

import com.proyectoplataformas.appguatemala.presentation.theme.AppGuatemalaTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun LoadingView(
    loadingText: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(8.dp))
        Text(loadingText)
    }
}

@Preview
@Composable
private fun PreviewLoadingView() {
    AppGuatemalaTheme() {
        Surface() {
            LoadingView(
                loadingText = "Cargando",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}