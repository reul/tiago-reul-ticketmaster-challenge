package space.reul.ticketmasterchallenge.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import space.reul.ticketmasterchallenge.data.repository.EventsRepositoryImpl
import space.reul.ticketmasterchallenge.domain.usecase.ListEvents

/**
 * Binds the repository interfaces to their implementations.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryInterfacesModule {
    @Binds
    abstract fun bindListEventsRepository(
        repositoryImpl: EventsRepositoryImpl
    ): ListEvents.Repository
}
