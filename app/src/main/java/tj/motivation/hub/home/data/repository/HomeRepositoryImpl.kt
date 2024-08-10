package tj.motivation.hub.home.data.repository

import android.util.Log
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import tj.motivation.hub.BuildConfig
import tj.motivation.hub.core.util.Resource
import tj.motivation.hub.home.data.local.QuotesDao
import tj.motivation.hub.home.data.local.entity.QuotesEntity
import tj.motivation.hub.home.data.remote.PhotoApi
import tj.motivation.hub.home.data.remote.QuotesApi
import tj.motivation.hub.home.data.remote.dto.quotes_dto.QuotesDto
import tj.motivation.hub.home.data.remote.dto.unsplash_dto.PhotoDto
import tj.motivation.hub.home.domain.model.quotes.Quotes
import tj.motivation.hub.home.domain.repository.HomeRepository
import java.io.IOException
import java.lang.Exception

class HomeRepositoryImpl(
    private val dao: QuotesDao,
    private val quotesApi: QuotesApi,
    private val photoApi: PhotoApi,
    private val generativeModel: GenerativeModel
) : HomeRepository {

    override fun getRandomQuoteAndPhoto(width: Float, height: Float): Flow<Resource<List<Quotes>>> =
        flow {
            emit(Resource.Loading())

            val cachedQuotes: List<Quotes> = dao.getAllQuotes().map { it.toQuotes() }
            emit(Resource.Success(cachedQuotes))

            try {
                val randomPhotos: List<PhotoDto> = photoApi.getRandomPhotos(width, height)
                val randomQuotesDto = quotesApi.getQuotes()

                val photosAndQuotes = mutableListOf<QuotesEntity>()


                randomPhotos.forEachIndexed { index, photoDto ->

                    //Asking Gemini AI which word must be highlighted in the texts
                    val prompt =
                        "Give me just the starting and ending index of the word which must be with yellow color in this text without any explanation in this format: number,number : ${randomQuotesDto[index].quote}"

                    val response = generativeModel.generateContent(prompt)
                    Log.d("TAG", "getRandomQuoteAndPhoto: ${response.text}")
                    val responseText = response.text ?: "-1"
                    val highlightedWordIndex = mutableMapOf(-1 to -1)
                    if (responseText.contains(",")) {
                        val split = responseText.split(",", limit = 2)
                        Log.d("TAG", "HomeScreenTest: ${split[0]} and ${split[1]}")
                        highlightedWordIndex[split[0].toInt()] = split[1].trim().toInt()
                    }
                    photosAndQuotes.add(
                        index,
                        randomQuotesDto[index].toQuotes(
                            photo = photoDto.toPhoto(),
                            yellowWordStart = highlightedWordIndex.keys.first(),
                            yellowWordEnd = highlightedWordIndex.values.first()
                        ).toQuotesEntity()
                    )
                }
//            dao.deleteQuotesById(dao.getAllQuotes().map { it.id })
                dao.insertQuotes(photosAndQuotes)

            } catch (e: IOException) {
                emit(Resource.Error(message = "IO exception...", data = cachedQuotes))
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Http exception...", data = cachedQuotes))
            } catch (e: Exception) {
                Log.d("TAG", "getRandomQuoteAndPhoto: ${e.message}")
            }
            Log.d("TAG", "getRandomQuoteAndPhoto: success")

            val newQuotesForCaching = dao.getAllQuotes().map { it.toQuotes() }
            emit(Resource.Success(newQuotesForCaching))
        }
}