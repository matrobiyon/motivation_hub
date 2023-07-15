package tj.motivation.hub.home.domain.repository

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import tj.motivation.hub.core.util.Resource
import tj.motivation.hub.home.domain.model.quotes.Quotes

interface HomeRepository {

    fun getQuotes() : Flow<Resource<List<Quotes>>>

}