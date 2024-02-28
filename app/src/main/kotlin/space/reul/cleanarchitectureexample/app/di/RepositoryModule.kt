package space.reul.cleanarchitectureexample.app.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import space.reul.cleanarchitectureexample.app.BuildConfig
import space.reul.cleanarchitectureexample.data.repository.EventsRepositoryImpl
import space.reul.cleanarchitectureexample.domain.usecase.ListEvents
import javax.inject.Singleton

/**
 * Binds the repository interfaces to their implementations.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryInterfacesModule {
    @Singleton
    @Binds
    abstract fun bindListShibasRepository(
        repositoryImpl: EventsRepositoryImpl
    ): ListEvents.Repository
}

/**
 * Provides instances of the repository implementations.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryInstancesModule {
    @Singleton
    @Provides
    fun providesShibaRepositoryImpl(): EventsRepositoryImpl =
        EventsRepositoryImpl(BuildConfig.TICKETMASTER_API_KEY)

}
