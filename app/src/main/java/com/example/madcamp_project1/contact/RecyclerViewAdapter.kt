package com.example.madcamp_project1.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_project1.databinding.ItemContactBinding


class RecyclerViewAdapter(private val contact:Contact) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemContactBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(data: ContactData){
            with(binding) {
                userName.text = data.name
                userContact.text = data.contact
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(contact[position])
    }

    override fun getItemCount(): Int {
        return contact.size
    }
}