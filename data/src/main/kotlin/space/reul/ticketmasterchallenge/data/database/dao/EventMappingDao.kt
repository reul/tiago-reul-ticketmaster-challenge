package space.reul.ticketmasterchallenge.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import space.reul.ticketmasterchallenge.data.database.mapping.EventMapping

/**
 * Data access object for the event mapping table.
 */
@Dao
interface EventMappingDao {
    /**
     * Loads all event mappings from the database.
     */
    @Query("SELECT * FROM EventMapping")
    fun loadAll(): List<EventMapping>

    /**
     * Inserts a list of event mappings into the database.
     */
    @Insert
    fun insertAll(events: List<EventMapping>)

    /**
     * Deletes an event mapping from the database.
     */
    @Delete
    fun delete(event: EventMapping)
}
