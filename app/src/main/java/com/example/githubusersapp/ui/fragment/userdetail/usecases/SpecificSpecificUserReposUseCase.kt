package com.example.githubusersapp.ui.fragment.userdetail.usecases

import com.example.githubusersapp.base.repository.GeneralDataSource
import javax.inject.Inject

/**
 * @author AliAzazAlam on 2/13/2022.
 */
class SpecificSpecificUserReposUseCase @Inject constructor(private val repository: GeneralDataSource) {
    suspend operator fun invoke(
        page: Int,
        user: String
    ) = repository.getSpecificUserRepos(
        page = page,
        user = user
    )
}