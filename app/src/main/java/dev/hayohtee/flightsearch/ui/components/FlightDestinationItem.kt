package dev.hayohtee.flightsearch.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hayohtee.flightsearch.R
import dev.hayohtee.flightsearch.data.model.Airport
import dev.hayohtee.flightsearch.ui.theme.FlightSearchTheme

@Composable
fun FlightDestinationItem(
    departure: Airport,
    arrival: Airport,
    onFavouriteClick: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isSelected by rememberSaveable { mutableStateOf(false) }
    val selectedColor = if (isSelected) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.onSurface

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(topEnd = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(id = R.string.depart).uppercase(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    AirportItem(airport = departure)
                }
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(id = R.string.arrive).uppercase(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    AirportItem(airport = arrival)
                }
            }
            IconButton(
                onClick = {
                    isSelected = true
                    onFavouriteClick(departure.iataCode, arrival.iataCode)
                },
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    tint = selectedColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlightDestinationItemPreview() {
    FlightSearchTheme {
        FlightDestinationItem(
            departure = Airport(
                id = 0,
                iataCode = "SEA",
                name = "Seattle International Airport",
                passengers = 200
            ),
            arrival = Airport(
                id = 1,
                iataCode = "LAX",
                name = "Los Angeles International Airport",
                passengers = 200
            ),
            onFavouriteClick = { _, _ -> },
        )
    }
}