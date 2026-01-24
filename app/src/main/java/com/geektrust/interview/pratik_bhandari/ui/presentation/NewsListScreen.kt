package com.geektrust.interview.pratik_bhandari.ui.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

data object NewsListScreenRoute

@Composable
fun NewsListScreen() {

    val viewModel = hiltViewModel<NewsViewModel>()
    val state = viewModel.newsState.collectAsStateWithLifecycle()

    when(state) {

    }
}