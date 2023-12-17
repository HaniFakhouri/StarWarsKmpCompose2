package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

/**
 * Created by hani.fakhouri on 2023-07-30.
 */
private val DarkColorPalette = darkColors(
    primary = lightsaberBlue,
    primaryVariant = lightsaberRed,
    secondary = yodaGreen,
    secondaryVariant = tatooineDune,
)

private val LightColorPalette = lightColors(

    // Color displayed most frequently across your app’s screens and components.
    primary = lightsaberBlue,
    primaryVariant = lightsaberRed,
    onPrimary = imperialWhite,

    // Color provides more ways to accent and distinguish your product. Secondary colors are best for:
    //   - Floating action buttons
    //   - Selection controls, like checkboxes and radio buttons
    //   - Highlighting selected text
    //   - Links and headlines
    secondary = yodaGreen,
    secondaryVariant = tatooineDune,
    onSecondary = galacticBlack,

    // Background color appears behind scrollable content.
    background = galacticBlack,
    onBackground = imperialWhite,

    // Surface color is used on surfaces of components, such as cards, sheets and menus.
    surface = imperialWhite,
    onSurface = galacticBlack,

    // Error color is used to indicate error within components, such as text fields.
    error = lightsaberRed,
    onError = imperialWhite,
)

@Composable
fun KmpAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        //DarkColorPalette
        LightColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}