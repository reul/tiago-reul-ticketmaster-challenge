package space.reul.cleanarchitectureexample.app.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import space.reul.cleanarchitectureexample.app.BuildConfig
import space.reul.cleanarchitectureexample.data.repository.EventsRepositoryImpl

/**
 * Provides instances of the repository implementations.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryInstancesModule {
    private const val EVENTS_SHARED_PREFERENCES_NAME = "events"

    @Provides
    fun provideEventsRepositoryImpl(application: Application): EventsRepositoryImpl =
        EventsRepositoryImpl(
            apiKey = BuildConfig.TICKETMASTER_API_KEY,
            database = DatabaseInstancesModule.provideEventsDatabase(application),
            sharedPreferences = application.getSharedPreferences(
                EVENTS_SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE
            )
        )
}
