package com.murat.hashtag_hw3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.murat.hashtag_hw3.databinding.ItemWordsBinding

class TagsAdapter : RecyclerView.Adapter<TagsAdapter.WordsViewHolder>() {
    private val tagList = arrayListOf<String>()
    private lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        return WordsViewHolder(
            ItemWordsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setListener(onItemClick: OnItemClickListener) {
        itemClickListener = onItemClick
    }

    fun setTagsList(tags: List<String>) {
        tagList.clear()
        tagList.addAll(tags)
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        holder.onBind(tagList[position])
    }

    override fun getItemCount(): Int {
        return tagList.size
    }

   inner class WordsViewHolder(private val binding: ItemWordsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(tags: String) {
            binding.tvWords.text = tags
            itemView.setOnClickListener {
                itemClickListener.onItemClick(tags)

            }
        }

    }
    interface OnItemClickListener {
        fun onItemClick(tags: String)
    }
}