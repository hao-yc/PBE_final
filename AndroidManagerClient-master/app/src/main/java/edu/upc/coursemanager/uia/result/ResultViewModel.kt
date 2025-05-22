package edu.upc.coursemanager.uia.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import edu.upc.coursemanager.domain.QueryTableUseCase

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val queryUseCase: QueryTableUseCase   // definido en domain
) : ViewModel() {

    var state: ResultState = ResultState.Loading
        private set

    fun load(table: String, filtros: Map<String, String>) = viewModelScope.launch {
        state = ResultState.Loading
        state = queryUseCase(table, filtros)
    }
}
