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
import com.sheharyar.joblogic.data.entities.ItemToSell
import com.sheharyar.joblogic.data.entities.ListOfItems
import com.sheharyar.joblogic.databinding.FragmentBuyListBinding
import com.sheharyar.joblogic.ui.adapters.BuyListAdapter
import com.sheharyar.joblogic.ui.adapters.CallListAdapter
import com.sheharyar.joblogic.utils.AppConstant
import com.sheharyar.joblogic.utils.Resource
import com.sheharyar.joblogic.viewmodel.BuyListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_buy_list.*
import kotlinx.android.synthetic.main.fragment_call_list.*

@AndroidEntryPoint
class FragmentBuyList : BaseFragment<FragmentBuyListBinding>(FragmentBuyListBinding::inflate),
    View.OnClickListener {

    private lateinit var mBuyListAdapter: BuyListAdapter
    private val viewModel: BuyListViewModel by viewModels()
    var mBuyList: ArrayList<ListOfItems> = ArrayList()

    override fun getLayoutId(): Int {
        return R.layout.fragment_buy_list
    }

    override fun isBindedToActivityLifeCycle(): Boolean {
        return true
    }

    override fun onVisible(view: View, savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() {
        ivBackButtonBuyList.setOnClickListener(this)
        setupRecyclerView()
        setupObservers()
    }

    override fun onInVisible() {

    }

    private fun setupObservers() {

        if (AppConstant.FragmentBuyLists?.haveItemInDb) {
            viewModel.getItemsFromDb.observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        progress_bar_buy_list.visibility = View.GONE
                        if (!it.data?.isNullOrEmpty()!!) {
                            for (i in 0 until it?.data?.size) {
                                mBuyList.add(it.data?.get(i))
                            }
                        }

                        mBuyListAdapter.setItems(mBuyList)
                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }

                    Resource.Status.LOADING -> {
                        progress_bar_buy_list.visibility = View.VISIBLE
                    }

                }
            })
        } else if (!AppConstant.FragmentBuyLists?.haveItemInDb) {
            viewModel.buyListData.observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        progress_bar_buy_list.visibility = View.GONE
                        if (!it.data?.isNullOrEmpty()!!) {
                            for (i in 0 until it?.data?.size) {
                                mBuyList.add(it.data?.get(i))
                            }
                        }

                        mBuyListAdapter.setItems(mBuyList)
                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }

                    Resource.Status.LOADING -> {
                        progress_bar_buy_list.visibility = View.VISIBLE
                    }

                }
            })
        }
    }

    private fun setupRecyclerView() {
        mBuyListAdapter = BuyListAdapter()
        rvBuyList.layoutManager = LinearLayoutManager(requireContext())
        rvBuyList.adapter = mBuyListAdapter
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    private fun loadFragment(fragment: Fragment) {
        // create a FragmentManager
        val fm: FragmentManager = requireActivity().supportFragmentManager
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        val fragmentTransaction: FragmentTransaction = fm.beginTransaction()
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayoutBuyList, fragment)
        fragmentTransaction.commit() // save the changes

    }

    override fun onClick(p0: View?) {
        childLayoutBuyList.visibility = View.GONE
        loadFragment(HomeScreenFragment())
    }
}