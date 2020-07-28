package com.android.oompa_loompa_s.view.OompaLompaList.adapter.impl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.oompa_loompa_s.R
import com.android.oompa_loompa_s.model.OompaLoompa
import com.android.oompa_loompa_s.view.OompaLompaList.adapter.OompaLompaVHListener


class OompaLompaAdapter(private val listener: OompaLompaVHListener) : RecyclerView.Adapter<OompaLompaVH>() {

    var dataList: MutableList<OompaLoompa> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OompaLompaVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return OompaLompaVH(layoutInflater.inflate(R.layout.oompaloompa_viewholder, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: OompaLompaVH, position: Int) {
        val item  = dataList[position]
        holder.bind(item, listener)
    }

    fun setData(data: MutableList<OompaLoompa>) {
        if(this.dataList.isEmpty()) {
            this.dataList = data
            this.notifyDataSetChanged()
        } else {
            this.dataList = data
            this.notifyItemRangeChanged(this.dataList.size - data.size, this.dataList.size-1)
        }
    }

}