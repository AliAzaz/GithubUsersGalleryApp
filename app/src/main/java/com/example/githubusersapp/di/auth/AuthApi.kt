package com.example.githubusersapp.di.auth

import com.example.githubusersapp.di.auth.remote.ApiResponse
import com.example.githubusersapp.model.UserData
import com.example.githubusersapp.model.UsersResult
import com.example.githubusersapp.model.repo.UserRepoResult
import com.example.githubusersapp.utils.CONSTANTS
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author AliAzazAlam on 2/13/2022.
 */
interface AuthApi {

    @GET(ApiRoutes.GET_SPECIFIC_USERS_DATA)
    suspend fun getSpecificUserData(
        @Path(CONSTANTS.USER) user: String
    ): ApiResponse<UserData>

    @GET(ApiRoutes.GET_SEARCH_USERS_DATA)
    suspend fun getSearchUsersData(
        @Query(CONSTANTS.SEARCH) search: String,
        @Query(CONSTANTS.PAGE) page: Int = 1
    ): ApiResponse<UsersResult>

    @GET(ApiRoutes.GET_SPECIFIC_USERS_REPOS)
    suspend fun getSpecificUserReposData(
        @Path(CONSTANTS.USER) user: String,
        @Query(CONSTANTS.PAGE) page: Int = 1
    ): ApiResponse<UserRepoResult>

}