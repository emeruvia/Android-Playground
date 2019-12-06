package dev.emg.mvi.ui.main.state

import dev.emg.mvi.model.BlogPost
import dev.emg.mvi.model.User

data class MainViewState (
    var blogPost: List<BlogPost>? = null,
    var user: User? = null
)