package tj.motivation.hub.home.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import tj.motivation.hub.core.util.Resource
import tj.motivation.hub.home.data.local.QuotesDao
import tj.motivation.hub.home.data.remote.PhotoApi
import tj.motivation.hub.home.data.remote.QuotesApi
import tj.motivation.hub.home.domain.model.quotes.Quotes
import tj.motivation.hub.home.domain.repository.HomeRepository
import java.io.IOException
import java.lang.Exception

class HomeRepositoryImpl(
    private val dao: QuotesDao,
    private val quotesApi: QuotesApi,
    private val photoApi: PhotoApi,
) : HomeRepository {
    override fun getRandomQuoteAndPhoto(width : Float,height : Float): Flow<Resource<List<Quotes>>> = flow {
        emit(Resource.Loading())

        val cachedQuotes: List<Quotes> = dao.getAllQuotes().map { it.toQuotes() }
        Log.d("TAG", "getRandomQuoteAndPhoto: start success")
        emit(Resource.Success(cachedQuotes))

        try {
            //Getting just one quotes for example purposes
            val randomPhoto = photoApi.getRandomPhoto(width,height)
            Log.d("TAG", "getRandomQuoteAndPhoto: $randomPhoto")
            val randomQuote = quotesApi.getRandomQuote()[0].toQuotes(randomPhoto.toPhoto())
//            dao.deleteQuotesById(dao.getAllQuotes().map { it.id })
            dao.insertQuotes(listOf(randomQuote.toQuotesEntity()))

        }catch (e : IOException){
            emit(Resource.Error(message = "IO exception...",data = cachedQuotes))
        }catch (e : HttpException){
            emit(Resource.Error(message = "Http exception...",data = cachedQuotes))
        }catch (e : Exception){
            Log.d("TAG", "getRandomQuoteAndPhoto: ${e.message}")
        }
        Log.d("TAG", "getRandomQuoteAndPhoto: success")

        val newQuotesForCaching = dao.getAllQuotes().map { it.toQuotes() }
        emit(Resource.Success(newQuotesForCaching))
    }
}