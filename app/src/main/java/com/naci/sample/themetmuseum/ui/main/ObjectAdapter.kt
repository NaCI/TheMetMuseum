package com.naci.sample.themetmuseum.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naci.sample.themetmuseum.data.model.ObjectInfo
import com.naci.sample.themetmuseum.databinding.ItemObjectBinding

internal class ObjectAdapter(private val clickListener: ObjectInfoListener) :
    ListAdapter<ObjectInfo, ObjectAdapter.ViewHolder>(ObjectDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val objectItem = getItem(position)
        holder.bind(objectItem, clickListener)
    }

    class ViewHolder private constructor(private val binding: ItemObjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(objectInfo: ObjectInfo, clickListener: ObjectInfoListener) {
            Glide.with(binding.root).load(objectInfo.primaryImageSmall).into(binding.imageObject)
            binding.nameObject.text = objectInfo.title
            binding.root.setOnClickListener {
                clickListener.onClick(objectInfo)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemObjectBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

internal class ObjectInfoListener(val clickListener: (objectInfo: ObjectInfo) -> Unit) {
    fun onClick(objectInfo: ObjectInfo) = clickListener(objectInfo)
}

internal class ObjectDiffCallback : DiffUtil.ItemCallback<ObjectInfo>() {
    override fun areItemsTheSame(oldItem: ObjectInfo, newItem: ObjectInfo): Boolean {
        return oldItem.objectID == newItem.objectID
    }

    override fun areContentsTheSame(oldItem: ObjectInfo, newItem: ObjectInfo): Boolean {
        return oldItem == newItem
    }
}