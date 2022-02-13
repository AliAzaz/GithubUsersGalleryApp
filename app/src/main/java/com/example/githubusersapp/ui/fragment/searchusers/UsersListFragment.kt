package com.example.githubusersapp.ui.fragment.searchusers

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusersapp.R
import com.example.githubusersapp.adapters.GenericListAdapter
import com.example.githubusersapp.base.FragmentBase
import com.example.githubusersapp.base.repository.ResponseStatus
import com.example.githubusersapp.databinding.FragmentUsersListBinding
import com.example.githubusersapp.model.UsersInfo
import com.example.githubusersapp.utils.*
import java.util.*
import kotlin.collections.ArrayList


class UsersListFragment : FragmentBase() {

    private val viewModel: UsersViewModel by lazy {
        obtainViewModel(this, UsersViewModel::class.java, viewModelFactory)
    }
    private lateinit var adapter: GenericListAdapter<UsersInfo>
    private lateinit var bi: FragmentUsersListBinding
    private var actionBarHeight = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*
        * Initializing databinding
        * */
        return FragmentUsersListBinding.inflate(inflater, container, false).apply {
            bi = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        /*
        * Get actionbar height for use in translation
        * */
        context?.let { item ->
            actionBarHeight = with(TypedValue().also {
                item.theme.resolveAttribute(
                    android.R.attr.actionBarSize,
                    it,
                    true
                )
            }) {
                TypedValue.complexToDimensionPixelSize(this.data, resources.displayMetrics)
            }

            /*
            * Translate items on menu click
            * */
            actionBarHeight *= -1
            bi.fldGrpSearchUsers.translationY = actionBarHeight.toFloat()
            bi.nestedScrollView.translationY = actionBarHeight.toFloat() / 2

        }

        /*
        * Initiating recyclerview
        * */
        callingRecyclerView()

        /*
        * Fetch User list
        * */
        viewModel.usersResponse.observe(viewLifecycleOwner, {
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
                        //bi.multiStateView.viewState = MultiStateView.ViewState.ERROR
                        bi.layoutShimmerLoading.gone()
                        bi.productList.gone()
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

        })

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
                bi.edtSearchUsers.hideKeyboard()
                val s = bi.edtSearchUsers.text.toString()
                adapter.clearProductItem()
                bi.populateTxt.text = "Search: ${s.toUpperCase(Locale.ENGLISH)}"
                viewModel.searchUserFromRemote(s)
            }
            false
        }

        /*
        * User search clear
        * */
        bi.inputSearchUsers.setEndIconOnClickListener {
            bi.edtSearchUsers.setText("")
            adapter.clearProductItem()
            bi.populateTxt.text = "Search: Ali"
            viewModel.searchUserFromRemote("Ali")
        }

    }

    /*
    * Initialize recyclerView with onClickListener
    * */
    @SuppressLint("ResourceType")
    private fun callingRecyclerView() {
        adapter = GenericListAdapter(R.layout.product_view) { item, position ->
            findNavController().navigate(
                UsersListFragmentDirections.actionUsersListFragmentToUserDetailFragment2(
                    item.login
                )
            )
        }
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        bi.productList.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.search_menu).isVisible = true
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_menu -> {
                bi.fldGrpSearchUsers.animate().apply {
                    duration = 1000
                    translationY(if (bi.fldGrpSearchUsers.translationY == actionBarHeight.toFloat()) 10f else actionBarHeight.toFloat())
                }.start()
                bi.nestedScrollView.animate().apply {
                    duration = 1000
                    translationY(if (bi.nestedScrollView.translationY == actionBarHeight.toFloat() / 2) 10f else actionBarHeight.toFloat() / 2)
                }.start()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}