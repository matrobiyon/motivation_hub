package tj.motivation.hub.home.data.remote.dto.unsplash_dto

import tj.motivation.hub.home.domain.model.unsplash.Photo

data class PhotoDto(
    val alt_description: String,
    val blur_hash: String,
    val color: String,
    val created_at: String,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: LinksDto,
    val promoted_at: String,
    val public_domain: Boolean,
    val slug: String,
    val updated_at: String,
    val urls: UrlsDto,
    val user: UserDto,
    val views: Int,
    val width: Int
) {
    fun toPhoto() : Photo {
        return Photo(
            id = id,
            urls = urls.toUrls(),
            views = views,
            width = width,
            likes = likes
        )
    }
}