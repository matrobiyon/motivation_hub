package tj.motivation.hub.home.presantation.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tj.motivation.hub.core.util.Resource
import tj.motivation.hub.home.domain.model.events.UIEvent
import tj.motivation.hub.home.domain.use_case.GetQuoteAndPhotoUseCase
import tj.motivation.hub.home.presantation.states.RandomPhotoAndQuoteState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getQuoteAndPhotoUseCase: GetQuoteAndPhotoUseCase
) : ViewModel() {

    private var _randomPhotoAndQuoteState = mutableStateOf(RandomPhotoAndQuoteState())
    val randomPhotoAndQuoteState: State<RandomPhotoAndQuoteState> = _randomPhotoAndQuoteState

    private var _errorEvent = MutableSharedFlow<UIEvent>()
    val errorEvent = _errorEvent.asSharedFlow()

    private var job: Job? = null

    fun getRandomQuoteAndPhoto(width : Float,height : Float) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            getQuoteAndPhotoUseCase(width,height).onEach { result ->
                Log.d("TAG", "getRandomQuoteAndPhoto: ${result.message} message ${result.data} data")
                when (result) {
                    is Resource.Success -> {
                        _randomPhotoAndQuoteState.value = randomPhotoAndQuoteState.value.copy(
                            data = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _randomPhotoAndQuoteState.value = randomPhotoAndQuoteState.value.copy(
                            data = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _errorEvent.emit(UIEvent.ShowSnackbar(result.message ?: "Unknown error"))
                    }

                    is Resource.Loading -> {
                        _randomPhotoAndQuoteState.value = randomPhotoAndQuoteState.value.copy(
                            data = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}