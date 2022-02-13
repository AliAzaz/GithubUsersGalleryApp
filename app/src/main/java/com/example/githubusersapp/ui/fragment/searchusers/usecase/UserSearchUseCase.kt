package com.example.githubusersapp.ui.fragment.searchusers.usecase

import com.example.githubusersapp.base.repository.GeneralDataSource
import javax.inject.Inject

/**
 * @author AliAzazAlam on 2/13/2022.
 */
class UserSearchUseCase @Inject constructor(private val repository: GeneralDataSource) {
    suspend operator fun invoke(
        page: Int = 1,
        category: String
    ) = repository.getSearchUsers(
        page = page,
        search = category
    )
}