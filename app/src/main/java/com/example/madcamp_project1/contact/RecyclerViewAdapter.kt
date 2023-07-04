package com.example.madcamp_project1.contact

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_project1.databinding.ItemContactBinding


class RecyclerViewAdapter(private val contact:Contact) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(), Filterable {

    var contactFiltered: ArrayList<ContactData>
    var currContact : ArrayList<ContactData>

    interface OnItemClickListener {
        fun onItemClick(position: Int){}
    }

    var itemClickListener: OnItemClickListener?= null

    init {
        contactFiltered = contact
        currContact = contact
    }


    inner class ViewHolder(private val binding: ItemContactBinding): RecyclerView.ViewHolder(binding.root){

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
        holder.bind(contactFiltered[position])
        holder.itemView.setOnClickListener{
            Log.d("tag", position.toString())
            itemClickListener?.onItemClick(position)
        }

    }

    override fun getItemCount(): Int {
        return contactFiltered.size
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charString = p0.toString()
                if (charString.isEmpty()){
                    contactFiltered = currContact
                } else{
                    var filteredList = ArrayList<ContactData>()
                    if (currContact != null) {
                        for (user in currContact) {

                            if (user.name.lowercase().contains(charString.lowercase())) {
                                filteredList.add(user)
                            } else if (user.contact.contains(charString)){
                                filteredList.add(user)
                            }

                        }
                        contactFiltered = filteredList
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = contactFiltered
                filterResults.count = contactFiltered.size

                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                if (p1 != null) {
                    contactFiltered = p1.values as ArrayList<ContactData>
                    notifyDataSetChanged()
                }
            }

        }
    }


}