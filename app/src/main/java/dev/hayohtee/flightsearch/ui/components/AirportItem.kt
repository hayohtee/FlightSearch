package dev.hayohtee.flightsearch.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hayohtee.flightsearch.data.model.Airport
import dev.hayohtee.flightsearch.ui.theme.FlightSearchTheme

@Composable
fun AirportItem(airport: Airport, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = airport.iataCode,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = airport.name,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AirportItemPreview() {
    FlightSearchTheme {
        AirportItem(
            airport = Airport(
                iataCode = "SEA",
                name = "Seattle International Airport",
                passengers = 100
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}