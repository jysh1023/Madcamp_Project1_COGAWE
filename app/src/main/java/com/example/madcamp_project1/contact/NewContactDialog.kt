package com.example.madcamp_project1.contact

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import com.example.madcamp_project1.MainActivity
import com.example.madcamp_project1.R
import com.example.madcamp_project1.databinding.NewContactDialogBinding


class NewContactDialog: DialogFragment() {

    private val contactViewModel: ContactViewModel by activityViewModels()

    private var _bind: NewContactDialogBinding? = null
    private val bind get() = _bind!!

    private lateinit var name: String
    private lateinit var contact: String
    private lateinit var school: String
    private lateinit var interest: String

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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.noButton.setOnClickListener {
            dismiss()
        }

        bind.yesButton.setOnClickListener {
            this.name = bind.nameInput.text.toString()
            this.contact = bind.contactInput.text.toString()
            this.school = bind.schoolInput.text.toString()
            this.interest = bind.interestInput.text.toString()

            if (TextUtils.isEmpty(name)){
                Toast.makeText(context,"Empty field exists",Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(contact)) {
                Toast.makeText(context,"Empty field exists",Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(school)) {
                Toast.makeText(context,"Empty field exists",Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(interest)){
                Toast.makeText(context,"Empty field exists",Toast.LENGTH_SHORT).show()
            } else {

                contactViewModel.updateData((ContactData(name, contact, school, interest)))
                dismiss()
            }

        }
    }


}