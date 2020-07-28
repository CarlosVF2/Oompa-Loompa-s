package com.android.oompa_loompa_s.view.OompaLompaList.impl

import android.app.ProgressDialog
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.oompa_loompa_s.R
import com.android.oompa_loompa_s.application.OompaLoompaApplication
import com.android.oompa_loompa_s.core.CoreFragment
import com.android.oompa_loompa_s.core.impl.CoreFragmentImpl
import com.android.oompa_loompa_s.model.OompaLoompa
import com.android.oompa_loompa_s.presenter.OompaLompaFragmentPresenter
import com.android.oompa_loompa_s.view.OompaLompaDetail.impl.OompaLoompaDetailFragmentImpl
import com.android.oompa_loompa_s.view.OompaLompaList.OompaLompaFragment
import com.android.oompa_loompa_s.view.OompaLompaList.adapter.OompaLompaVHListener
import com.android.oompa_loompa_s.view.OompaLompaList.adapter.impl.OompaLompaAdapter
import kotlinx.android.synthetic.main.fragment_oompa_loompa.*
import javax.inject.Inject


class OompaLompaFragmentImpl : CoreFragmentImpl(), CoreFragment, OompaLompaVHListener,
    OompaLompaFragment {
    @Inject
    lateinit var oompaLompaFragmentPresenter: OompaLompaFragmentPresenter

    private lateinit var progressDialog: ProgressDialog
    private lateinit var searchView:SearchView
    private var ompaLompaAdapter: OompaLompaAdapter = OompaLompaAdapter(this)
    private var data: MutableList<OompaLoompa> = ArrayList()

    //region lifecycle Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);//Para que la gestión sea del fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().application as OompaLoompaApplication).oompaLoompaComponent.inject(this)
        oompaLompaFragmentPresenter.setView(this)
        return inflater.inflate(R.layout.fragment_oompa_loompa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Cargando...")
        progressDialog.setMessage("Cargando listado de datos, por favor espere...")
    }

    fun setUpRecyclerView() {
        recyclerViewOompaLoompa.setHasFixedSize(true)
        val lm = LinearLayoutManager(context)
        recyclerViewOompaLoompa.layoutManager = lm
        recyclerViewOompaLoompa.adapter = ompaLompaAdapter
        recyclerViewOompaLoompa.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val visibleItemCount: Int = lm.getChildCount()
                val totalItemCount: Int = lm.getItemCount()
                val firstVisibleItemPosition: Int = lm.findFirstVisibleItemPosition()
                // Load more if we have reach the end to the recyclerView
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    oompaLompaFragmentPresenter.loadMoreItems()
                }
            }
        })

    }

    //region menu


    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {

        menuInflater.inflate(R.menu.menu_fragment_listado, menu)
        val search = menu.findItem(R.id.action_search)
        searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                            oompaLompaFragmentPresenter.filterData(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                            oompaLompaFragmentPresenter.filterData(newText)
                return true
            }
        })
        oompaLompaFragmentPresenter.onViewBinded()
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun closeSearchView() {
        searchView.isIconified = true
    }

    //endregion menu

    //endregion lifecycle Fragment

    //region CoreFragment

    override fun onInitLoading() {
        view?.visibility = View.GONE
    }

    override fun onLoaded() {
        view?.visibility = View.VISIBLE
    }

    override fun setVisibilityView() {
        if (data.isEmpty()) {
            super.setVisibilityView()
        }
    }

    //endregion  CoreFragment

    //region Interface

    fun setData(data: MutableList<OompaLoompa>) {
        this.data = data
        ompaLompaAdapter.setData(data)
    }

    override fun filterData(data: MutableList<OompaLoompa>) {
        ompaLompaAdapter.dataList.clear()
        setData(data)
    }

    //region show loading

    override fun showLoadingProgress() {
        if (!progressDialog.isShowing) {
            progressDialog.show()
        }
    }

    override fun hideLoadinProgress() {
        if(progressDialog.isShowing){
            progressDialog.dismiss()
        }
    }
    //endregion show loading

    //endregion Interface

    //region Click VH

    override fun onClickItem(postion: Int) {
        val bundle = Bundle()
        bundle.putLong("id", this.data[postion].id)

        val oompaLoompaDetailFragment =
            OompaLoompaDetailFragmentImpl()
        oompaLoompaDetailFragment.arguments = bundle
        val fragmentTransaction = parentFragmentManager
            .beginTransaction()
            //.setReorderingAllowed(true) // setAllowOptimization before 26.1.0
            //.addSharedElement(imageView, imageView.getTransitionName())
        //fragmentTransaction.setCustomAnimations(R.animator.fade_in, R.animator.fade_out);
        fragmentTransaction.replace(
                R.id.nav_host_fragment,
                oompaLoompaDetailFragment,
                OompaLoompaDetailFragmentImpl::class.simpleName
            )
        fragmentTransaction
            .addToBackStack(null)
            .commit();
    }

    //endregion Click VH


}