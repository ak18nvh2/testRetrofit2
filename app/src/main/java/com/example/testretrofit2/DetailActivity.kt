package com.example.testretrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_create_update_employee.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_employee.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var intent = intent
        var bundle = intent.extras
        if( bundle != null ){
            var contact = bundle.getSerializable("CONTACT") as Contact
            tv_DetailID.text = "ID: " + contact.contactId
            tv_DetailEmail.text = "Email: "+ contact.email
            tv_DetailCreateAt.text = "Create at: " + contact.createdAt
            tv_DetailUpdateAt.text = "Update at: " + contact.updatedAt
            tv_DetailApiOriginated.text = "Api originated: " + contact.apiOriginated.toString()
            tv_DetailAge.text = "Age: " + contact.customFields?.get(1)?.value
            tv_Name.text = "Full name: " + contact.lastName + " " + contact.firstName
        }
        btn_BackDetail.setOnClickListener(){
            finish()
        }
    }
}