package com.mykhailo.vasylenko.designsytem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mykhailo.vasylenko.designsystem.R
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme

@Composable
fun BackButton(
    onClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.clickable(onClick = onClicked),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = ApplicationTheme.colors.textLink
        )
        Spacer(
            modifier = Modifier.width(2.dp)
        )
        Text(
            text = "Back",
            color = ApplicationTheme.colors.textLink,
            style = ApplicationTheme.typography.subtitle2
        )
    }
}