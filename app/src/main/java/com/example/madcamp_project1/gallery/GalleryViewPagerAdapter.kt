package com.example.madcamp_project1.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_project1.R
import com.example.madcamp_project1.databinding.ItemPhotoDetailBinding
import com.squareup.picasso.Picasso

class GalleryViewPagerAdapter(photoDataList: MutableList<PhotoData>) :
    RecyclerView.Adapter<GalleryViewPagerAdapter.PagerViewHolder>() {
    private var item = photoDataList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding =
            ItemPhotoDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(item[position])
    }

    override fun getItemCount(): Int = item.size

    inner class PagerViewHolder(private val binding: ItemPhotoDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PhotoData) {
            Picasso.get()
                .load(data.photoUri.ifBlank { "https://avatars.githubusercontent.com/u/86835564?s=200&v=4" })
                .resizeDimen(R.dimen.gallery_detail_image_size, R.dimen.gallery_detail_image_size)
                .centerInside()
//                for Glide
//                .apply(RequestOptions.overrideOf(R.dimen.gallery_image_width, R.dimen.gallery_image_height))
//                .apply(RequestOptions.centerInsideTransform())
                .into(binding.photoDetail)
//            binding.fullDescriptionTextView.text = data.photoDescription
        }
    }
}