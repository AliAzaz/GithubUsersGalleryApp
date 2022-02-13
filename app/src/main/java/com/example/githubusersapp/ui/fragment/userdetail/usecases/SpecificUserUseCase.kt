package com.example.githubusersapp.ui.fragment.userdetail.usecases

import com.example.githubusersapp.base.repository.GeneralDataSource
import javax.inject.Inject

/**
 * @author AliAzazAlam on 2/13/2022.
 */
class SpecificUserUseCase @Inject constructor(private val repository: GeneralDataSource) {
    suspend operator fun invoke(
        user: String
    ) = repository.getSpecificUser(
        user = user
    )
}