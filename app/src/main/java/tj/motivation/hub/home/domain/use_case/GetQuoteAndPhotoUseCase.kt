package tj.motivation.hub.home.domain.use_case

import kotlinx.coroutines.flow.Flow
import tj.motivation.hub.core.util.Resource
import tj.motivation.hub.home.domain.model.quotes.Quotes
import tj.motivation.hub.home.domain.repository.HomeRepository

class GetQuoteAndPhotoUseCase(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(width : Float,height : Float): Flow<Resource<List<Quotes>>> {
        return homeRepository.getRandomQuoteAndPhoto(width,height)
    }

}