package com.albersa.nooroproject.ui.weather

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.albersa.nooroproject.R

@Composable
fun SearchBar(
    text: String,
    onTextChanged: (String) -> Unit,
    onSearchIconPressed: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = text,
        onValueChange = onTextChanged,
        placeholder = {
            Text(stringResource(R.string.search_location_placeholder))
        },
        trailingIcon = {
            Icon(imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                Modifier.clickable {
                    keyboardController?.hide()
                    onSearchIconPressed(text)
            })
        },
        shape = ShapeDefaults.ExtraLarge,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 35.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                onSearchIconPressed(text)
            }
        )
    )
}