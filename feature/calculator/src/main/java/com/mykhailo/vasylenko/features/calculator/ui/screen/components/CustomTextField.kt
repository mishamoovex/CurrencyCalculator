package com.mykhailo.vasylenko.features.calculator.ui.screen.components


import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mykhailo.vasylenko.designsytem.theme.ApplicationTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    textStyle: TextStyle = MaterialTheme.typography.body1,
    placeholder: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = ApplicationTheme.colors.text,
        disabledTextColor = ApplicationTheme.colors.text.copy(alpha = 0.5f),

        backgroundColor = Color.Transparent,

        cursorColor = ApplicationTheme.colors.textAction,
        errorCursorColor = ApplicationTheme.colors.textError,

        placeholderColor = ApplicationTheme.colors.textHint,
        disabledPlaceholderColor = ApplicationTheme.colors.textHint.copy(alpha = 0.5f)
    ),
    paddingValues: PaddingValues = TextFieldDefaults.textFieldWithoutLabelPadding(
        start = 0.dp,
        end = 0.dp,
        top = 0.dp,
        bottom = 0.dp
    )

) {

    val textColor = if (isError) {
        ApplicationTheme.colors.textError
    } else {
        colors.textColor(enabled = enabled).value
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    @OptIn(ExperimentalMaterialApi::class)
    BasicTextField(
        value = value,
        modifier = modifier
            .background(colors.backgroundColor(enabled).value)
            .defaultMinSize(
                minWidth = TextFieldDefaults.MinWidth
            ),
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(colors.cursorColor(isError).value),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = singleLine,
        maxLines = maxLines,
        decorationBox = @Composable { innerTextField ->
            // places leading icon, text field with label and placeholder, trailing icon
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                placeholder = placeholder,
                label = null,
                leadingIcon = null,
                trailingIcon = trailingIcon,
                singleLine = singleLine,
                enabled = enabled,
                isError = isError,
                interactionSource = interactionSource,
                colors = colors,
                contentPadding = paddingValues
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun CustomTextFiledPreview() {
    ApplicationTheme {
        CustomTextField(
            value = "mykhailo.development@gmail.com",
            onValueChange = {}
        )
    }
}