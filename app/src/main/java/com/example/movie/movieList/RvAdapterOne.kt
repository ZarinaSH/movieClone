package com.example.movie.movieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import kotlinx.android.synthetic.main.rv_item.view.*

class RvAdapterOne(var list: MutableList<Result>):RecyclerView.Adapter<RvAdapterOne.RvView>() {
    class RvView(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvView {
        return RvView(LayoutInflater.from(parent.context).inflate((R.layout.rv_item),parent,false))
    }

    override fun onBindViewHolder(holder: RvView, position: Int) {
        var a = list[position]
        holder.itemView.rvFilmName.text = a.original_title

    }

    override fun getItemCount(): Int = list.size
}