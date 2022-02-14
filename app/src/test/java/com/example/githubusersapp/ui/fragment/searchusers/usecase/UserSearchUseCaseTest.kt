package com.example.githubusersapp.ui.fragment.searchusers.usecase

import com.example.githubusersapp.MockTestUtil
import com.example.githubusersapp.base.repository.GeneralRepository
import com.example.githubusersapp.base.repository.ResultCallBack
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author AliAzazAlam on 2/14/2022.
 */
@RunWith(JUnit4::class)
class UserSearchUseCaseTest {

    @MockK
    private lateinit var repository: GeneralRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test getSearchUsers gives list of users`() = runBlocking {
        // Given
        val usecase = UserSearchUseCase(repository)
        val mockUsers = MockTestUtil.createUsers()

        // When
        coEvery { repository.getSearchUsers(1,"alimate") }
            .returns(
                ResultCallBack.Success(flowOf(mockUsers))
            )

        // Invoke
        val userListFlow = usecase(1,"alimate")

        // Then
        MatcherAssert.assertThat(userListFlow, CoreMatchers.notNullValue())

        val result = (userListFlow as ResultCallBack.Success).data
        val userListDataState = result.first()
        MatcherAssert.assertThat(userListDataState, CoreMatchers.notNullValue())

        MatcherAssert.assertThat(userListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(userListDataState.usersInfo.size, CoreMatchers.`is`(mockUsers.usersInfo.size))
    }
}