package space.reul.ticketmasterchallenge.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import space.reul.ticketmasterchallenge.domain.model.EventList
import kotlin.test.BeforeTest
import kotlin.test.Test

class ListEventsTest {
    private lateinit var subject: ListEvents
    private lateinit var repository: ListEvents.Repository

    @BeforeTest
    fun setUp() {
        repository = mockk()

        coEvery { repository.listEvents() } returns EventList(listOf())

        subject = ListEvents(
            Dispatchers.Unconfined,
            repository
        )
    }

    @Test
    fun `verify repository_liseEvents method is called`() = runTest {

        subject.invoke()

        coVerify { repository.listEvents() }
    }
}
