package com.example.madcamp_project1.gallery

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_project1.R
import com.example.madcamp_project1.databinding.ItemPhotoBinding
import com.squareup.picasso.Picasso

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var photoList: MutableList<PhotoData> = mutableListOf()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: PhotoData, pos: Int)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(private val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(photoData: PhotoData) {
            Picasso.get()
                .load(if(photoData.photoUri.isNullOrBlank()) "https://avatars.githubusercontent.com/u/86835564?s=200&v=4" else photoData.photoUri)
//                .fit()
                .resizeDimen(R.dimen.gallery_image_width, R.dimen.gallery_image_height)
                .centerCrop()
                .into(binding.photoImgView)
            binding.descriptionTextView.text = photoData.photoDescription

            val pos = adapterPosition
            if(pos == RecyclerView.NO_POSITION) {
                Log.d("LOG", "recycler view no position")
                return
            }
            itemView.setOnClickListener{
                listener?.onItemClick(itemView, photoData, pos)
            }
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