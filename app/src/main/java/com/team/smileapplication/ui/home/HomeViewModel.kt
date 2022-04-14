package com.team.smileapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

import com.team.smileapplication.common.AbsentLiveData
import com.team.smileapplication.data.api.BaseResponse
import com.team.smileapplication.data.api.Resource
import com.team.smileapplication.data.model.Result
import com.team.smileapplication.data.model.User
import com.team.smileapplication.repo.UMSRepository
import java.util.ArrayList
import javax.inject.Inject

class HomeViewModel
@Inject constructor(repoRepository: UMSRepository) : ViewModel() {




    /*** For Remainder***/
    private val getInfo = MutableLiveData<HashMap<String, String>>()

    fun getInformation(data: HashMap<String, String>?) {
        getInfo.value = data
    }

    val getInfoRepo: LiveData<Resource<List<Result>>> =
            Transformations.switchMap(getInfo) { data ->
                if (data == null) {
                    AbsentLiveData.create()
                } else {
                    repoRepository.getInfo()
                }
            }
    /**********/

}