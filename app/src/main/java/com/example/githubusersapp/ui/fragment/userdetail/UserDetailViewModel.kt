package com.example.githubusersapp.ui.fragment.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusersapp.base.repository.ResponseStatusCallbacks
import com.example.githubusersapp.base.repository.ResultCallBack
import com.example.githubusersapp.ui.fragment.userdetail.usecases.SpecificSpecificUserReposUseCase
import com.example.githubusersapp.ui.fragment.userdetail.usecases.SpecificUserUseCase
import com.example.githubusersapp.model.FetchDataModel
import com.example.githubusersapp.model.UserData
import com.example.githubusersapp.model.repo.UserRepoResultItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject

/**
 * @author AliAzazAlam on 2/13/2022.
 */
@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val specificUserUseCase: SpecificUserUseCase,
    private val specificUserReposUseCase: SpecificSpecificUserReposUseCase
) : ViewModel() {

    private val _userReposList = MutableLiveData<ResponseStatusCallbacks<FetchDataModel>>()
    val userReposResponse: LiveData<ResponseStatusCallbacks<FetchDataModel>>
        get() = _userReposList

    private val _selectedUser = MutableLiveData<ResponseStatusCallbacks<UserData>>()
    val selectedUserResponse: LiveData<ResponseStatusCallbacks<UserData>>
        get() = _selectedUser

    private var pagination = 1
    private var selectedUser = StringUtils.EMPTY
    private var updatedItems = arrayListOf<UserRepoResultItem>()


    /* Observed function for initiate searching */
    private fun fetchReposFromRemoteServer(pagination: Int, user: String) {
        _userReposList.value = ResponseStatusCallbacks.loading(
            data = FetchDataModel(
                page = pagination,
                usersInfo = null
            )
        )
        viewModelScope.launch {
            try {
                specificUserReposUseCase(page = pagination, user = user).let { user ->
                    when (user) {
                        is ResultCallBack.CallException -> {
                            _userReposList.postValue(
                                ResponseStatusCallbacks.error(
                                    null,
                                    message = user.exception.toString()
                                )
                            )
                        }
                        is ResultCallBack.Error -> {
                            _userReposList.postValue(
                                ResponseStatusCallbacks.error(
                                    null,
                                    message = user.error
                                )
                            )
                        }
                        is ResultCallBack.Success -> {
                            user.data.collect { dataset ->
                                dataset.let {
                                    if (it.isNotEmpty()) {
                                        if (pagination == 1) {
                                            updatedItems = arrayListOf()
                                            updatedItems.addAll(it)
                                        } else {
                                            updatedItems.addAll(it)
                                        }
                                        _userReposList.postValue(
                                            ResponseStatusCallbacks.success(
                                                data = FetchDataModel(
                                                    page = pagination,
                                                    usersInfo = updatedItems
                                                ),
                                                "Repo received"
                                            )
                                        )
                                    } else
                                        _userReposList.value = ResponseStatusCallbacks.error(
                                            data = FetchDataModel(
                                                page = pagination,
                                                usersInfo = null
                                            ),
                                            if (pagination == 1) "Sorry no Repo received" else "Sorry no more Repos available"
                                        )
                                }
                            }
                        }
                    }

                }
            } catch (e: Exception) {
                _userReposList.value = ResponseStatusCallbacks.error(null, e.message.toString())
            }
        }
    }

    /*Send data to detail page*/
    fun getUserData(user: String) {
        _selectedUser.value = ResponseStatusCallbacks.loading(null)
        viewModelScope.launch {
            try {
                selectedUser = user
                specificUserUseCase(user).let {
                    when (it) {
                        is ResultCallBack.CallException -> {
                        }
                        is ResultCallBack.Error -> {
                            _selectedUser.value = ResponseStatusCallbacks.error(
                                data = null,
                                message = it.error
                            )
                        }
                        is ResultCallBack.Success -> {
                            _selectedUser.value = ResponseStatusCallbacks.success(
                                data = it.data
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _selectedUser.value = ResponseStatusCallbacks.error(null, e.message.toString())
            }
        }
    }

    fun getUserRepos(user: String) {
        fetchReposFromRemoteServer(pagination, user)
    }


    /*load next page*/
    fun loadNextPageRepos() {
        pagination++
        fetchReposFromRemoteServer(pagination, selectedUser)
    }

    /*Retry connection if internet is not available*/
    fun retryConnection() {
        fetchReposFromRemoteServer(pagination, selectedUser)
    }

}