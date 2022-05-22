package com.sheharyar.joblogic.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sheharyar.joblogic.R
import com.sheharyar.joblogic.base.BaseFragment
import com.sheharyar.joblogic.databinding.FragmentHomeBinding
import com.sheharyar.joblogic.utils.AppConstant
import com.sheharyar.joblogic.utils.Resource
import com.sheharyar.joblogic.viewmodel.BuyListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeScreenFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    View.OnClickListener {

    private val viewModel: BuyListViewModel by viewModels()

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun isBindedToActivityLifeCycle(): Boolean {
        return true
    }

    override fun onVisible(view: View, savedInstanceState: Bundle?) {
        setupUI()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getItemsFromDb.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (!it.data?.isNullOrEmpty()!!) {

                        AppConstant.FragmentBuyLists?.haveItemInDb = true
                    }
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING -> {

                }

            }
        })
    }

    private fun setupUI() {
        btnCallList?.setOnClickListener(this)
        btnBuyList?.setOnClickListener(this)
        btnSellList?.setOnClickListener(this)
    }

    override fun onInVisible() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCallList -> {
                /*//findNavController().navigate(R.id.action_mHomeScreenFragment_to_mCallListFragment
                    *//*bundleOf("position" to position)*//*)*/
                childLayout.visibility = View.GONE
                loadFragment(FragmentCallList())
            }
            R.id.btnBuyList -> {
                //findNavController().navigate(R.id.action_mHomeScreenFragment_to_mBuyListFragment)
                childLayout.visibility = View.GONE
                loadFragment(FragmentBuyList())
            }
            R.id.btnSellList -> {
                //findNavController().navigate(R.id.action_mHomeScreenFragment_to_mSellListFragment)
                childLayout.visibility = View.GONE
                loadFragment(FragmentSellList())
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // create a FragmentManager
        val fm: FragmentManager = requireActivity().supportFragmentManager
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        val fragmentTransaction: FragmentTransaction = fm.beginTransaction()
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit() // save the changes

    }
}