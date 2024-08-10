package tj.motivation.hub.home.data.remote.dto.quotes_dto

import com.google.gson.annotations.SerializedName
import tj.motivation.hub.home.domain.model.quotes.Quotes
import tj.motivation.hub.home.domain.model.unsplash.Photo

data class QuotesDto(
    @SerializedName("a") val author: String,
    @SerializedName("h") val html: String,
    @SerializedName("q") val quote: String,
    @SerializedName("c") val count: String
) {
    fun toQuotes(photo: Photo, yellowWordStart: Int? = null, yellowWordEnd: Int? = null): Quotes {
        return Quotes(
            author,
            quote,
            photo.urls.small_s3,
            photo.urls.thumb,
            photo.likes,
            yellowWordStart = yellowWordStart,
            yellowWordEnd = yellowWordEnd
        )
    }
}