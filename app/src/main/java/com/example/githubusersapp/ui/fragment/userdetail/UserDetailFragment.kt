package com.example.githubusersapp.ui.fragment.userdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusersapp.R
import com.example.githubusersapp.adapters.GenericListAdapter
import com.example.githubusersapp.base.FragmentBase
import com.example.githubusersapp.base.repository.ResponseStatus
import com.example.githubusersapp.databinding.FragmentUserDetailBinding
import com.example.githubusersapp.utils.*
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import com.example.githubusersapp.model.repo.UserRepoResultItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : FragmentBase() {

    private val viewModel by viewModels<UserDetailViewModel>()
    private lateinit var bi: FragmentUserDetailBinding
    private val userLoginName: String by lazy {
        arguments?.getString(CONSTANTS.INTENT_EXTRAS.USER_DATA) as String
    }
    private lateinit var adapter: GenericListAdapter<UserRepoResultItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*
        * Initializing databinding
        * */
        return FragmentUserDetailBinding.inflate(inflater, container, false).apply {
            bi = this
            viewModel.getUserData(user = userLoginName)
            viewModel.getUserRepos(user = userLoginName)
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        * Initiating recyclerview
        * */
        callingRecyclerView()

        /*
        * Checking scrollview scroll end
        * */
        bi.repoList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadNextPageRepos()
                }
            }
        })

        /*
        * Fetch product list
        * */
        viewModel.selectedUserResponse.observe(viewLifecycleOwner, {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    it.data?.let { item ->
                        /*Passing data to view*/
                        bi.setVariable(BR.selectedUser, item)
                    }
                }
                ResponseStatus.ERROR -> {
                }
                ResponseStatus.LOADING -> {
                }
            }
        })


        /*
        * Fetch Repo list
        * */
        viewModel.userReposResponse.observe(viewLifecycleOwner, {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    it.data?.apply {
                        adapter.productItems = usersInfo as ArrayList<UserRepoResultItem>
                        bi.layoutShimmerLoading.gone()
                        bi.repoList.visible()
                    }
                }
                ResponseStatus.ERROR -> {
                    it.data?.let { item ->
                        if (item.page == 1) {
                            bi.noItemAvailable.visible()
                            bi.layoutShimmerLoading.gone()
                            bi.repoList.gone()
                        } else
                            bi.nestedScrollView.showSnackBar(
                                message = it.message.toString()
                            )
                    } ?: run {
                        bi.layoutShimmerLoading.gone()
                        bi.nestedScrollView.showSnackBar(
                            message = getString(R.string.error_internet),
                            action = getString(R.string.retry)
                        ) {
                            viewModel.retryConnection()
                        }

                    }
                }
                ResponseStatus.LOADING -> {
                    it.data?.let { item ->
                        if (item.page == 1) {
                            bi.layoutShimmerLoading.visible()
                            bi.repoList.gone()
                            bi.noItemAvailable.gone()
                        } else
                            bi.nestedScrollView.showSnackBar(
                                message = getString(R.string.loading_repos)
                            )
                    }
                }
            }

        })
    }

    /*
    * Initialize recyclerView with onClickListener
    * */
    @SuppressLint("ResourceType")
    private fun callingRecyclerView() {
        adapter = GenericListAdapter(R.layout.repo_view) { item, position ->
        }
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        bi.repoList.adapter = adapter
    }
}