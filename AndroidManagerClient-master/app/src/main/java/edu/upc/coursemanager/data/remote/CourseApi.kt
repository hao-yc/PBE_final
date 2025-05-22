package edu.upc.coursemanager.data.remote

import kotlinx.serialization.json.JsonArray
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface CourseApi {
    @GET("{tabla}")                       // ← solo GET genérico
    suspend fun queryTable(
        @Path("tabla") tabla: String,
        @QueryMap filtros: Map<String, String>
    ): JsonArray
}
