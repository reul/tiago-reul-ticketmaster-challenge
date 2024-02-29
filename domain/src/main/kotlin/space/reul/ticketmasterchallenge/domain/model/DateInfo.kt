package space.reul.ticketmasterchallenge.domain.model

data class DateInfo(
    val localDate: String? = null,
    val localTime: String? = null,
    val dateTime: String? = null,
    val dateTBD: Boolean? = null,
    val dateTBA: Boolean? = null,
    val timeTBA: Boolean? = null,
    val noSpecificTime: Boolean? = null
)
