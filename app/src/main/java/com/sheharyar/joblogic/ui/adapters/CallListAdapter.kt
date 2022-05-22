package com.sheharyar.joblogic.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sheharyar.joblogic.data.entities.DataList
import com.sheharyar.joblogic.databinding.ItemCallListBinding
import kotlinx.android.synthetic.main.item_call_list.view.*

class CallListAdapter() :
    RecyclerView.Adapter<CallListAdapter.CallViewHolder>() {

    private var items = ArrayList<DataList>()

    fun setItems(items: ArrayList<DataList>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun filterList(filteredList: ArrayList<DataList>) {
        items = filteredList
        notifyDataSetChanged()
        // send call back to fragment to tell the user that there is no matching roles here..
        /*if (filteredList.size == 0) {
            listener.onNotifyNoMatchingCities()
            return
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallViewHolder {
        val binding: ItemCallListBinding =
            ItemCallListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CallViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CallViewHolder, position: Int) {
        return holder.bind(items[position])
    }

    class CallViewHolder(
        private val itemCallListBinding: ItemCallListBinding,

        ) : RecyclerView.ViewHolder(itemCallListBinding.root) {
        private lateinit var newsResultsModel: DataList


        @SuppressLint("SetTextI18n")
        fun bind(item: DataList) {
            this.newsResultsModel = item

            itemCallListBinding?.root?.tvNameDetails.text = item?.name
            itemCallListBinding?.root?.tvNumberDetails.text = item?.number
        }
    }
}