package com.mykhailo.vasylenko.designsytem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: String,
    navigateUp: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(bottom = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BackButton(onClicked = navigateUp)

        Text(
            text = title,
            color = ApplicationTheme.colors.text,
            style = ApplicationTheme.typography.subtitle1,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun ToolbarPreview() {
    ApplicationTheme {
        Toolbar(
            title = "Select currency",
            navigateUp = {}
        )
    }
}