package dev.archfoundry.marsphotosapp.Network

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET

private const val BASE_URL ="https://android-kotlin-fun-mars-server.appspot.com"
val retrofit=Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService{
    @GET(value="photos")
    suspend fun getPhotos():List<MarsPhoto>
}



