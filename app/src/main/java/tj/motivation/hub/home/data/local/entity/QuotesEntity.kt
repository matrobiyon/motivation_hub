package tj.motivation.hub.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import tj.motivation.hub.home.domain.model.quotes.Quotes

@Entity(tableName = "quotes")
data class QuotesEntity(
    val author: String,
    val quote: String,
    val image: String,
    val imageThumb: String,
    val likes: Int = 0,
    val yellowWordStart : Int? = null,
    val yellowWordEnd : Int? = null,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
    fun toQuotes(): Quotes {
        return Quotes(author, quote, image, imageThumb, likes)
    }
}
