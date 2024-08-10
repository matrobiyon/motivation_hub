package tj.motivation.hub.home.domain.model.quotes

import tj.motivation.hub.home.data.local.entity.QuotesEntity

data class Quotes(
    val author: String,
    val quote: String,
    val image: String,
    val imageThumb: String,
    val likes: Int = 0,
    val yellowWordStart: Int? = null,
    val yellowWordEnd: Int? = null,
    val id: Int? = null
) {
    fun toQuotesEntity(): QuotesEntity {
        return QuotesEntity(
            author, quote, image, imageThumb, likes, yellowWordStart, yellowWordEnd
        )
    }
}
