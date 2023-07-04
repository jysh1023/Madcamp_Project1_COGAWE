package com.example.madcamp_project1.contact

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_project1.R
import com.example.madcamp_project1.databinding.FragmentContactBinding
import com.google.gson.Gson
import java.io.IOException

class ContactFragment : Fragment(){

    private val contactViewModel: ContactViewModel by activityViewModels()

    private var _bind: FragmentContactBinding? = null
    private val bind get() = _bind!!

    private lateinit var contactDetail: ContactDetailDialog
    lateinit var adapter: RecyclerViewAdapter
    private lateinit var newContactDialog: NewContactDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _bind = FragmentContactBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataset = readFromJSON("contact.json")
        adapter = RecyclerViewAdapter(dataset!!)

        bind.recyclerView.adapter = adapter
        bind.recyclerView.layoutManager=LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        bind.addContact.setOnClickListener {
//            Toast.makeText(context,"Pressed", Toast.LENGTH_SHORT).show()
            newContactDialog =  NewContactDialog()
            newContactDialog.show(activity?.supportFragmentManager!!,"new contact")
        }

        contactViewModel.getData().observe(viewLifecycleOwner, Observer {
            adapter.currContact.add(it)

        })


        setFilter()

        adapter.itemClickListener = object : RecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val item = adapter.contactFiltered[position]
//                Toast.makeText(context,"Clicked ${item.name}", Toast.LENGTH_SHORT).show()
                contactDetail = ContactDetailDialog(item)
                contactDetail.show(activity?.supportFragmentManager!!, "contact detail")
            }
        }

    }

    private fun readFromJSON(fileName: String): Contact? {

        val assetManager = resources.assets
        var result: Contact? = null

        try {
            val inputStream = assetManager.open(fileName)
            val reader = inputStream.bufferedReader()
            val gson = Gson()
            result = gson.fromJson(reader, Contact::class.java)
        } catch (e: IOException){
            e.printStackTrace()
        }
        return result
    }

    private fun setFilter() {
        bind.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }

        })
    }


}