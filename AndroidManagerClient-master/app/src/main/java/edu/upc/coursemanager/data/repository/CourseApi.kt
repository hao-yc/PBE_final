package edu.upc.coursemanager.data.repository

import edu.upc.coursemanager.data.remote.CourseApi
import kotlinx.serialization.json.JsonArray
import javax.inject.Inject

class CourseRepository @Inject constructor(
    private val api: CourseApi
) {
    suspend fun query(tabla: String, filtros: Map<String, String>): JsonArray =
        api.queryTable(tabla, filtros)
}
