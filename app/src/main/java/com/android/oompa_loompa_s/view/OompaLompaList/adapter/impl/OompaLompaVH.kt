package com.android.oompa_loompa_s.view.OompaLompaList.adapter.impl

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.oompa_loompa_s.R
import com.android.oompa_loompa_s.model.OompaLoompa
import com.android.oompa_loompa_s.view.OompaLompaList.adapter.OompaLompaVHListener
import com.squareup.picasso.Picasso

open class OompaLompaVH(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

    val textViewAge = itemView.findViewById(R.id.textViewAge) as TextView
    val textViewName = itemView.findViewById(R.id.textViewNameSurname) as TextView
    val textViewCountry = itemView.findViewById(R.id.textViewCountry) as TextView
    val textViewEmail = itemView.findViewById(R.id.textViewEmail) as TextView
    val imageViewOompaLoompa = itemView.findViewById(R.id.imageViewOompaLoompa) as ImageView

    lateinit var  listener : OompaLompaVHListener

    fun bind(oompaLoompa: OompaLoompa, listener: OompaLompaVHListener){
        this.listener = listener
        textViewName.text = oompaLoompa.firstName + " " + oompaLoompa.lastName
        textViewAge.text = oompaLoompa.age.toString()
        textViewCountry.text = oompaLoompa.country
        textViewEmail.text = oompaLoompa.email
        imageViewOompaLoompa.loadUrl(oompaLoompa.image)
        itemView.setOnClickListener(this)
    }
    fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(this)
    }

    override fun onClick(v: View?) {
        listener.onClickItem(adapterPosition)
    }
}