package com.example.githubusersapp.di.modules

import com.example.githubusersapp.ui.fragment.userdetail.UserDetailFragment
import com.example.githubusersapp.ui.fragment.searchusers.UsersListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Ali Azaz
 */
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindProductListFragment(): UsersListFragment

    @ContributesAndroidInjector
    abstract fun bindProductDetailFragment(): UserDetailFragment
}