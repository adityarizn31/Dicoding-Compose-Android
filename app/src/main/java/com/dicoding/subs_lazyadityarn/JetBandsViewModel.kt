package com.dicoding.subs_lazyadityarn

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.subs_lazyadityarn.data.BandRepository
import com.dicoding.subs_lazyadityarn.model.Band
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JetBandsViewModel(private val repository: BandRepository) : ViewModel() {
    private val _groupedAgents = MutableStateFlow(
        repository.getBands()
            .sortedBy { it.id }
            .groupBy { it.name[0] }
    )
    val groupedAgents: StateFlow<Map<Char, List<Band>>> get() = _groupedAgents

}

class ViewModelFactory(private val repository: BandRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JetBandsViewModel::class.java)) {
            return JetBandsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}