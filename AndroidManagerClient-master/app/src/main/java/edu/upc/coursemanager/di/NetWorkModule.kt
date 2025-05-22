package edu.upc.coursemanager.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.upc.coursemanager.data.remote.CourseApi
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import okhttp3.MediaType.Companion.toMediaType
import kotlin.time.Duration.Companion.seconds
import java.time.Duration      // ← nuevo import



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://example.com/"   // ← cámbialo luego

    @Provides @Singleton
    fun okHttp(): OkHttpClient =
                OkHttpClient.Builder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .readTimeout(Duration.ofSeconds(15))
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()


    @Provides @Singleton
    fun retrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                Json { ignoreUnknownKeys = true }
                    .asConverterFactory("application/json".toMediaType())
            )
            .client(client)
            .build()

    @Provides @Singleton
    fun api(retrofit: Retrofit): CourseApi =
        retrofit.create(CourseApi::class.java)
}
