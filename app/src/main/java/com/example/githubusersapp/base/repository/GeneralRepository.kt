package com.example.githubusersapp.base.repository

import com.example.githubusersapp.di.auth.AuthApi
import com.example.githubusersapp.di.auth.remote.ApiResponse
import com.example.githubusersapp.di.auth.remote.message
import com.example.githubusersapp.model.UserData
import com.example.githubusersapp.model.UsersResult
import com.example.githubusersapp.model.repo.UserRepoResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author AliAzazAlam on 2/13/2022.
 */
class GeneralRepository @Inject constructor(private val apiService: AuthApi) : GeneralDataSource {

    override suspend fun getSpecificUser(
        user: String
    ): ResultCallBack<UserData> {
        var result: ResultCallBack<UserData>
        apiService.getSpecificUserData(user = user).apply {
            result = when (this) {
                is ApiResponse.ApiSuccessResponse -> {
                    data?.let {
                        ResultCallBack.Success(it)
                    } ?: ResultCallBack.Error("Null record")
                }
                is ApiResponse.ApiFailureResponse.Error -> {
                    ResultCallBack.Error(message())
                }
                is ApiResponse.ApiFailureResponse.Exception -> {
                    ResultCallBack.CallException(exception = this.exception as Exception)
                }
            }
        }
        return result
    }

    override suspend fun getSpecificUserRepos(
        page: Int,
        user: String
    ): ResultCallBack<Flow<UserRepoResult>> {
        var result: ResultCallBack<Flow<UserRepoResult>>
        apiService.getSpecificUserReposData(
            page = page,
            user = user
        ).apply {
            result = when (this) {
                is ApiResponse.ApiSuccessResponse -> {
                    data?.let {
                        ResultCallBack.Success(
                            flow { emit(it) }
                        )
                    } ?: ResultCallBack.Error("Null record")
                }
                is ApiResponse.ApiFailureResponse.Error -> {
                    ResultCallBack.Error(message())
                }
                is ApiResponse.ApiFailureResponse.Exception -> {
                    ResultCallBack.CallException(exception = this.exception as Exception)
                }
            }
        }
        return result
    }

    override suspend fun getSearchUsers(
        page: Int,
        search: String
    ): ResultCallBack<Flow<UsersResult>> {
        var result: ResultCallBack<Flow<UsersResult>>
        apiService.getSearchUsersData(
            page = page,
            search = search
        ).apply {
            result = when (this) {
                is ApiResponse.ApiSuccessResponse -> {
                    data?.let {
                        ResultCallBack.Success(
                            flow { emit(it) }
                        )
                    } ?: ResultCallBack.Error("Null record")
                }
                is ApiResponse.ApiFailureResponse.Error -> {
                    ResultCallBack.Error(message())
                }
                is ApiResponse.ApiFailureResponse.Exception -> {
                    ResultCallBack.CallException(exception = this.exception as Exception)
                }
            }
        }
        return result
    }
}