package edu.upc.coursemanager.domain

import edu.upc.coursemanager.uia.result.ResultState
import kotlinx.serialization.json.buildJsonArray
import javax.inject.Inject

class QueryTableUseCase @Inject constructor() {

    suspend operator fun invoke(
        table: String,
        filtros: Map<String, String>
    ): ResultState {
        // placeholder con lista vacía
        val dummy = buildJsonArray { /* sin elementos */ }
        return ResultState.Ready(dummy)
    }
}
