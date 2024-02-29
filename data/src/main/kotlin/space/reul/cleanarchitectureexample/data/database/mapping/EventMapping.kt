package space.reul.cleanarchitectureexample.data.database.mapping

import androidx.room.Entity
import androidx.room.PrimaryKey
import space.reul.cleanarchitectureexample.domain.model.City
import space.reul.cleanarchitectureexample.domain.model.DateInfo
import space.reul.cleanarchitectureexample.domain.model.Dates
import space.reul.cleanarchitectureexample.domain.model.Event
import space.reul.cleanarchitectureexample.domain.model.EventEmbedded
import space.reul.cleanarchitectureexample.domain.model.Image
import space.reul.cleanarchitectureexample.domain.model.State
import space.reul.cleanarchitectureexample.domain.model.Venue

/**
 * Mapping to persist [Event] in a room database.
 */
@Entity
data class EventMapping(
    @PrimaryKey
    val id: String,
    val name: String,
    val url: String,
    val imageUrl: String,
    val date: String,
    val venue: String,
    val city: String,
    val state: String,
) {
    companion object {
        fun fromEvent(event: Event): EventMapping = EventMapping(
            id = event.id.orEmpty(),
            name = event.name.orEmpty(),
            url = event.url.orEmpty(),
            imageUrl = event.images?.firstOrNull()?.url.orEmpty(),
            date = event.dates?.start?.localDate.orEmpty(),
            venue = event._embedded?.venues?.firstOrNull()?.name.orEmpty(),
            city = event._embedded?.venues?.firstOrNull()?.city?.name.orEmpty(),
            state = event._embedded?.venues?.firstOrNull()?.state?.stateCode.orEmpty()
        )
    }

    fun toEvent() = Event(
        id = id,
        name = name,
        url = url,
        images = listOf(Image(url = imageUrl)),
        dates = Dates(start = DateInfo(localDate = date)),
        _embedded = EventEmbedded(
            venues = listOf(
                Venue(
                    name = venue,
                    city = City(name = city),
                    state = State(name = state, stateCode = state)
                )
            )
        )
    )
}