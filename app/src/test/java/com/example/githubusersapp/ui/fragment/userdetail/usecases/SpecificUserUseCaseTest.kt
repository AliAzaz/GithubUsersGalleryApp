package com.example.githubusersapp.ui.fragment.userdetail.usecases

import com.example.githubusersapp.MockTestUtil
import com.example.githubusersapp.base.repository.GeneralRepository
import com.example.githubusersapp.base.repository.ResultCallBack
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author AliAzazAlam on 2/14/2022.
 */
@RunWith(JUnit4::class)
class SpecificUserUseCaseTest {

    @MockK
    private lateinit var repository: GeneralRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test getSingleUser gives user`() = runBlocking {
        // Given
        val usecase = SpecificUserUseCase(repository)
        val mockUsers = MockTestUtil.createSingleUser()

        // When
        coEvery { repository.getSpecificUser("alimohamadi17") }
            .returns(
                ResultCallBack.Success(mockUsers)
            )

        // Invoke
        val singleUser = usecase("alimohamadi17")

        // Then
        MatcherAssert.assertThat(singleUser, CoreMatchers.notNullValue())

        val result = (singleUser as ResultCallBack.Success).data
        MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(result.id, CoreMatchers.`is`(72912588))
    }
}