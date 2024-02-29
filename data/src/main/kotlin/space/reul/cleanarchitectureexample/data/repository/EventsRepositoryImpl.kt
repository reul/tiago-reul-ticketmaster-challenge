package space.reul.cleanarchitectureexample.data.repository

import android.content.SharedPreferences
import space.reul.cleanarchitectureexample.data.database.db.EventsDatabase
import space.reul.cleanarchitectureexample.data.database.mapping.EventMapping
import space.reul.cleanarchitectureexample.data.webapi.TicketmasterService
import space.reul.cleanarchitectureexample.domain.model.Event
import space.reul.cleanarchitectureexample.domain.model.EventList
import space.reul.cleanarchitectureexample.domain.usecase.ListEvents
import java.util.concurrent.TimeUnit


/**
 * Repository implementation for listing events. Caches the events in a local database and uses a
 * web service to fetch the events if the cache is outdated.
 *
 * Cache lasts 2h.
 *
 * @property apiKey The API key to use for the Ticketmaster web service.
 * @property webApi The web service to use for listing [Event].
 * @property database The database to use for caching [Event].
 * @property sharedPreferences The shared preferences to use for tracking duration of [Event] cache.
 */
class EventsRepositoryImpl(
    private val apiKey: String,
    private val webApi: TicketmasterService = TicketmasterService(),
    private val database: EventsDatabase,
    private val sharedPreferences: SharedPreferences
) : ListEvents.Repository {
    private companion object {
        const val LAST_UPDATE_KEY = "last_update"
        val CACHE_DURATION = TimeUnit.HOURS.toMillis(2L)
    }

    override suspend fun listEvents(): EventList {
        try {
            return if (sharedPreferences.getLong(
                    LAST_UPDATE_KEY, 0L
                ) + CACHE_DURATION > System.currentTimeMillis()
            ) {
                EventList(database.eventMappingDao().loadAll().map { it.toEvent() })
            } else {
                webApi.listEvents(apiKey).also { event ->
                    database.eventMappingDao().insertAll(event.events.map { mappedEvent ->
                        EventMapping.fromEvent(mappedEvent)
                    })
                    sharedPreferences.edit().putLong(LAST_UPDATE_KEY, System.currentTimeMillis())
                        .apply()
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }
}
