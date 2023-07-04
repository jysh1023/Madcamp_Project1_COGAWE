package com.example.madcamp_project1.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_project1.databinding.ItemUltraSrtFcstBinding
import com.squareup.picasso.Picasso

class SrtFcstRecyclerViewAdapter : RecyclerView.Adapter<SrtFcstRecyclerViewAdapter.ViewHolder>() {
    var fcstList: MutableList<FcstData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUltraSrtFcstBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = fcstList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(fcstList[position])
    }

    inner class ViewHolder(private val binding: ItemUltraSrtFcstBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fcstData: FcstData) {
            binding.srtFcstHour.text = when(fcstData.fcstHour) {
                in 0..11 -> "오전 ${(fcstData.fcstHour+11)%12+1}시"
                else -> "오후 ${(fcstData.fcstHour+11)%12+1}시"
            }
            Picasso.get()
                .load(fcstData.fcstSkyDrawableId)
                .resize(100, 100)
                .centerCrop()
                .into(binding.srtFcstSkyIcon)
            binding.srtFcstTmp.text = "${fcstData.fcstTmp}°"
        }
    }
}