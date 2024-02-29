package space.reul.ticketmasterchallenge.app.ui.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import space.reul.ticketmasterchallenge.app.ui.theme.TicketmasterChallengeTheme
import space.reul.ticketmasterchallenge.domain.model.Event
import space.reul.ticketmasterchallenge.domain.model.EventList
import space.reul.ticketmasterchallenge.domain.model.Image

@Composable
fun Events(modifier: Modifier = Modifier, eventList: EventList) {
    LazyColumn(
        modifier = modifier,
    ) {
        eventList.events.forEach { event ->
            item { EventItem(event = event) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventGridPreview() {
    TicketmasterChallengeTheme {
        Events(
            eventList = EventList(
                events = arrayListOf(
                    Event(images = listOf(Image(url = "")))
                )
            )
        )
    }
}
