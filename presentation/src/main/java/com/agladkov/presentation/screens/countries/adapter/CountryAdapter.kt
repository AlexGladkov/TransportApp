package com.agladkov.presentation.screens.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.agladkov.presentation.R

class CountryAdapter: RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private val data: MutableList<CountryCellModel> = ArrayList()

    fun setData(models: List<CountryCellModel>) {
        data.clear()
        data.addAll(models)
        notifyDataSetChanged()
    }

    fun addData(models: List<CountryCellModel>) {
        data.addAll(models)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_country, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(model = data[position])
    }

    class CountryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
        private val imgIcon = itemView.findViewById<ImageView>(R.id.imgIcon)

        fun bind(model: CountryCellModel) {
            txtTitle.text = model.title
            imgIcon.setImageDrawable(ContextCompat.getDrawable(itemView.context, model.icon))
        }
    }
}