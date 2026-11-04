package dev.archfoundry.marsphotosapp.Data

import dev.archfoundry.marsphotosapp.Network.MarsApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

interface AppContainer {
    val marsPhotosRepository: MarsPhotoRepository
}
class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com"
    private val retrofit=Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    val retrofitService:MarsApiService by lazy {
        dev.archfoundry.marsphotosapp.Network.retrofit.create(MarsApiService::class.java)
    }

    override val marsPhotosRepository: MarsPhotoRepository by lazy{
        NetworkMarsPhotoRepository(retrofitService)
    }
    }

