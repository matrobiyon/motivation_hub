package tj.motivation.hub.home.domain.model.quotes

import tj.motivation.hub.home.data.local.entity.QuotesEntity

data class Quotes(
    val author: String,
    val quote: String,
    val background: String,
    val backgroundId: String,
    val id: Int? = null
) {
    fun toQuotesEntity(): QuotesEntity {
        return QuotesEntity(
            author, quote, background, backgroundId
        )
    }
}
