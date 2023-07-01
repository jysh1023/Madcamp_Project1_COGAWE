package com.example.madcamp_project1.contact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_project1.databinding.FragmentContactBinding
import com.google.gson.Gson
import java.io.IOException

class ContactFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _bind: FragmentContactBinding? = null
    private val bind get() = _bind!!

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
        val adapter = RecyclerViewAdapter(dataset!!)

        bind.recyclerView.adapter = adapter
        bind.recyclerView.layoutManager=LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
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

    private fun searchFromJSON(data: ArrayList<ContactData>, string: String): ContactData{

        var result: ContactData? = null

        for(item in data){
            when(item.name){
                string -> result = item
                else -> {}
            }
        }

        return result!!

    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        TODO("Not yet implemented")
    }


}