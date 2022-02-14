package com.example.githubusersapp.base.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubusersapp.MainCoroutinesRule
import com.example.githubusersapp.MockTestUtil
import com.example.githubusersapp.di.auth.AuthApi
import com.example.githubusersapp.di.auth.remote.ApiResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

/**
 * @author AliAzazAlam on 2/14/2021.
 */
@RunWith(JUnit4::class)
class GeneralRepositoryTest {

    // Subject under test
    private lateinit var repository: GeneralRepository

    @MockK
    private lateinit var authApi: AuthApi

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test getspecificuser() returns single user`() = runBlocking {
        // Given
        repository = GeneralRepository(authApi)
        val searchUser = MockTestUtil.createSingleUser()

        // When
        coEvery { authApi.getSpecificUserData(any()) }.returns(ApiResponse.create(response = Response.success(searchUser)))

        // Invoke
        val usersFlow = repository.getSpecificUser("alimohamadi17")

        // Then
        MatcherAssert.assertThat(usersFlow, CoreMatchers.notNullValue())

        val result = (usersFlow as ResultCallBack.Success).data
        MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(result.login, CoreMatchers.`is`("alimohamadi17"))
    }

    @Test
    fun `test searchUser returns list of users `() = runBlocking {
        //Given
        repository = GeneralRepository(authApi)
        val searchUser = MockTestUtil.createUsers()

        //When
        coEvery {
            authApi.getSearchUsersData(any(), any())
        }.returns(ApiResponse.create(response = Response.success(searchUser)))

        //Invoke
        val usersFlow = repository.getSearchUsers(1, "Ali")

        //Then
        MatcherAssert.assertThat(usersFlow, CoreMatchers.notNullValue())

        val result = (usersFlow as ResultCallBack.Success).data
        val users = result.first()
        MatcherAssert.assertThat(users, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(users.usersInfo, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            users.usersInfo.size,
            CoreMatchers.`is`(searchUser.usersInfo.size)
        )

    }

    @Test
    fun `test searchUserRepos returns list of users repos`() = runBlocking {
        //Given
        repository = GeneralRepository(authApi)
        val searchUser = MockTestUtil.createRepos()

        //When
        coEvery {
            authApi.getSpecificUserReposData(any(), any())
        }.returns(ApiResponse.create(response = Response.success(searchUser)))

        //Invoke
        val usersFlow = repository.getSpecificUserRepos(1, "Ali")

        //Then
        MatcherAssert.assertThat(usersFlow, CoreMatchers.notNullValue())

        val result = (usersFlow as ResultCallBack.Success).data
        val users = result.first()
        MatcherAssert.assertThat(users, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(users, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            users.size,
            CoreMatchers.`is`(searchUser.size)
        )

    }

    @After
    fun tearDown() {

    }
}