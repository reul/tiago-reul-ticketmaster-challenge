package space.reul.cleanarchitectureexample.data.database.mapping

import space.reul.cleanarchitectureexample.domain.model.City
import space.reul.cleanarchitectureexample.domain.model.DateInfo
import space.reul.cleanarchitectureexample.domain.model.Dates
import space.reul.cleanarchitectureexample.domain.model.Event
import space.reul.cleanarchitectureexample.domain.model.EventEmbedded
import space.reul.cleanarchitectureexample.domain.model.Image
import space.reul.cleanarchitectureexample.domain.model.State
import space.reul.cleanarchitectureexample.domain.model.Venue
import kotlin.test.Test
import kotlin.test.assertEquals

class EventMappingTest {
    @Test
    fun `verify fromEvent creates correct EventMapping`() {
        val event = Event(
            id = "1",
            name = "Event 1",
            url = "http://example.com",
            images = listOf(Image(url = "http://example.com/image.jpg")),
            dates = Dates(start = DateInfo(localDate = "2021-01-01")),
            _embedded = EventEmbedded(
                venues = listOf(
                    Venue(
                        name = "Venue 1",
                        city = City(name = "City 1"),
                        state = State(name = "State 1", stateCode = "ST")
                    )
                )
            )
        )

        val result = EventMapping.fromEvent(event)

        assertEquals(
            result, EventMapping(
                id = "1",
                name = "Event 1",
                url = "http://example.com",
                imageUrl = "http://example.com/image.jpg",
                date = "2021-01-01",
                venue = "Venue 1",
                city = "City 1",
                state = "ST"
            )
        )
    }

    @Test
    fun `verify toEvent creates correct Event`() {
        val eventMapping = EventMapping(
            id = "1",
            name = "Event 1",
            url = "http://example.com",
            imageUrl = "http://example.com/image.jpg",
            date = "2021-01-01",
            venue = "Venue 1",
            city = "City 1",
            state = "ST"
        )

        val event = eventMapping.toEvent()

        assertEquals(
            event, Event(
                id = "1",
                name = "Event 1",
                url = "http://example.com",
                images = listOf(Image(url = "http://example.com/image.jpg")),
                dates = Dates(start = DateInfo(localDate = "2021-01-01")),
                _embedded = EventEmbedded(
                    venues = listOf(
                        Venue(
                            name = "Venue 1",
                            city = City(name = "City 1"),
                            state = State(name = "ST", stateCode = "ST")
                        )
                    )
                )
            )
        )
    }
}
