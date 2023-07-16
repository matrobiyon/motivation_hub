package tj.motivation.hub.home.domain.repository

import kotlinx.coroutines.flow.Flow
import tj.motivation.hub.core.util.Resource
import tj.motivation.hub.home.domain.model.quotes.Quotes

interface HomeRepository {
    fun getRandomQuoteAndPhoto() : Flow<Resource<List<Quotes>>>

}