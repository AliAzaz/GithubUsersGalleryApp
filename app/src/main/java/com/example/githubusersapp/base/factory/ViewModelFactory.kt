package com.example.githubusersapp.base.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubusersapp.base.repository.GeneralRepository
import com.example.githubusersapp.ui.fragment.searchusers.UsersViewModel
import com.example.githubusersapp.ui.fragment.searchusers.usecase.UserSearchUseCase
import com.example.githubusersapp.ui.fragment.userdetail.usecases.SpecificUserUseCase
import com.example.githubusersapp.ui.fragment.userdetail.UserDetailViewModel
import com.example.githubusersapp.ui.fragment.userdetail.usecases.SpecificSpecificUserReposUseCase
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author AliAzazAlam on 2/13/2022.
 */
@Singleton
@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val repository: GeneralRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UsersViewModel::class.java) -> UsersViewModel(
                UserSearchUseCase(repository)
            ) as T
            modelClass.isAssignableFrom(UserDetailViewModel::class.java) -> UserDetailViewModel(
                SpecificUserUseCase(repository),
                SpecificSpecificUserReposUseCase(repository)
            ) as T
            else -> throw IllegalArgumentException("Unknown viewModel class $modelClass")
        }
    }

}