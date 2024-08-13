package tj.motivation.hub.home.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import tj.motivation.hub.core.util.Resource
import tj.motivation.hub.home.data.local.QuotesDao
import tj.motivation.hub.home.data.local.entity.QuotesEntity
import tj.motivation.hub.home.data.remote.PhotoApi
import tj.motivation.hub.home.data.remote.QuotesApi
import tj.motivation.hub.home.data.remote.dto.unsplash_dto.PhotoDto
import tj.motivation.hub.home.domain.model.quotes.Quotes
import tj.motivation.hub.home.domain.repository.HomeRepository
import java.io.IOException

class HomeRepositoryImpl(
    private val dao: QuotesDao,
    private val quotesApi: QuotesApi,
    private val photoApi: PhotoApi,
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
                        val test = randomQuotesDto[index].toQuotes(
                            photo = photoDto.toPhoto()).toQuotesEntity()
                        photosAndQuotes.add(
                            index,
                            test
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

            val newQuotesForCaching = dao.getAllQuotes().map { it.toQuotes() }
            emit(Resource.Success(newQuotesForCaching))
        }
}