package space.reul.ticketmasterchallenge.domain.model

data class Image(
    val ratio: String? = null,
    val url: String? = null,
    val width: Long? = null,
    val height: Long? = null,
    val fallback: Boolean? = null,
    val attribution: String? = null
)
