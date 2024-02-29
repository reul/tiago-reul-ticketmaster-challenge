package space.reul.cleanarchitectureexample.data.database.db

import androidx.room.Database
import space.reul.cleanarchitectureexample.data.database.dao.EventMappingDao
import space.reul.cleanarchitectureexample.data.database.mapping.EventMapping

/**
 * Room database for the event mapping table.
 */
@Database(entities = [EventMapping::class], version = 1)
abstract class EventsDatabase : androidx.room.RoomDatabase() {
    /**
     * Data access object for the event mapping table.
     */
    abstract fun eventMappingDao(): EventMappingDao
}
