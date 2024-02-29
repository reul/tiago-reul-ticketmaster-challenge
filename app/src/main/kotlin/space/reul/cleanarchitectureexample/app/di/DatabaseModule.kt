package space.reul.cleanarchitectureexample.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import space.reul.cleanarchitectureexample.data.database.db.EventsDatabase

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {
    @Binds
    abstract fun bindEventsDatabase(
        database: EventsDatabase
    ): EventsDatabase
}
