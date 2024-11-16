package com.proyectoplataformas.appguatemala.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.proyectoplataformas.appguatemala.ui.theme.LightColors
import com.proyectoplataformas.appguatemala.ui.theme.DarkColors
import com.proyectoplataformas.appguatemala.ui.theme.Typography

private val lightScheme = lightColorScheme(
    primary = LightColors.primary,
    onPrimary = LightColors.onPrimary,
    primaryContainer = LightColors.primaryContainer,
    onPrimaryContainer = LightColors.onPrimaryContainer,
    secondary = LightColors.secondary,
    onSecondary = LightColors.onSecondary,
    secondaryContainer = LightColors.secondaryContainer,
    onSecondaryContainer = LightColors.onSecondaryContainer,
    tertiary = LightColors.tertiary
    // Add other colors as needed
)

private val darkScheme = darkColorScheme(
    primary = DarkColors.primary,
    onPrimary = DarkColors.onPrimary,
    primaryContainer = DarkColors.primaryContainer,
    onPrimaryContainer = DarkColors.onPrimaryContainer,
    secondary = DarkColors.secondary,
    onSecondary = DarkColors.onSecondary,
    secondaryContainer = DarkColors.secondaryContainer,
    onSecondaryContainer = DarkColors.onSecondaryContainer,
    // Add other colors as needed
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun AppGuatemalaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
//      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//          val context = LocalContext.current
////          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
////      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}

