package com.example.githubusersapp.ui.fragment.searchusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusersapp.base.repository.ResponseStatusCallbacks
import com.example.githubusersapp.base.repository.ResultCallBack
import com.example.githubusersapp.ui.fragment.searchusers.usecase.UserSearchUseCase
import com.example.githubusersapp.model.FetchDataModel
import com.example.githubusersapp.model.UsersInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author AliAzazAlam on 2/13/2022.
 */
@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userSearchUseCase: UserSearchUseCase
) : ViewModel() {

    private val _usersList = MutableLiveData<ResponseStatusCallbacks<FetchDataModel>>()
    val usersResponse: LiveData<ResponseStatusCallbacks<FetchDataModel>>
        get() = _usersList

    private var pagination = 1
    private var searchUser: String? = null
    private var updatedItems = arrayListOf<UsersInfo>()

    /*
    * load next page
    * */
    fun loadNextPageUsers() {
        searchUser?.let { fetchSearchUsersFromRemoteServer(pagination, it) }
    }

    /*
    * Retry connection if internet is not available
    * */
    fun retryConnection() {
        searchUser?.let { fetchSearchUsersFromRemoteServer(pagination, it) }
    }

    /*
    * Search function for searching users by name
    * */
    fun searchUserFromRemote(search: String) {
        pagination = 1
        searchUser = search
        fetchSearchUsersFromRemoteServer(pagination, search)
    }

    /*
    * Query to fetch users from server
    * */
    private fun fetchSearchUsersFromRemoteServer(page: Int, search: String) {
        _usersList.value = ResponseStatusCallbacks.loading(
            data = FetchDataModel(
                page = page,
                usersInfo = null
            )
        )
        viewModelScope.launch {
            try {
                userSearchUseCase(page = page, category = search).let { user ->
                    when (user) {
                        is ResultCallBack.CallException -> {
                            _usersList.postValue(
                                ResponseStatusCallbacks.error(
                                    null,
                                    message = user.exception.toString()
                                )
                            )
                        }
                        is ResultCallBack.Error -> {
                            _usersList.postValue(
                                ResponseStatusCallbacks.error(
                                    null,
                                    message = user.error
                                )
                            )
                        }
                        is ResultCallBack.Success -> {
                            user.data.collect { dataset ->
                                dataset.usersInfo.let {
                                    if (it.isNotEmpty()) {
                                        //pagination increased for next page
                                        pagination++
                                        if (page == 1) {
                                            updatedItems = arrayListOf()
                                            updatedItems.addAll(it)
                                        } else {
                                            updatedItems.addAll(it)
                                        }
                                        _usersList.postValue(
                                            ResponseStatusCallbacks.success(
                                                data = FetchDataModel(
                                                    page = page,
                                                    usersInfo = updatedItems
                                                ),
                                                "Users received"
                                            )
                                        )
                                    } else
                                        _usersList.value = ResponseStatusCallbacks.error(
                                            data = FetchDataModel(
                                                page = page,
                                                usersInfo = null
                                            ),
                                            if (page == 1) "Sorry no Users received" else "Sorry no more Users available"
                                        )
                                }
                            }
                        }
                    }

                }
            } catch (e: Exception) {
                _usersList.value = ResponseStatusCallbacks.error(null, e.message.toString())
            }
        }
    }

    /*
    * Search query
    * */
    fun getSearchQuery() = searchUser

    /*
    * Clear fields
    * */
    fun clearFields(){
        pagination = 1
        searchUser = null
        updatedItems = arrayListOf()
    }
}