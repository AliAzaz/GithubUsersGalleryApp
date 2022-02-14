package com.example.githubusersapp.ui.fragment.searchusers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.githubusersapp.MainCoroutinesRule
import com.example.githubusersapp.MockTestUtil
import com.example.githubusersapp.base.repository.ResponseStatusCallbacks
import com.example.githubusersapp.base.repository.ResultCallBack
import com.example.githubusersapp.model.FetchDataModel
import com.example.githubusersapp.model.UsersResult
import com.example.githubusersapp.ui.fragment.searchusers.usecase.UserSearchUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author AliAzazAlam on 2/14/2022.
 */
@RunWith(JUnit4::class)
class UsersViewModelTest {

    // Subject under test
    private lateinit var viewModel: UsersViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var userListUseCase: UserSearchUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test getAllUsersFromRemoteServer() returns list of users`() = runBlocking {
        // Given
        val usersListObserver =
            mockk<Observer<ResponseStatusCallbacks<FetchDataModel>>>(relaxed = true)

        // When
        coEvery {
            val items = arrayListOf<UsersResult>()
            userListUseCase.invoke(any(), any()).let { item ->
                if (item is ResultCallBack.Success) {
                    item.data.collect {
                        items.add(it)
                    }
                }
            }
            items.toList()
        }
            .returns(listOf(MockTestUtil.createUsers()))

        // Invoke
        viewModel = UsersViewModel(userListUseCase)
        viewModel.usersResponse.observeForever(usersListObserver)

        // Then
        coVerify(exactly = 1) {
            userListUseCase.invoke(any(),any())
        }

    }

    @After
    fun tearDown() {
    }
}