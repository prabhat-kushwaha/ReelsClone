package com.prabhatkushwaha.task.framework.ui.reels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prabhatkushwaha.task.business.domain.models.ReelsModel
import com.prabhatkushwaha.task.business.domain.util.ApiResult
import com.prabhatkushwaha.task.business.interactors.reels.GetReelsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ReelsViewModel @Inject constructor(private val reelsInteractor: GetReelsInteractor) :
    ViewModel() {


    private var _reelsData = MutableLiveData<ApiResult<List<ReelsModel>?>>()
    var reelsData: LiveData<ApiResult<List<ReelsModel>?>> = _reelsData
    fun getReelsData() = viewModelScope.launch {
        _reelsData.value = ApiResult.Loading()
        reelsInteractor.getReelsList(userId = "ID1549716343", page = "1", category = "hindi")
            .collect {
                _reelsData.value = it
            }
    }


}