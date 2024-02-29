package space.reul.ticketmasterchallenge.domain.usecase

/**
 * Use case for a single operation.
 *
 * @param T The return type of the use case.
 */
interface UseCase<T> {
    /**
     * Invokes the use case.
     *
     * @return The result of the use case.
     */
    suspend operator fun invoke(): T
}
