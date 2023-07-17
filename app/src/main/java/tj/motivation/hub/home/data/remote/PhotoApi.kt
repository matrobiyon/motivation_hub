package tj.motivation.hub.home.data.remote

import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Headers
import tj.motivation.hub.BuildConfig
import tj.motivation.hub.home.data.remote.dto.unsplash_dto.PhotoDto
import java.io.File
import java.io.FileInputStream

interface PhotoApi {

    @GET("/photos/random")
    @Headers(value = ["Authorization: ${BuildConfig.UNSPLASH_API_KEY}"])
    suspend fun getRandomPhoto() : PhotoDto

}