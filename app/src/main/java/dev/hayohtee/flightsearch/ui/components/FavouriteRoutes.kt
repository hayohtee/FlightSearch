package dev.hayohtee.flightsearch.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.hayohtee.flightsearch.R
import dev.hayohtee.flightsearch.data.model.Airport

@Composable
fun FavouriteRoutes(routes: List<Pair<Airport, Airport>>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        item {
            Text(
                text = stringResource(id = R.string.favourite_routes),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        items(items = routes) { (departure, arrival) ->
            FlightDestinationItem(
                departure = departure,
                arrival = arrival,
                onFavouriteClick = { _, _ -> },
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}