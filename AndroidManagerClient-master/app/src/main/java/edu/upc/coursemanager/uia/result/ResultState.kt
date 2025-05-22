package edu.upc.coursemanager.uia.result

import kotlinx.serialization.json.JsonArray

sealed interface ResultState {
    data object Loading : ResultState
    data class Ready(val data: JsonArray) : ResultState
    data class Error(val msg: String) : ResultState
}
