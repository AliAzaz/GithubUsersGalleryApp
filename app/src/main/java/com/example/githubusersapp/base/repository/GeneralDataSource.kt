package com.example.githubusersapp.base.repository

import com.example.githubusersapp.model.UserData
import com.example.githubusersapp.model.UsersResult
import com.example.githubusersapp.model.repo.UserRepoResult
import kotlinx.coroutines.flow.Flow

/**
 * @author AliAzazAlam on 2/13/2022.
 */
interface GeneralDataSource {

    /*
    * Get specific user data from server
    * */
    suspend fun getSpecificUser(
        user: String
    ): ResultCallBack<UserData>

    suspend fun getSpecificUserRepos(
        page: Int,
        user: String
    ): ResultCallBack<Flow<UserRepoResult>>
    /*
    * Get specific user data from server End
    * */

    /*
    * Get search users
    * */
    suspend fun getSearchUsers(
        page: Int,
        search: String
    ): ResultCallBack<Flow<UsersResult>>
    /*
    * Get search users End
    * */


}