package space.reul.cleanarchitectureexample.data.repository

import space.reul.cleanarchitectureexample.data.webapi.TicketmasterService
import space.reul.cleanarchitectureexample.domain.model.Event
import space.reul.cleanarchitectureexample.domain.model.EventList
import space.reul.cleanarchitectureexample.domain.usecase.ListEvents


/**
 * Repository implementation for listing events.
 *
 * @property apiKey The API key to use for the Ticketmaster web service.
 * @property webApi The web service to use for listing [Event].
 */
class EventsRepositoryImpl(
    private val apiKey: String,
    private val webApi: TicketmasterService = TicketmasterService()
) : ListEvents.Repository {

    override suspend fun listEvents(): EventList {
        return webApi.listEvents(apiKey)
    }
}
