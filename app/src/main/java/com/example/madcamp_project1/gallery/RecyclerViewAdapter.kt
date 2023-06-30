package com.example.madcamp_project1.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_project1.databinding.ItemPhotoBinding

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var photoList: MutableList<PhotoData> = mutableListOf()

    inner class ViewHolder(private val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(photoData: PhotoData) {
//            binding.photo.setImageResource()
            binding.description.text = photoData.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = photoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photoList[position])
    }
}