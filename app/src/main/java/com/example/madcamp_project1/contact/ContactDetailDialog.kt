package com.example.madcamp_project1.contact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.madcamp_project1.databinding.ItemContactDetailBinding

class ContactDetailDialog (item : ContactData) : DialogFragment(){

    private var _bind: ItemContactDetailBinding? = null
    private val bind get() = _bind!!

    private var name: String?= null
    private var contact: String?= null
    private var school: String?= null
    private var interest: String?= null

    init {
        this.name = item.name
        this.contact = item.contact
        this.school = item.school
        this.interest = item.interest
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _bind = ItemContactDetailBinding.inflate(layoutInflater, container, false)

        bind.userName.text = name
        bind.userContact.text = contact
        bind.userSchool.text = school
        bind.userInterest.text = interest

        bind.callButton.setOnClickListener {

            val telNumber = "tel:${contact}"

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(telNumber))
            startActivity(intent)
        }

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _bind = null
    }



}