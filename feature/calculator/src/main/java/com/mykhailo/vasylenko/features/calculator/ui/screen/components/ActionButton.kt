package com.mykhailo.vasylenko.features.calculator.ui.screen.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    shape: Shape = RoundedCornerShape(percent = 50),
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = ApplicationTheme.colors.backgroundButton,
        contentColor = ApplicationTheme.colors.textOnButton,
        disabledBackgroundColor = ApplicationTheme.colors.textHint,
        disabledContentColor = ApplicationTheme.colors.textOnButton
    ),
    content: @Composable () -> Unit
) {
    val shouldEnable = if (isLoading) true else enabled

    Button(
        onClick = onClick,
        enabled = shouldEnable,
        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp),
        contentPadding = PaddingValues(0.dp),
        shape = shape,
        colors = colors,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            this@Button.AnimatedVisibility(
                visible = isLoading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                CircularProgressIndicator(
                    color = colors.contentColor(enabled = true).value
                )
            }
            this@Button.AnimatedVisibility(
                visible = !isLoading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                content()
            }
        }
    }
}

@Preview
@Composable
private fun ActionButtonPreview() {
    ApplicationTheme {
        ActionButton(
            onClick = { },
            modifier = Modifier
                .height(30.dp)
                .width(150.dp)
        ) {
            Text(text = "Do some action")
        }
    }
}

@Preview
@Composable
private fun ActionButtonPreview_Disabled() {
    ApplicationTheme {
        ActionButton(
            onClick = { },
            enabled = false
        ) {
            Text(text = "Do some action")
        }
    }
}

@Preview
@Composable
private fun ActionButtonPreview_Loading() {
    ApplicationTheme {
        var isLoading by remember { mutableStateOf(true) }

        ActionButton(
            onClick = { isLoading = !isLoading },
            isLoading = isLoading
        ) {
            Text(text = "Do some action")
        }
    }
}