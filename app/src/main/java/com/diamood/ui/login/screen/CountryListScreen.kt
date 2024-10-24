package com.diamood.ui.login.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diamood.api.country.CountryRepositoryFakeImpl
import com.diamood.data.login.Country
import com.diamood.domain.login.GetCountryListUseCase
import com.diamood.theme.PrimaryLight
import com.diamood.viewmodels.login.CountryListViewModel

@Composable
fun CountryListScreen(viewModel: CountryListViewModel, onCountrySelected: (Country?) -> Unit) {
    val searchText by viewModel.searchText.collectAsState()
    val countries by viewModel.countries.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Cancelar",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable { viewModel.cancelSelection() },
            textAlign = TextAlign.Center,
            color = PrimaryLight,
        )
        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.colors(unfocusedIndicatorColor = Color.Transparent),
            shape = RoundedCornerShape(8.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(countries) { country ->
                CountryItem(country, onCountrySelected)
            }
        }
    }
}

@Composable
fun CountryItem(country: Country, onCountrySelected: (Country) -> Unit) {
    Card(
        onClick = { onCountrySelected(country) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(color = Color(0xFFEAF3F2))
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "${country.flag} ")
            Text(text = "${country.name} ", fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = "${country.phoneCode} ", color = Color.Black)
        }
    }
}

@Preview
@Composable
fun CountryListScreenPreview() {
    val useCase = GetCountryListUseCase(CountryRepositoryFakeImpl())
    val viewModel = CountryListViewModel(useCase)

    CountryListScreen(viewModel = viewModel) { }
}

@Preview
@Composable
fun CountryItemPreview() {
    CountryItem(country = Country("España", "🇪🇸", "ES", "+34")) {}
}
