package space.reul.cleanarchitectureexample.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class ListShibasTest {
    private lateinit var subject: ListEvents
    private lateinit var repository: ListEvents.Repository

    @BeforeTest
    fun setUp() {
        repository = mockk()

        coEvery { repository.listShibas() } returns arrayListOf()

        subject = ListEvents(
            Dispatchers.Unconfined,
            repository
        )
    }

    @Test
    fun `verify repository_listShibas method is called`() = runTest {

        subject.invoke()

        coVerify { repository.listShibas() }
    }
}
