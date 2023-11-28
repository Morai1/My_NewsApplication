package com.example.my_newsapplication.showcase.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.my_newsapplication.ui.theme.WhiteGray

@Composable
fun TheNewsButton( 
    text : String,
    onClickAction: () -> Unit
    )
{
    Button(onClick = onClickAction, colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ),
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold ))
    }
}

@Composable
fun TheNewsTextButton(
    text : String,
    onClickAction: () -> Unit) {
    
    TextButton(onClick = onClickAction) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold ),
            color = WhiteGray
        )
    }

}