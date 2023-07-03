package com.example.madcamp_project1.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.madcamp_project1.databinding.NewContactDialogBinding


class NewContactDialog: DialogFragment() {

    private var _bind: NewContactDialogBinding? = null
    private val bind get() = _bind!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = NewContactDialogBinding.inflate(layoutInflater, container, false)
        return bind.root

        bind.noButton.setOnClickListener{

        }

        bind.yesButton.setOnClickListener{

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _bind = null
    }

}