package tj.motivation.hub.home.data.remote

import retrofit2.http.GET
import retrofit2.http.HEAD
import tj.motivation.hub.BuildConfig
import tj.motivation.hub.home.data.remote.dto.unsplash_dto.PhotoDto
import java.io.File
import java.io.FileInputStream

interface PhotoApi {

    @GET("/photos/random")
    @HEAD(value = "Authorization : ${BuildConfig.UNSPLASH_API_KEY}")
    suspend fun getRandomPhoto() : PhotoDto

    private fun getUnsplashApiKey(key: String, file: String = "local.properties"): Any {
        val properties = java.util.Properties()
        val localProperties = File(file)
        if (localProperties.isFile) {
            java.io.InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8)
                .use { reader ->
                    properties.load(reader)
                }
        } else error("File from not found")

        return properties.getProperty(key)
    }

}