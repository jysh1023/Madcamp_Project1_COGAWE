package com.example.madcamp_project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.madcamp_project1.databinding.ActivityGalleryBinding
import com.example.madcamp_project1.gallery.PhotoData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.IllegalStateException

class GalleryActivity : AppCompatActivity() {
    private var _binding: ActivityGalleryBinding? = null
    private val binding get() = _binding!!
    private var photoDataList: ArrayList<PhotoData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        photoDataList = readFromJSON("gallery.json") ?: arrayListOf()

        val pos: Int? = intent?.getIntExtra("position", -1)
        if (pos == -1 || pos == null) {
            throw IllegalStateException("invalid position in gallery activity")
        }
        with(binding) {
            viewPagerPhotoDetail.adapter = GalleryViewPagerAdapter(photoDataList!!)
            viewPagerPhotoDetail.post {
                viewPagerPhotoDetail.setCurrentItem(pos, false)
            }
        }
    }

    private fun readFromJSON(fileName: String): ArrayList<PhotoData>? {
        val assetManager = resources.assets
        var result: ArrayList<PhotoData>? = null

        try {
            val inputStream = assetManager.open(fileName)
            val reader = inputStream.bufferedReader()
            val gson = Gson()
            result = gson.fromJson(reader, object : TypeToken<ArrayList<PhotoData>>() {}.type)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }
}