package com.example.githubusersapp.di.auth

import com.example.githubusersapp.MainCoroutinesRule
import com.example.githubusersapp.di.auth.remote.ApiResponse
import com.example.githubusersapp.di.modules.NetworkApiModuleTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

/**
 * @author AliAzazAlam on 2/14/2022.
 */
class AuthApiTest : NetworkApiModuleTest<AuthApi>() {

    private lateinit var apiService: AuthApi

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = MainCoroutinesRule()

    @Before
    fun setUp() {
        apiService = createService(AuthApi::class.java)
    }

    @After
    fun tearDown() {
    }

    @Throws(IOException::class)
    @Test
    fun `test getUsersData() returns single User`() = runBlocking {
        // Given
        enqueueResponse("/user_detail_response.json")

        // Invoke
        val response = apiService.getSpecificUserData("alimohamadi17")
        val resultSuccess = if (response is ApiResponse.ApiSuccessResponse) response.data else null
        val responseBody = requireNotNull(resultSuccess)
        mockWebServer.takeRequest()

        // Then
        MatcherAssert.assertThat(responseBody.id, CoreMatchers.`is`(72912588))
        MatcherAssert.assertThat(responseBody.login, CoreMatchers.`is`("alimohamadi17"))
        MatcherAssert.assertThat(responseBody.url, CoreMatchers.`is`("https://api.github.com/users/alimohamadi17"))
        MatcherAssert.assertThat(responseBody.avatarUrl,
            CoreMatchers.`is`("https://avatars.githubusercontent.com/u/72912588?v=4"))
        MatcherAssert.assertThat(responseBody.type, CoreMatchers.`is`("User"))
        MatcherAssert.assertThat(responseBody.nodeId, CoreMatchers.`is`("MDQ6VXNlcjcyOTEyNTg4"))
        MatcherAssert.assertThat(
            responseBody.reposUrl,
            CoreMatchers.`is`("https://api.github.com/users/alimohamadi17/repos")
        )
    }

    @Throws(IOException::class)
    @Test
    fun `test getUsersList() returns list of Users `() = runBlocking {
        // Given
        enqueueResponse("/users_response.json")

        // Invoke
        val response = apiService.getSearchUsersData("Ali")
        val resultSuccess = if (response is ApiResponse.ApiSuccessResponse) response.data else null
        val responseBody = requireNotNull(resultSuccess)
        mockWebServer.takeRequest()

        // Then
        MatcherAssert.assertThat(responseBody.usersInfo[0].id, CoreMatchers.`is`(72912588))
        MatcherAssert.assertThat(responseBody.usersInfo[0].login, CoreMatchers.`is`("alimohamadi17"))
        MatcherAssert.assertThat(responseBody.usersInfo[0].url, CoreMatchers.`is`("https://api.github.com/users/alimohamadi17"))
        MatcherAssert.assertThat(responseBody.usersInfo[0].avatarUrl,
            CoreMatchers.`is`("https://avatars.githubusercontent.com/u/72912588?v=4"))
        MatcherAssert.assertThat(responseBody.usersInfo[0].type, CoreMatchers.`is`("User"))
        MatcherAssert.assertThat(responseBody.usersInfo[0].nodeId, CoreMatchers.`is`("MDQ6VXNlcjcyOTEyNTg4"))
        MatcherAssert.assertThat(
            responseBody.usersInfo[0].reposUrl,
            CoreMatchers.`is`("https://api.github.com/users/alimohamadi17/repos")
        )
    }

    @Throws(IOException::class)
    @Test
    fun `test getUserReposList() returns list of Repos `() = runBlocking {
        // Given
        enqueueResponse("/user_repos_response.json")

        // Invoke
        val response = apiService.getSpecificUserReposData("alimohamadi17")
        val resultSuccess = if (response is ApiResponse.ApiSuccessResponse) response.data else null
        val responseBody = requireNotNull(resultSuccess)
        mockWebServer.takeRequest()

        // Then
        MatcherAssert.assertThat(responseBody[1].id, CoreMatchers.`is`(426964456))
        MatcherAssert.assertThat(responseBody[1].name, CoreMatchers.`is`("btn-hover"))
        MatcherAssert.assertThat(responseBody[1].htmlUrl, CoreMatchers.`is`("https://github.com/alimohamadi17/btn-hover"))
        MatcherAssert.assertThat(responseBody[1].forksCount, CoreMatchers.`is`(0))
        MatcherAssert.assertThat(responseBody[1].stargazersCount, CoreMatchers.`is`(0))
    }

}