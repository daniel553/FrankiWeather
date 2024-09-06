package com.frankiapp.domain.weather

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ProvideCitySuggestionsUseCase @Inject constructor() {
    private val originalSuggestion = listOf(
        "Los Angeles",
        "San Diego",
        "Dallas",
        "New York",
    )

    operator fun invoke(search: String): List<String> {
        return if (search.isBlank()) {
            originalSuggestion
        } else {
            originalSuggestion.filter { it.contains(search) }
        }
    }
}