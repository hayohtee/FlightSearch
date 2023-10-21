package dev.hayohtee.flightsearch.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hayohtee.flightsearch.R
import dev.hayohtee.flightsearch.data.model.Airport
import dev.hayohtee.flightsearch.ui.components.AirportSuggestions
import dev.hayohtee.flightsearch.ui.components.FlightDestinations
import dev.hayohtee.flightsearch.ui.theme.FlightSearchTheme

@Composable
fun HomeScreen(
    uiState: UiState,
    onSearchAirportChange: (String) -> Unit,
    onAirportSuggestionClick: (Airport) -> Unit,
    modifier: Modifier = Modifier
) {
    var hideKeyboard by remember { mutableStateOf(false) }

    Scaffold(modifier = modifier, topBar = { FlightSearchAppBar() }) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            Column(
                modifier = Modifier
                    .clickable { hideKeyboard = true }
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AirportSearchBar(
                    value = uiState.airportSearchText,
                    onValueChange = onSearchAirportChange,
                    hideKeyboard = hideKeyboard,
                    onFocusClear = { hideKeyboard = false },
                    modifier = Modifier.fillMaxWidth()
                )

                if (!uiState.showDestinations) {
                    AirportSuggestions(
                        airports = uiState.airportSuggestions,
                        onAirportClick = {
                            hideKeyboard = true
                            onAirportSuggestionClick(it)
                        }
                    )
                }

                if (uiState.showDestinations && uiState.selectedAirport != null) {
                    FlightDestinations(
                        departure = uiState.selectedAirport,
                        destinations = uiState.destinations,
                        onFavouriteClick = { _, _ ->
                            hideKeyboard = true
                        }
                    )
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun AirportSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    hideKeyboard: Boolean,
    onFocusClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = stringResource(id = R.string.airport_searchbar_hint))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search, contentDescription = null
            )
        },
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus()
                onValueChange(value)
            }
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        singleLine = true,
        shape = RoundedCornerShape(50),
        modifier = modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        )
    )

    if (hideKeyboard) {
        focusManager.clearFocus()
        onFocusClear()
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    FlightSearchTheme {
        HomeScreen(
            uiState = UiState(),
            onSearchAirportChange = {},
            onAirportSuggestionClick = {}
        )
    }
}