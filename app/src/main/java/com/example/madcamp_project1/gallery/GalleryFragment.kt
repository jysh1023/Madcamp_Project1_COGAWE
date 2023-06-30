package com.example.madcamp_project1.gallery

import android.os.Bundle
import android.provider.ContactsContract.Contacts.Photo
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_project1.R
import com.example.madcamp_project1.databinding.ActivityMainBinding
import com.example.madcamp_project1.databinding.FragmentGalleryBinding
import com.example.madcamp_project1.databinding.ItemPhotoBinding

class GalleryFragment : Fragment() {

    private var _bind: FragmentGalleryBinding? = null
    private val bind get() = _bind!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _bind = FragmentGalleryBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val mDatas: MutableList<PhotoData> = mutableListOf()
//        with(mDatas){
//            add(PhotoData("","dog1"))
//            add(PhotoData("","dog2"))
//            add(PhotoData("","dog3"))
//            add(PhotoData("","dog4"))
//            add(PhotoData("","dog5"))
//            add(PhotoData("","dog2"))
//            add(PhotoData("","dog6"))
//            add(PhotoData("","dog7"))
//            add(PhotoData("","dog8"))
//            add(PhotoData("","dog9"))
//            add(PhotoData("","dog10"))
//            add(PhotoData("","dog11"))
//            add(PhotoData("","dog12"))
//        }
        val adapter = RecyclerViewAdapter()
//        adapter.photoList = mDatas
        with(adapter.photoList) {
            add(PhotoData("","photo1"))
            add(PhotoData("","photo2"))
            add(PhotoData("","photo3"))
            add(PhotoData("","photo4"))
            add(PhotoData("","photo5"))
            add(PhotoData("","photo2"))
            add(PhotoData("","photo6"))
            add(PhotoData("","photo7"))
            add(PhotoData("","photo8"))
            add(PhotoData("","photo9"))
            add(PhotoData("","photo10"))
            add(PhotoData("","photo11"))
            add(PhotoData("","photo12"))
        }
        bind.recyclerView.adapter = adapter
        bind.recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    }
}