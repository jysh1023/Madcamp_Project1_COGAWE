package com.example.madcamp_project1.gallery

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_project1.GalleryActivity
import com.example.madcamp_project1.R
import com.example.madcamp_project1.databinding.FragmentGalleryBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import android.util.Pair as UtilPair

class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGalleryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecyclerViewAdapter()
        adapter.setOnItemClickListener(object : RecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: PhotoData, pos: Int) {
                val intent = Intent(activity, GalleryActivity::class.java)
                intent.putExtra("position", pos)

                val imgView: View = v.findViewById<ImageView>(R.id.photoPreview)
                val options: ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                    activity,
                    UtilPair.create(imgView, imgView.transitionName)
                )
                startActivity(intent, options.toBundle())

            }
        })
        adapter.photoList = readFromJSON("gallery.json") ?: mutableListOf()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun readFromJSON(fileName: String): MutableList<PhotoData>? {
        val assetManager = resources.assets
        var result: MutableList<PhotoData>? = null

        try {
            val inputStream = assetManager.open(fileName)
            val reader = inputStream.bufferedReader()
            val gson = Gson()
            result = gson.fromJson(reader, object : TypeToken<MutableList<PhotoData>>() {}.type)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }
}