package com.example.madcamp_project1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_project1.databinding.ItemPhotoBinding
import com.example.madcamp_project1.databinding.ItemPhotoDetailBinding
import com.example.madcamp_project1.gallery.PhotoData
import com.squareup.picasso.Picasso

class GalleryViewPagerAdapter(photoDataList: MutableList<PhotoData>) : RecyclerView.Adapter<GalleryViewPagerAdapter.PagerViewHolder>() {
    var item = photoDataList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder  {
        val binding = ItemPhotoDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(item[position])
    }

    override fun getItemCount(): Int = item.size

    inner class PagerViewHolder(private val binding: ItemPhotoDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PhotoData) {
            Picasso.get()
                .load(if (data.photoUri.isNullOrBlank()) "https://avatars.githubusercontent.com/u/86835564?s=200&v=4" else data.photoUri)
                .resizeDimen(R.dimen.gallery_image_width, R.dimen.gallery_image_height)
                .centerInside()
                .into(binding.photoDetail)
            binding.fullDescriptionTextView.text = data.photoDescription
        }
    }
}