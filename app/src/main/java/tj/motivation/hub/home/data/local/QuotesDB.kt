package tj.motivation.hub.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import tj.motivation.hub.home.data.local.entity.QuotesEntity

@Database(entities = [QuotesEntity::class], version = 1)
abstract class QuotesDB : RoomDatabase() {

    abstract fun getQuotesDao() : QuotesDao

}