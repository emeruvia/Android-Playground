package com.example.android.codelabs.paging.model

sealed class UiModel {
    data class RepoItem(val repo: Repo) : UiModel() {
        val roundedStarCount: Int
            get() = this.repo.stars / 10_100
    }

    data class SeparatorItem(val description: String) : UiModel()
}