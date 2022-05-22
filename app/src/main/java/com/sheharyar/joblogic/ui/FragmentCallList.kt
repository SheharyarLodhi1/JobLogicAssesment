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
import androidx.viewpager.widget.PagerAdapter
import com.sheharyar.joblogic.R
import com.sheharyar.joblogic.base.BaseFragment
import com.sheharyar.joblogic.data.entities.CallListModel
import com.sheharyar.joblogic.data.entities.DataList
import com.sheharyar.joblogic.databinding.FragmentCallListBinding
import com.sheharyar.joblogic.ui.adapters.CallListAdapter
import com.sheharyar.joblogic.utils.Resource
import com.sheharyar.joblogic.viewmodel.CallListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_call_list.*

@AndroidEntryPoint
class FragmentCallList : BaseFragment<FragmentCallListBinding>(FragmentCallListBinding::inflate),
    View.OnClickListener {

    private lateinit var mCallListAdapter: CallListAdapter
    private val viewModel: CallListViewModel by viewModels()
    var mCallList: ArrayList<DataList> = ArrayList()

    override fun getLayoutId(): Int {
        return R.layout.fragment_call_list
    }

    override fun isBindedToActivityLifeCycle(): Boolean {
        return true
    }

    override fun onVisible(view: View, savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() {
        ivBackButtonCallList.setOnClickListener(this)
        viewModel?.getCallResponse()
        setupRecyclerView()
        setupObservers()
    }

    override fun onInVisible() {

    }

    private fun setupObservers() {

        viewModel.loading.observe(this, Observer {
            if (it) {
                progress_bar_call_list.visibility = View.VISIBLE
            } else {
                progress_bar_call_list.visibility = View.GONE
            }
        })
        viewModel.errorMessage.observe(this) {
            // show popup error message
            progress_bar_call_list.visibility = View.GONE
        }

        viewModel.callListData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progress_bar_call_list.visibility = View.GONE
                    if (it.data!= null) {
                        //mCallList.add(it?.data)
                        for (i in 0 until it?.data?.size) {
                            mCallList.add(it?.data?.get(i))
                        }
                    }
                    mCallListAdapter.setItems(mCallList)

                    //AppConstant.MediaMetaList.mediaMetaArrayList.add(it.data?.get(0)?.media?.get(0)?.media_metadata?.get(0)!!)
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    progress_bar_call_list.visibility = View.VISIBLE

            }
        })
    }

    private fun setupRecyclerView() {
        mCallListAdapter = CallListAdapter()
        rvCallList.layoutManager = LinearLayoutManager(requireContext())
        rvCallList.adapter = mCallListAdapter
    }

    override fun onClick(p0: View?) {
        childLayoutCallList.visibility = View.GONE
        loadFragment(HomeScreenFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        // create a FragmentManager
        val fm: FragmentManager = requireActivity().supportFragmentManager
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        val fragmentTransaction: FragmentTransaction = fm.beginTransaction()
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayoutCallList, fragment)
        fragmentTransaction.commit() // save the changes

    }
}