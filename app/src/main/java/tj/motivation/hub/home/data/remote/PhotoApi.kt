package tj.motivation.hub.home.data.remote

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import tj.motivation.hub.BuildConfig
import tj.motivation.hub.home.data.remote.dto.unsplash_dto.PhotoDto

interface PhotoApi {

    @GET("/photos/random")
    @Headers(value = ["Authorization: Client-ID ${BuildConfig.UNSPLASH_API_KEY}"])
    suspend fun getRandomPhoto(@Query("w") width: Float, @Query("h") height: Float): PhotoDto

    @GET("/photos")
    @Headers(value = ["Authorization: Client-ID ${BuildConfig.UNSPLASH_API_KEY}"])
    suspend fun getRandomPhotos(
        @Query("w") width: Float,
        @Query("h") height: Float,
    ): List<PhotoDto>

}