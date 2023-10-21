package dev.hayohtee.flightsearch.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hayohtee.flightsearch.data.model.Airport
import dev.hayohtee.flightsearch.ui.theme.FlightSearchTheme


@Composable
fun AirportSuggestions(
    airports: List<Airport>,
    onAirportClick: (Airport) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = airports, key = { it.id }) { airport ->
            AirportItem(
                airport = airport,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onAirportClick(airport) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AirportSuggestionsPreview() {
    FlightSearchTheme {
        AirportSuggestions(
            airports = listOf(
                Airport(
                    id = 0,
                    iataCode = "SEA",
                    name = "Seattle International Airport",
                    passengers = 100
                ),
                Airport(
                    id = 1,
                    iataCode = "LAX",
                    name = "Los Angeles International Airport",
                    passengers = 100
                ),
                Airport(
                    id = 2,
                    iataCode = "DFW",
                    name = "Dallas Fort International Airport",
                    passengers = 100
                )
            ),
            onAirportClick = {}
        )
    }
}
