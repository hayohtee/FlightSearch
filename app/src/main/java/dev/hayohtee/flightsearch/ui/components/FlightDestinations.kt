package dev.hayohtee.flightsearch.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hayohtee.flightsearch.data.model.Airport
import dev.hayohtee.flightsearch.ui.theme.FlightSearchTheme

@Composable
fun FlightDestinations(
    departure: Airport,
    destinations: List<Airport>,
    onFavouriteClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        item {
            Text(
                text = "Flights from ${departure.iataCode}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        items(items = destinations, key = { it.id }) { destination ->
            FlightDestinationItem(
                departure = departure,
                arrival = destination,
                onFavouriteClick = onFavouriteClick,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlightDestinationsPreview() {
    FlightSearchTheme {
        FlightDestinations(
            departure = Airport(
                id = 0,
                iataCode = "SEA",
                name = "Seattle International Airport",
                passengers = 100
            ),
            destinations = listOf(
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
            onFavouriteClick = { _, _ -> },
            modifier = Modifier.padding(16.dp)
        )
    }
}