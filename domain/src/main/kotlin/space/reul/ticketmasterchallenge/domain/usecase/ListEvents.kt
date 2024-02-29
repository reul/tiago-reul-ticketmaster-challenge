package space.reul.ticketmasterchallenge.domain.usecase

import kotlinx.coroutines.withContext
import space.reul.ticketmasterchallenge.domain.model.EventList
import kotlin.coroutines.CoroutineContext

/**
 * Use case for listing events.
 *
 * @property backgroundContext The coroutine context to run the use case on.
 * @property repository The repository to get the events from.
 */
class ListEvents(
    private val backgroundContext: CoroutineContext,
    private val repository: Repository
) : UseCase<EventList> {
    override suspend operator fun invoke(): EventList = withContext(backgroundContext) {
        repository.listEvents()
    }

    /**
     * Repository interface for listing events.
     */
    interface Repository {
        suspend fun listEvents(): EventList
    }
}
