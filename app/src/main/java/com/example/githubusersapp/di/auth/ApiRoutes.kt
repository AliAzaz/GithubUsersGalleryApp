package com.example.githubusersapp.di.auth

import com.example.githubusersapp.utils.CONSTANTS

/**
 * @author AliAzazAlam on 2/13/2022.
 */
object ApiRoutes {
    const val GET_SPECIFIC_USERS_DATA = "users/{${CONSTANTS.USER}}"
    const val GET_SPECIFIC_USERS_REPOS = "users/{${CONSTANTS.USER}}/repos?per_page=20"
    const val GET_SEARCH_USERS_DATA = "search/users?per_page=30"
}