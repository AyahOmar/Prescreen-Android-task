package com.aya.walmarttask.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aya.walmarttask.data.model.Anime
import com.aya.walmarttask.databinding.ItemLayoutBinding
import com.squareup.picasso.Picasso

class MainAdapter(private val anime: ArrayList<Anime>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(private val itemBinding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(anime: Anime) {
            itemView.apply {

                //View Binding
                itemBinding.mTextViewTitle.text = anime.title
                itemBinding.type.text = anime.type
                itemBinding.synopsis.text = anime.synopsis
                itemBinding.episodes.text = "EPs: " + anime.episodes.toString()
                itemBinding.rated.text = anime.rated
                itemBinding.score.text = anime.score.toString()

                Picasso.with(context).load(anime.image_url)
                    .into(itemBinding.mImageViewCardViewItem);

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        var view = DataViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        return view
    }

    override fun getItemCount(): Int = anime.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(anime[position])
    }

    fun addAnimes(anime: List<Anime>) {
        this.anime.apply {
            clear()
            addAll(anime)
        }

    }
}