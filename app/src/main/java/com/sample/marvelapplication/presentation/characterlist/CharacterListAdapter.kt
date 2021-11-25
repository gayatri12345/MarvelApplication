package com.sample.marvelapplication.presentation.characterlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.domain.model.MarvelCharacter
import com.sample.marvelapplication.R
import com.sample.marvelapplication.databinding.ItemCharacterBinding
import java.util.*

/**
 * This class os to bind data to character list
 * @param onItemClicked
 */
class CharacterListAdapter(private val onItemClicked: (position: Int) -> Unit) :
    RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {
    private val listData: MutableList<MarvelCharacter> = ArrayList()
    private lateinit var layoutInflater: LayoutInflater

    class ViewHolder(
        private val binding: ItemCharacterBinding,
        private val onItemClicked: (position: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: MarvelCharacter) {
            binding.character = item
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) = onItemClicked(adapterPosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (!this::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding: ItemCharacterBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_character, parent, false)
        return ViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    fun addData(list: List<MarvelCharacter>) {
        this.listData.clear()
        this.listData.addAll(list)
        notifyDataSetChanged()
    }
}
