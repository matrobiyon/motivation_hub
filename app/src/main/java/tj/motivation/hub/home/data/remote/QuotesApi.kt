package tj.motivation.hub.home.data.remote

import retrofit2.http.GET
import tj.motivation.hub.home.data.remote.dto.quotes_dto.QuotesDto

interface QuotesApi {

    @GET("quotes/")
    suspend fun getQuotes() : List<QuotesDto>

    @GET("random/")
    suspend fun getRandomQuote() : List<QuotesDto>

}