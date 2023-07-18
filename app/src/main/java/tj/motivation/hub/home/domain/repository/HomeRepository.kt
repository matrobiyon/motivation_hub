package tj.motivation.hub.home.domain.repository

import kotlinx.coroutines.flow.Flow
import tj.motivation.hub.core.util.Resource
import tj.motivation.hub.home.domain.model.quotes.Quotes

interface HomeRepository {
    fun getRandomQuoteAndPhoto(width : Float,height : Float) : Flow<Resource<List<Quotes>>>

}