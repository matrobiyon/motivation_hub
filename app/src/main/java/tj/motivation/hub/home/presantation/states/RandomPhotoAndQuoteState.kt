package tj.motivation.hub.home.presantation.states

import tj.motivation.hub.home.domain.model.quotes.Quotes

data class RandomPhotoAndQuoteState(
    private val data : List<Quotes> = emptyList(),
    private val isLoading : Boolean = false
)
