package dev.archfoundry.marsphotosapp.Data

import dev.archfoundry.marsphotosapp.Network.MarsApiService
import dev.archfoundry.marsphotosapp.Network.MarsPhoto

interface MarsPhotoRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}
class NetworkMarsPhotoRepository(private val marsApiService: MarsApiService): MarsPhotoRepository {

    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return marsApiService.getPhotos()
    }

}