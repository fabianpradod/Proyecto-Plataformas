package com.proyectoplataformas.appguatemala.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.Surface
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import com.proyectoplataformas.appguatemala.R
import com.proyectoplataformas.appguatemala.ui.theme.AppGuatemalaTheme


@Composable
fun WelcomeScreenRoute(
    onWelcomeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    WelcomeScreen(
        onWelcomeClick = onWelcomeClick,
        modifier = modifier
    )
}

@Preview
@Composable
private fun PreviewWelcomeScreen() {
    AppGuatemalaTheme {
        Surface {
            WelcomeScreen(
                onWelcomeClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun WelcomeScreen(
    onWelcomeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(55.dp)
                .background(color = MaterialTheme.colorScheme.inversePrimary)
                .align(Alignment.CenterStart)
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(55.dp)
                .background(color = MaterialTheme.colorScheme.inversePrimary)
                .align(Alignment.CenterEnd)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_guatemala_logo),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
            )
            Button(
                onClick = onWelcomeClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 40.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Text("Empieza a conocer a Guatemala!", color =  MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}
