package com.sheharyar.joblogic.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sheharyar.joblogic.data.entities.DataList
import com.sheharyar.joblogic.data.entities.ListOfItems
import com.sheharyar.joblogic.databinding.ItemBuyListBinding
import kotlinx.android.synthetic.main.item_buy_list.view.*

class BuyListAdapter :
    RecyclerView.Adapter<BuyListAdapter.BuyListViewHolder>() {

    private var items = ArrayList<ListOfItems>()

    fun setItems(items: ArrayList<ListOfItems>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun filterList(filteredList: ArrayList<ListOfItems>) {
        items = filteredList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyListViewHolder {
        val binding: ItemBuyListBinding =
            ItemBuyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuyListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BuyListViewHolder, position: Int) {
        return holder.bind(items[position])
    }

    class BuyListViewHolder(
        private val itemCallListBinding: ItemBuyListBinding,

        ) : RecyclerView.ViewHolder(itemCallListBinding.root) {
        private lateinit var newsResultsModel: ListOfItems


        @SuppressLint("SetTextI18n")
        fun bind(item: ListOfItems) {
            this.newsResultsModel = item

            itemCallListBinding?.root?.tvNameDetails.text = item?.name
            itemCallListBinding?.root?.tvPriceDetails.text = item?.price.toString()
            itemCallListBinding?.root?.tvQuantityDetails.text = item?.quantity.toString()
        }
    }
}