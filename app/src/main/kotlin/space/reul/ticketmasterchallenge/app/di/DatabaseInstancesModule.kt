package space.reul.ticketmasterchallenge.app.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import space.reul.ticketmasterchallenge.data.database.db.EventsDatabase

@Module
@InstallIn(SingletonComponent::class)
object DatabaseInstancesModule {
    private const val DATABASE_NAME = "events_database"

    @Provides
    fun provideEventsDatabase(application: Application): EventsDatabase =
        Room.databaseBuilder(
            application.applicationContext,
            EventsDatabase::class.java, DATABASE_NAME
        ).build()
}