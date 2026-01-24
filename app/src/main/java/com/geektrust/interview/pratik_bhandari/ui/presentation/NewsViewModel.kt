package com.geektrust.interview.pratik_bhandari.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geektrust.interview.pratik_bhandari.domain.usecases.GetTopNewsUseCase
import com.geektrust.interview.pratik_bhandari.domain.usecases.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase,
    private val getTopNewsUseCase: GetTopNewsUseCase
) : ViewModel() {

    private val _newsState = MutableStateFlow<NewsScreenState>(NewsScreenState.Loading)
    val newsState: StateFlow<NewsScreenState> = _newsState.asStateFlow()

    fun searchNews(
        query: String,
        page: Int = 1,
        maxResults: Int = 10,
        language: String? = null,
        country: String? = null
    ) {
        viewModelScope.launch {
            searchNewsUseCase(
                query = query,
                page = page,
                maxResults = maxResults,
                language = language,
                country = country
            ).let {
                _newsState.value = it
            }
        }
    }

    fun getTopHeadlines(
        page: Int = 1,
        maxResults: Int = 10,
        category: String? = null,
        language: String? = null,
        country: String? = null
    ) {
        viewModelScope.launch {
            getTopNewsUseCase(
                page = page,
                maxResults = maxResults,
                category = category,
                language = language,
                country = country
            ).let {
                _newsState.value = it
            }
        }
    }
}
