package com.sheharyar.joblogic.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sheharyar.joblogic.R
import com.sheharyar.joblogic.base.BaseFragment
import com.sheharyar.joblogic.data.entities.ListOfItems
import com.sheharyar.joblogic.databinding.FragmentSellListBinding
import com.sheharyar.joblogic.ui.adapters.BuyListAdapter
import com.sheharyar.joblogic.utils.Resource
import com.sheharyar.joblogic.viewmodel.BuyListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_buy_list.*
import kotlinx.android.synthetic.main.fragment_sell_list.*
@AndroidEntryPoint
class FragmentSellList : BaseFragment<FragmentSellListBinding>(FragmentSellListBinding::inflate),
    View.OnClickListener {

    private lateinit var mBuyListAdapter: BuyListAdapter
    private val viewModel: BuyListViewModel by viewModels()
    var mBuyList: ArrayList<ListOfItems> = ArrayList()

    override fun getLayoutId(): Int {
        return R.layout.fragment_sell_list
    }

    override fun isBindedToActivityLifeCycle(): Boolean {
       return true
    }

    override fun onVisible(view: View, savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() {
        ivBackButtonSellList.setOnClickListener(this)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getItemsFromDb.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progress_bar_sell_list.visibility = View.GONE
                    if (!it.data?.isNullOrEmpty()!!) {
                        for (i in 0 until it?.data?.size) {
                            mBuyList.add(it.data?.get(i))
                        }
                    }

                    mBuyListAdapter.setItems(mBuyList)
                    //AppConstant.MediaMetaList.mediaMetaArrayList.add(it.data?.get(0)?.media?.get(0)?.media_metadata?.get(0)!!)
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING -> {
                    progress_bar_sell_list.visibility = View.VISIBLE
                }

            }
        })
    }

    private fun setupRecyclerView() {
        mBuyListAdapter = BuyListAdapter()
        rvSellList.layoutManager = LinearLayoutManager(requireContext())
        rvSellList.adapter = mBuyListAdapter
    }

    override fun onInVisible() {

    }

    override fun onClick(p0: View?) {
        childLayoutSellList.visibility = View.GONE
        loadFragment(HomeScreenFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        // create a FragmentManager
        val fm: FragmentManager = requireActivity().supportFragmentManager
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        val fragmentTransaction: FragmentTransaction = fm.beginTransaction()
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayoutSellList, fragment)
        fragmentTransaction.commit() // save the changes

    }
}