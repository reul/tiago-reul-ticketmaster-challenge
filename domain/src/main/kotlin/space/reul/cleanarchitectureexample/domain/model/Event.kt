package space.reul.cleanarchitectureexample.domain.model

data class EventList(
    val events: List<Event>
)

data class Event(
    val name: String? = null,
    val id: String? = null,
    val url: String? = null,
    val images: List<Image>? = null,
    val dates: Dates? = null,
    val _embedded: EventEmbedded? = null,
)

data class Dates(
    val start: DateInfo? = null,
)

data class DateInfo(
    val localDate: String? = null,
    val localTime: String? = null,
    val dateTime: String? = null,
    val dateTBD: Boolean? = null,
    val dateTBA: Boolean? = null,
    val timeTBA: Boolean? = null,
    val noSpecificTime: Boolean? = null
)

data class EventEmbedded(
    val venues: List<Venue>? = null,
)

data class Image(
    val ratio: String? = null,
    val url: String? = null,
    val width: Long? = null,
    val height: Long? = null,
    val fallback: Boolean? = null,
    val attribution: String? = null
)

data class WebUrl(
    val href: String? = null
)

data class Venue(
    val id: String? = null,
    val name: String? = null,
    val city: City? = null,
    val state: State? = null,
)

data class City(
    val name: String? = null
)

data class State(
    val name: String? = null,
    val stateCode: String? = null
)
