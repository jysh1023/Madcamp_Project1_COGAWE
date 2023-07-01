package com.example.madcamp_project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.madcamp_project1.databinding.ActivityGalleryBinding
import com.example.madcamp_project1.gallery.PhotoData
import com.squareup.picasso.Picasso

class GalleryActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        mBinding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val data: PhotoData = PhotoData(
            intent?.getStringExtra("photoUri") ?: "",
            intent?.getStringExtra("photoDescription") ?: ""
        )

        Picasso.get()
            .load(if(data.photoUri.isNullOrBlank()) "https://avatars.githubusercontent.com/u/86835564?s=200&v=4" else data.photoUri)
            .resizeDimen(R.dimen.gallery_image_width, R.dimen.gallery_image_height)
            .centerInside()
            .into(mBinding.fullPhotoImgView)
        mBinding.fullDescriptionTextView.text = data.photoDescription
    }
}