package com.example.githubusersapp.ui.fragment.searchusers

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusersapp.R
import com.example.githubusersapp.adapters.GenericListAdapter
import com.example.githubusersapp.base.FragmentBase
import com.example.githubusersapp.base.repository.ResponseStatus
import com.example.githubusersapp.databinding.FragmentUsersListBinding
import com.example.githubusersapp.model.UsersInfo
import com.example.githubusersapp.utils.*
import dagger.hilt.android.AndroidEntryPoint
import org.apache.commons.lang3.StringUtils
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class UsersListFragment : FragmentBase() {

    private val viewModel by viewModels<UsersViewModel>()
    private lateinit var adapter: GenericListAdapter<UsersInfo>
    private lateinit var bi: FragmentUsersListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*
        * Initializing databinding
        * */
        return FragmentUsersListBinding.inflate(inflater, container, false).apply {
            bi = this
            setInputLabelString(viewModel.getSearchQuery())
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        /*
        * Initiating recyclerview
        * */
        callingRecyclerView()

        /*
        * Fetch User list
        * */
        viewModel.usersResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    it.data?.apply {
                        adapter.productItems = usersInfo as ArrayList<UsersInfo>
                        bi.layoutShimmerLoading.gone()
                        bi.productList.visible()
                    }
                }
                ResponseStatus.ERROR -> {
                    it.data?.let { item ->
                        if (item.page == 1) {
                            bi.noItemAvailable.visible()
                            bi.layoutShimmerLoading.gone()
                            bi.productList.gone()
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
                            bi.productList.gone()
                            bi.noItemAvailable.gone()
                        } else
                            bi.nestedScrollView.showSnackBar(
                                message = getString(R.string.loading_users)
                            )
                    }
                }
            }

        }

        /*
        * Checking scrollview scroll end
        * */
        bi.productList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadNextPageUsers()
                }
            }
        })

        /*
        * User search
        * */
        bi.edtSearchUsers.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (bi.edtSearchUsers.text.toString().trim().isNotEmpty()) {
                    bi.edtSearchUsers.hideKeyboard()
                    val s = bi.edtSearchUsers.text.toString()
                    adapter.clearProductItem()
                    setInputLabelString(s)
                    viewModel.searchUserFromRemote(s)
                    bi.layoutShimmerLoading.visibility = View.VISIBLE
                }
            }
            false
        }

        /*
        * User search clear
        * */
        bi.inputSearchUsers.setEndIconOnClickListener {
            //Clearing adapter
            adapter.clearProductItem()
            //Clearing Viewmodel fields
            viewModel.clearFields()
            //Clearing layout
            bi.edtSearchUsers.text = null
            setInputLabelString(null)
        }

    }

    /*
    * Initialize recyclerView with onClickListener
    * */
    @SuppressLint("ResourceType")
    private fun callingRecyclerView() {
        adapter = GenericListAdapter(R.layout.user_view) { item, position ->
            findNavController().navigate(
                UsersListFragmentDirections.actionUsersListFragmentToUserDetailFragment2(
                    item.login
                )
            )
        }
        adapter.apply {
            stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            bi.productList.adapter = this
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private fun setInputLabelString(searchedLbl: String?) {
        bi.populateTxt.text =
            searchedLbl?.let {
                String.format("Search: ${it.uppercase(Locale.ENGLISH)}")
            } ?: StringUtils.EMPTY
    }
}