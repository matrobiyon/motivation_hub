package tj.motivation.hub.home.data.remote

import retrofit2.http.GET
import tj.motivation.hub.home.data.remote.dto.unsplash_dto.PhotoDto

interface PhotoApi {

    @GET("/photos/random")
    suspend fun getRandomPhoto() : PhotoDto
}