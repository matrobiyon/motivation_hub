package tj.motivation.hub.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tj.motivation.hub.home.data.local.entity.QuotesEntity

@Dao
interface QuotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuotes(quotesEntity: List<QuotesEntity>)

    @Query("DELETE FROM quotes WHERE backgroundsId IN(:quotesId)")
    suspend fun deleteQuotesByBackgroundId(quotesId: List<String>)

    @Query("SELECT * FROM quotes")
    fun getAllQuotes(): List<QuotesEntity>

}