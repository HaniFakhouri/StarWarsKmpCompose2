package theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Created by hani.fakhouri on 2023-07-30.
 */

val Typography = Typography(
    h4 = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.W400,
        fontSize = 34.sp,
        letterSpacing = 0.25.sp,
        color = Color.Unspecified, //Warning! Don't set colors here - it overrides automatic text colors (e.g. Button)
    ),
    h5 = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.W400,
        fontSize = 24.sp,
        letterSpacing = 0.sp,
        color = Color.Unspecified, //Warning! Don't set colors here - it overrides automatic text colors (e.g. Button)
    ),
    h6 = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
        letterSpacing = 0.15.sp,
        color = Color.Unspecified, //Warning! Don't set colors here - it overrides automatic text colors (e.g. Button)
    ),
    body1 = TextStyle( //Default text style
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.5.sp,
        color = Color.Unspecified //Warning! Don't set colors here - it overrides automatic text colors (e.g. Button)
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.25.sp,
        color = Color.Unspecified, //Warning! Don't set colors here - it overrides automatic text colors (e.g. Button)
    ),
    button = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp,
        color = Color.Unspecified, //Warning! Don't set colors here - it overrides automatic text colors (e.g. Button)
    ),
    caption = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp,
        color = Color.Unspecified, //Warning! Don't set colors here - it overrides automatic text colors (e.g. Button)
    )
)