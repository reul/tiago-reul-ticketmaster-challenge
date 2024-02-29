package space.reul.ticketmasterchallenge.domain.model

data class Event(
    val name: String? = null,
    val id: String? = null,
    val url: String? = null,
    val images: List<Image>? = null,
    val dates: Dates? = null,
    val _embedded: EventEmbedded? = null,
)
