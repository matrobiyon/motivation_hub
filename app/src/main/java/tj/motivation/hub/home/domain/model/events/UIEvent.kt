package tj.motivation.hub.home.domain.model.events

sealed class UIEvent() {
    data class ShowSnackbar(val message : String) : UIEvent()
}
