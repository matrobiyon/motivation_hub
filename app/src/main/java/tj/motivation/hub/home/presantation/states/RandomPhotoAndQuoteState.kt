package tj.motivation.hub.home.presantation.states

import tj.motivation.hub.home.domain.model.quotes.Quotes

data class RandomPhotoAndQuoteState(
    val data : List<Quotes> = emptyList(),
    val isLoading : Boolean = false
)
