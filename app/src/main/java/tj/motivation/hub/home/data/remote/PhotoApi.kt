package tj.motivation.hub.home.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import tj.motivation.hub.BuildConfig
import tj.motivation.hub.home.data.remote.dto.unsplash_dto.PhotoDto
import java.io.File
import java.io.FileInputStream

interface PhotoApi {

    @GET("/photos/random")
    @Headers(value = ["Authorization: ${BuildConfig.UNSPLASH_API_KEY}"])
    suspend fun getRandomPhoto(@Query("w") width : Float,@Query("h") height : Float) : Response<PhotoDto>

}