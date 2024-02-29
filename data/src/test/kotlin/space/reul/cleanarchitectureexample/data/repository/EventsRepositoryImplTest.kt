package space.reul.cleanarchitectureexample.data.repository

import android.content.SharedPreferences
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import space.reul.cleanarchitectureexample.data.database.db.EventsDatabase
import space.reul.cleanarchitectureexample.data.database.mapping.EventMapping
import space.reul.cleanarchitectureexample.data.webapi.TicketmasterService
import space.reul.cleanarchitectureexample.domain.model.Event
import space.reul.cleanarchitectureexample.domain.model.EventList
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EventsRepositoryImplTest {
    private lateinit var subject: EventsRepositoryImpl
    private lateinit var service: TicketmasterService
    private lateinit var database: EventsDatabase
    private lateinit var sharedPreferences: SharedPreferences

    private val API_KEY = "API_KEY"

    @BeforeTest
    fun setUp() {
        service = mockk()
        database = mockk()
        sharedPreferences = mockk {
            every { edit() } returns mockk()
            every { edit().putLong(any(), any()) } returns mockk()
            every { edit().putLong(any(), any()).apply() } returns Unit
            every { getLong("last_update", 0L) } returns 2L

        }

        every { database.eventMappingDao() } returns mockk {
            every { loadAll() } returns arrayListOf(
                EventMapping(id = "1", name = "Event From DB 1", "", "", "", "", "", ""),
                EventMapping(id = "2", name = "Event From DB 2", "", "", "", "", "", ""),
            )
            every { insertAll(any()) } returns Unit
        }

        coEvery { service.listEvents(API_KEY) } returns EventList(
            arrayListOf(
                Event(name = "Event 1"), Event(name = "Event 2"), Event(name = "Event 3")

            )
        )

        subject = EventsRepositoryImpl(API_KEY, service, database, sharedPreferences)
    }

    @Test
    fun `verify listEvents calls web service method with the given API_KEY if cache is expired`() =
        runTest {
            subject.listEvents()

            coVerify { service.listEvents(API_KEY) }
        }

    @Test
    fun `verify listEvents returns the list of events from the web service if cache is expired`() =
        runTest {
            val result = subject.listEvents()

            assertEquals(
                result, EventList(
                    arrayListOf(
                        Event(name = "Event 1"), Event(name = "Event 2"), Event(name = "Event 3")
                    )
                )
            )
        }

    @Test
    fun `verify listEvents returns the list of events from the database if cache is not expired`() =
        runTest {
            every {
                sharedPreferences.getLong(
                    "last_update", 0L
                )
            } returns System.currentTimeMillis()

            val result = subject.listEvents()

            assertEquals(
                result.events.map { it.name }, arrayListOf(
                    "Event From DB 1", "Event From DB 2"
                )
            )
        }
}

