package com.example.cookbook.feature_recipe_book.presentation.add_recipe_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun RecipeIntroCard(
    label1: String,
    text1: String,
    label2: String,
    text2: String,
    onText1Change: (String) -> Unit,
    onText2Change: (String) -> Unit,
    isInputNumber: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = text1,
            onValueChange = onText1Change,
            label = {
                Text(
                    text = label1
                )
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = if(isInputNumber) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = text2,
            onValueChange = onText2Change,
            label = {
                Text(
                    text = label2
                )
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = if(isInputNumber) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions()
        )
    }
}