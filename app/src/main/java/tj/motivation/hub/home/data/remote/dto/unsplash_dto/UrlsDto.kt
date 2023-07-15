package tj.motivation.hub.home.data.remote.dto.unsplash_dto

import tj.motivation.hub.home.domain.model.unsplash.Urls

data class UrlsDto(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val small_s3: String,
    val thumb: String
) {
    fun toUrls(): Urls {
        return Urls(
            full, raw, regular, small, small_s3, thumb
        )
    }
}