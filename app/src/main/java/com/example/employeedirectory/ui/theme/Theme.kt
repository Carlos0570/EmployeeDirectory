package com.example.employeedirectory.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp

private val ColorScheme = darkColorScheme(
    background = Background,
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    tertiary = Tertiary,
    error = Red,
    outline = Black,
    onSurface = White
)

@Composable
fun EmployeeDirectoryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode)
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = ColorScheme.primary.toArgb()
            window.navigationBarColor = ColorScheme.background.toArgb()
        }
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = Typography,
        content = content,
        shapes = shapes()
    )
}
fun shapes() = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(6.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(10.dp)
)