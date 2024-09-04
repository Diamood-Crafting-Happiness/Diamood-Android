package com.diamood.ui.login.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diamood.api.country.CountryRepositoryFakeImpl
import com.diamood.data.login.Country
import com.diamood.viewmodels.login.CountryListViewModel

@Composable
fun CountryListScreen(viewModel: CountryListViewModel, onCountrySelected: (Country) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(viewModel.countries) {
            CountryItem(it, onCountrySelected)
        }
    }
}

@Composable
fun CountryItem(country: Country, onCountrySelected: (Country) -> Unit) {
    Card(
        onClick = { onCountrySelected(country) },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .background(color = Color(0xFFEAF3F2))
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "${country.flag} ")
            Text(text = "${country.name} ", fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = "${country.code} ", color = Color.Black)
        }
    }
}

@Preview
@Composable
fun CountryListScreenPreview() {
    val repository = CountryRepositoryFakeImpl()
    val viewModel = CountryListViewModel(repository)

    CountryListScreen(viewModel = viewModel) { }
}

@Preview
@Composable
fun CountryItemPreview() {
    CountryItem(country = Country("España", "🇪🇸", "ES", "+34")) {}
}
