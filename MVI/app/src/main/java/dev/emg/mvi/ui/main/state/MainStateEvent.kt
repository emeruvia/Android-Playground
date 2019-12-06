package dev.emg.mvi.ui.main.state

sealed class MainStateEvent {

    class GetBlogPostEvent: MainStateEvent()

    class GetUserEvent(val userId: String): MainStateEvent()

    class None: MainStateEvent()

}