package com.example.madcamp_project1.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_project1.databinding.FragmentGalleryBinding

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

        val mDatas: MutableList<PhotoData> = mutableListOf()
        with(mDatas){
            add(PhotoData("https://madcamp.io/img/intro-together.3087da89.png","몰입캠프"))
            add(PhotoData("https://avatars.githubusercontent.com/u/86835564?s=200&v=4","몰입캠프 GitHub"))
            add(PhotoData("https://madcamp.io/img/intro-other-life-1.b6a0fa59.jpg","몰입캠프2"))
            add(PhotoData("https://madcamp.io/img/intro-immerse-1.a041fbd6.jpg","몰입캠프3"))
            add(PhotoData("https://blog.kakaocdn.net/dn/vzQh8/btqTDcAHG3c/BBq0prrRsJMXyeAYw7kkN0/img.jpg","카이스트 몰입캠프 MADCAMP 참가 후기"))
            add(PhotoData("","img2"))
            add(PhotoData("","img6"))
            add(PhotoData("","img7"))
            add(PhotoData("","img8"))
            add(PhotoData("","img9"))
            add(PhotoData("","img10"))
            add(PhotoData("","img11"))
        }
        val adapter = RecyclerViewAdapter()
        adapter.photoList = mDatas
        bind.recyclerView.adapter = adapter
        bind.recyclerView.layoutManager = GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)
    }
}