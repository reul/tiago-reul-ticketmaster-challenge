package space.reul.cleanarchitectureexample.domain.model

data class EventList (
    val events: List<Event>
)

data class Event (
    val name: String? = null,
    val type: String? = null,
    val id: String? = null,
    val test: Boolean? = null,
    val url: String? = null,
    val locale: String? = null,
    val images: List<Image>? = null,
    val sales: Sales? = null,
    val dates: Dates? = null,
    val classifications: List<Classification>? = null,
    val promoter: Promoter? = null,
    val promoters: List<Promoter>? = null,
    val info: String? = null,
    val pleaseNote: String? = null,
    val priceRanges: List<PriceRange>? = null,
    val products: List<Product>? = null,
    val seatmap: Seatmap? = null,
    val accessibility: Accessibility? = null,
    val ticketLimit: TicketLimit? = null,
    val ageRestrictions: AgeRestrictions? = null,
    val ticketing: Ticketing? = null,
    val links: EventLinks? = null,
    val embedded: EventEmbedded? = null,
    val outlets: List<Outlet>? = null,
    val doorsTimes: DoorsTimes? = null
)

data class Accessibility (
    val ticketLimit: Long? = null,
    val info: String? = null,
    val url: String? = null,
    val urlText: String? = null
)

data class AgeRestrictions (
    val legalAgeEnforced: Boolean? = null
)

data class Classification (
    val primary: Boolean? = null,
    val segment: Genre? = null,
    val genre: Genre? = null,
    val subGenre: Genre? = null,
    val type: Genre? = null,
    val subType: Genre? = null,
    val family: Boolean? = null
)

data class Genre (
    val id: String? = null,
    val name: String? = null
)

data class Dates (
    val start: DateInfo? = null,
    val timezone: String? = null,
    val status: Status? = null,
    val spanMultipleDays: Boolean? = null,
    val initialStartDate: DoorsTimes? = null
)

data class DoorsTimes (
    val localDate: String? = null,
    val localTime: String? = null,
    val dateTime: String? = null
)

data class DateInfo (
    val localDate: String? = null,
    val localTime: String? = null,
    val dateTime: String? = null,
    val dateTBD: Boolean? = null,
    val dateTBA: Boolean? = null,
    val timeTBA: Boolean? = null,
    val noSpecificTime: Boolean? = null
)

data class Status (
    val code: String? = null
)

data class EventEmbedded (
    val venues: List<Venue>? = null,
    val attractions: List<Attraction>? = null
)

data class Attraction (
    val name: String? = null,
    val type: String? = null,
    val id: String? = null,
    val test: Boolean? = null,
    val url: String? = null,
    val locale: String? = null,
    val externalLinks: ExternalLinks? = null,
    val aliases: List<String>? = null,
    val images: List<Image>? = null,
    val classifications: List<Classification>? = null,
    val upcomingEvents: Map<String, Long>? = null,
    val links: AttractionLinks? = null
)

data class ExternalLinks (
    val twitter: List<ExternalLink>? = null,
    val wiki: List<ExternalLink>? = null,
    val facebook: List<ExternalLink>? = null,
    val instagram: List<ExternalLink>? = null,
    val homepage: List<ExternalLink>? = null,
    val youtube: List<ExternalLink>? = null,
    val itunes: List<ExternalLink>? = null,
    val lastfm: List<ExternalLink>? = null,
    val spotify: List<ExternalLink>? = null,
    val musicbrainz: List<Musicbrainz>? = null
)

data class ExternalLink (
    val url: String? = null
)

data class Musicbrainz (
    val id: String? = null
)

data class Image (
    val ratio: String? = null,
    val url: String? = null,
    val width: Long? = null,
    val height: Long? = null,
    val fallback: Boolean? = null,
    val attribution: String? = null
)

data class AttractionLinks (
    val self: WebUrl? = null
)

data class WebUrl (
    val href: String? = null
)

data class Venue (
    val name: String? = null,
    val type: String? = null,
    val id: String? = null,
    val test: Boolean? = null,
    val url: String? = null,
    val locale: String? = null,
    val images: List<Image>? = null,
    val postalCode: String? = null,
    val timezone: String? = null,
    val city: City? = null,
    val state: State? = null,
    val country: Country? = null,
    val address: Address? = null,
    val location: Location? = null,
    val markets: List<Genre>? = null,
    val dmas: List<DMA>? = null,
    val boxOfficeInfo: BoxOfficeInfo? = null,
    val parkingDetail: String? = null,
    val accessibleSeatingDetail: String? = null,
    val generalInfo: GeneralInfo? = null,
    val upcomingEvents: UpcomingEvents? = null,
    val links: AttractionLinks? = null,
    val ada: Ada? = null,
    val aliases: List<String>? = null,
    val social: Social? = null
)

data class Ada (
    val adaPhones: String? = null,
    val adaCustomCopy: String? = null,
    val adaHours: String? = null
)

data class Address (
    val line1: String? = null
)

data class BoxOfficeInfo (
    val phoneNumberDetail: String? = null,
    val openHoursDetail: String? = null,
    val acceptedPaymentDetail: String? = null,
    val willCallDetail: String? = null
)

data class City (
    val name: String? = null
)

data class Country (
    val name: String? = null,
    val countryCode: String? = null
)

data class DMA (
    val id: Long? = null
)

data class GeneralInfo (
    val generalRule: String? = null,
    val childRule: String? = null
)

data class Location (
    val longitude: String? = null,
    val latitude: String? = null
)

data class Social (
    val twitter: Twitter? = null
)

data class Twitter (
    val handle: String? = null
)

data class State (
    val name: String? = null,
    val stateCode: String? = null
)

data class UpcomingEvents (
    val ticketmaster: Long? = null,
    val total: Long? = null,
    val filtered: Long? = null,
    val tmr: Long? = null
)

data class EventLinks (
    val self: WebUrl? = null,
    val attractions: List<WebUrl>? = null,
    val venues: List<WebUrl>? = null
)

data class Outlet (
    val url: String? = null,
    val type: String? = null
)

data class PriceRange (
    val type: String? = null,
    val currency: String? = null,
    val min: Double? = null,
    val max: Double? = null
)

data class Product (
    val name: String? = null,
    val id: String? = null,
    val url: String? = null,
    val type: String? = null,
    val classifications: List<Classification>? = null
)

data class Promoter (
    val id: String? = null,
    val name: String? = null,
    val description: String? = null
)

data class Sales (
    val public: Public? = null,
    val presales: List<Presale>? = null
)

data class Presale (
    val startDateTime: String? = null,
    val endDateTime: String? = null,
    val name: String? = null
)

data class Public (
    val startDateTime: String? = null,
    val startTBD: Boolean? = null,
    val startTBA: Boolean? = null,
    val endDateTime: String? = null
)

data class Seatmap (
    val staticURL: String? = null
)

data class TicketLimit (
    val info: String? = null
)

data class Ticketing (
    val safeTix: AllInclusivePricing? = null,
    val allInclusivePricing: AllInclusivePricing? = null
)

data class AllInclusivePricing (
    val enabled: Boolean? = null
)