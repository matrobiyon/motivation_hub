package tj.motivation.hub.home.domain.model.unsplash

data class Photo(
    val id : String,
    val urls: Urls,
    val views: Int,
    val width: Int,
    val likes : Int,
)