package space.reul.cleanarchitectureexample.data.repository

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import space.reul.cleanarchitectureexample.domain.model.EventList
import space.reul.cleanarchitectureexample.data.webapi.TicketmasterService
import space.reul.cleanarchitectureexample.domain.model.Event
import kotlin.test.BeforeTest
import kotlin.test.Test

class EventsRepositoryImplTest {
    private lateinit var subject: EventsRepositoryImpl
    private lateinit var service: TicketmasterService

    private val API_KEY = "API_KEY"

    @BeforeTest
    fun setUp() {
        service = mockk()

        coEvery { service.listEvents(API_KEY) } returns EventList(arrayListOf(
            Event(name = "Event 1", type = "2021-01-01"),
            Event(name = "Event 2", type = "2021-01-02"),
            Event(name = "Event 3", type = "2021-01-03")
        ))

        subject = EventsRepositoryImpl(API_KEY, service)
    }

    @Test
    fun `verify listEvents calls web service method with the given API_KEY`() = runTest {
        subject.listEvents()

        coVerify { service.listEvents(API_KEY) }
    }

    @Test
    fun `verify listEvents returns the list of events from the web service`() = runTest {
        val result = subject.listEvents()

        assert(result == EventList(arrayListOf(
            Event(name = "Event 1", type = "2021-01-01"),
            Event(name = "Event 2", type = "2021-01-02"),
            Event(name = "Event 3", type = "2021-01-03")
        )))
    }
}

