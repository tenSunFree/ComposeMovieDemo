package com.example.composemoviedemo.discount.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.composemoviedemo.common.util.Utils
import com.example.composemoviedemo.discount.model.DiscountRepository
import com.example.composemoviedemo.discount.model.DiscountResponse

class DiscountViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val remoteMovieData =
        DiscountRepository.getInstance()
    private var _response = MutableLiveData<DiscountResponse>()
    val response: LiveData<DiscountResponse> = _response

    suspend fun requestPhotos() {
        if (!Utils.ensureNetworkAvailable(getApplication())) return
        val photos = remoteMovieData?.requestPhotos()
        val photosFilter: List<DiscountResponse.Item> = photos?.filter {
            it.id < 100
        }!!
        val response = DiscountResponse()
        response.addAll(photosFilter)
        _response.value = response
    }
}