package space.reul.cleanarchitectureexample.app.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import space.reul.cleanarchitectureexample.app.R
import space.reul.cleanarchitectureexample.app.ui.theme.Spacing
import space.reul.cleanarchitectureexample.domain.model.City
import space.reul.cleanarchitectureexample.domain.model.DateInfo
import space.reul.cleanarchitectureexample.domain.model.Dates
import space.reul.cleanarchitectureexample.domain.model.Event
import space.reul.cleanarchitectureexample.domain.model.EventEmbedded
import space.reul.cleanarchitectureexample.domain.model.Image
import space.reul.cleanarchitectureexample.domain.model.State
import space.reul.cleanarchitectureexample.domain.model.Venue

/**
 * A composable that displays a single event as a Card.
 */
@Composable
fun EventItem(modifier: Modifier = Modifier, event: Event) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(dimensionResource(id = R.dimen.event_card_height))
            .padding(Spacing.half),
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.card_elevation)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),

        ) {

        Row(
            Modifier.padding(Spacing.regular)
        ) {
            AsyncImageCell(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                url = event.images?.firstOrNull()?.url.orEmpty(),
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = Spacing.regular)
            ) {
                Text(
                    text = event.name.orEmpty(),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = event.dates?.start?.localDate.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = event._embedded?.venues?.firstOrNull()?.name.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Row {
                    val city = event._embedded?.venues?.firstOrNull()?.city?.name.orEmpty()
                    val state = event._embedded?.venues?.firstOrNull()?.state?.stateCode.orEmpty()

                    Text(
                        text = city,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )

                    if (city.isNotBlank() && state.isNotBlank()) Text(
                        text = ", ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )

                    Text(
                        text = state,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun EventItemPreview() {
    MaterialTheme {
        Surface {
            EventItem(
                event = Event(
                    name = "Event Name",
                    dates = Dates(
                        start = DateInfo(
                            localDate = "2022-12-31"
                        )
                    ),
                    _embedded = EventEmbedded(
                        venues = listOf(
                            Venue(
                                name = "Venue Name",
                                city = City(
                                    name = "City Name"
                                ),
                                state = State(
                                    stateCode = "ST"
                                )
                            )
                        )
                    ),
                    images = listOf(
                        Image(
                            url = "https://example.com/image.jpg"
                        )
                    )
                )
            )
        }
    }
}
