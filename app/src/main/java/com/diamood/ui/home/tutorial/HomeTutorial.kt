package com.diamood.ui.home.tutorial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.ImportContacts
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diamood.theme.PrimaryLight

@Composable
fun HomeTutorial() {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Image,
                modifier = Modifier.size(36.dp),
                tint = PrimaryLight,
                contentDescription = ""
            )

            Column(Modifier.padding(horizontal = 16.dp)) {
                Text(text = "Todos tus lienzos", fontWeight = FontWeight.Bold)
                Text(text = "Guarda toda la información de tus lienzos.")
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.ImportContacts,
                modifier = Modifier.size(36.dp),
                tint = PrimaryLight,
                contentDescription = ""
            )
            Column(Modifier.padding(horizontal = 16.dp)) {
                Text(text = "Diario de Diamond Painting", fontWeight = FontWeight.Bold)
                Text(text = "Registra cómo te sientes y sube fotos de tu progreso.")
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                modifier = Modifier.size(36.dp),
                tint = PrimaryLight,
                contentDescription = ""
            )
            Column(Modifier.padding(horizontal = 16.dp)) {
                Text(text = "Tienda de alta calidad", fontWeight = FontWeight.Bold)
                Text(text = "¡La tienda de Diamond Painting más ecológica a nivel mundial!")
            }
        }

    }

}

@Preview
@Composable
fun HomeTutorialPreview() {
    HomeTutorial()
}
