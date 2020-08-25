package com.example.testretrofit2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import kotlinx.android.synthetic.main.activity_create_update_employee.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.dialog_processbar.*
import kotlinx.android.synthetic.main.dialog_select.*
import kotlinx.android.synthetic.main.dialog_yes_no.*
import kotlinx.android.synthetic.main.dialog_yes_no.tv_TitleOfCustomDialogConfirm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateAndUpdateEmployee : AppCompatActivity(), View.OnClickListener {
    private var BUTTON_TYPE = 0 //  1 is change profile, 2 is create new employee
    private var contactPost : ContactPost = ContactPost()
    val REQUEST_SELECT_IMAGE = 1111
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_update_employee)
        init()
    }


    private fun setDefaultInformation() {
        edt_InputAge.setText(contactPost.custom?.stringAge)
        edt_InputEmail.setText(contactPost.email)
        edt_InputFirstName.setText(contactPost.firstName)
        edt_InputLastName.setText(contactPost.lastName)
        img_AvatarCreateOrUpdate.setImageURI(Uri.parse(contactPost.custom?.stringImage))

    }

    private fun init() {
        btn_Cancel.setOnClickListener(this)
        btn_Save.setOnClickListener(this)
        btn_BackCreateOrUpdate.setOnClickListener(this)
        img_AvatarCreateOrUpdate.setOnClickListener(this)
        var intent = intent
        BUTTON_TYPE = intent.getIntExtra("BUTTON", 0)
//        var bundle = intent.extras
//        if (bundle != null) {
//            if (BUTTON_TYPE == 1) {
//                this.employee = bundle.getSerializable("EMPLOYEE2") as Employee
//                setDefaultInformation()
//            }
//        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun selectImage(view: View) {
        val i = Intent(
            Intent.ACTION_OPEN_DOCUMENT,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )

        startActivityForResult(i, REQUEST_SELECT_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                this.contactPost.custom?.stringImage = data?.getData().toString()
                img_AvatarCreateOrUpdate.setImageURI(Uri.parse(this.contactPost.custom?.stringImage))
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onClick(v: View?) {
        when (v) {
            img_AvatarCreateOrUpdate -> {
                selectImage(img_AvatarCreateOrUpdate)
            }
            btn_Save -> {
                val dialogYesNo = MaterialDialog(this)
                    .noAutoDismiss()
                    .customView(R.layout.dialog_yes_no)
                dialogYesNo.show()
                dialogYesNo.setCancelable(false)
                dialogYesNo.tv_TitleOfCustomDialogConfirm.text = "Are you sure save this?"
                dialogYesNo.btn_CancelDialogConfirm.setOnClickListener() {
                    dialogYesNo.dismiss()
                }
                dialogYesNo.btn_AcceptDiaLogConFirm.setOnClickListener() {
                    this.contactPost.custom?.stringAge = edt_InputAge.text.toString()
                    this.contactPost.lastName = edt_InputLastName.text.toString()
                    this.contactPost.firstName = edt_InputFirstName.text.toString()
                    this.contactPost.email = edt_InputEmail.text.toString()
                    var bodyPost = BodyPost()
                    bodyPost.contactPost = this.contactPost
                    dialogYesNo.dismiss()

                    if (BUTTON_TYPE == 2) {//2 is create new employee
                     val callInsert =   RetrofitClient.instance.insertContact(bodyPost)
                        val dialog = MaterialDialog(this)
                            .noAutoDismiss()
                            .customView(R.layout.dialog_processbar)
                        dialog.show()
                        dialog.setCancelable(false)
                        dialog.btn_CancelUpdate.setOnClickListener(){
                            callInsert.cancel()
                        }
                        callInsert.enqueue(object: Callback<Contact>{
                            override fun onFailure(call: Call<Contact>, t: Throwable) {
                                Log.d("AAAAACreate Failure", t.message)
                                dialog.dismiss()
                            }
                            override fun onResponse(
                                call: Call<Contact>,
                                response: Response<Contact>
                            ) {
                                if(response.isSuccessful){
                                    Log.d("AAAAACreate response",response.body()?.contactId)
                                } else {
                                    Log.d("AAAAAACreate response !",response.code().toString() + response.message().toString())
                                }
                                dialog.dismiss()
                            }
                        })
                    }

//                    } else if (BUTTON_TYPE == 1) {//  1 is change profile
//
//                        val dialog = MaterialDialog(this)
//                            .noAutoDismiss()
//                            .customView(R.layout.dialog_processbar)
//                        dialog.setCancelable(false)
//                        dialog.show()
//
//                        val call = RetrofitClient.instance.updateEmployee(
//                            this.employee.id!!,
//                            this.employee.employeeName!!,
//                            this.employee.employeeSalary!!,
//                            this.employee.employeeAge!!
//                        )
//                        dialog.btn_CancelUpdate.setOnClickListener() {
//                            call.cancel()
//                        }
//                        call.enqueue(object : Callback<FileJson2> {
//                            override fun onFailure(call: Call<FileJson2>, t: Throwable) {
//                                dialog.dismiss()
//                                if (call.isCanceled) {
//                                    Toast.makeText(
//                                        applicationContext,
//                                        "Cancel successful!",
//                                        Toast.LENGTH_SHORT
//                                    )
//                                        .show()
//                                    Log.d("AAAAUpdate failure", "Cancel successful!")
//
//                                } else {
//
//                                    Toast.makeText(
//                                        applicationContext,
//                                        "k luu duoc ${t.message}",
//                                        Toast.LENGTH_LONG
//                                    ).show()
//                                    Log.d("AAAAUpdate failure", t.message)
//                                }
//                                dialog.dismiss()
//
//                            }
//                            override fun onResponse(call: Call<FileJson2>, response: Response<FileJson2>) {
//                                dialog.dismiss()
//                                if (response.isSuccessful) {
//                                    Log.d("AAAAAUpdate",response.body()?.message + response.body()?.toString())
//                                    Toast.makeText(
//                                        applicationContext,
//                                         response.body()?.message,
//                                        Toast.LENGTH_LONG
//                                    )
//                                        .show()
//                                    val intent: Intent = Intent()
//                                    setResult(Activity.RESULT_OK, intent)
//                                    finish()
//                                } else {
//                                    Log.d("AAAAAUpdate",response.code().toString() + " " + response.message())
//                                    Toast.makeText(
//                                        applicationContext,
//                                        "k thanh cong ${response.message()}",
//                                        Toast.LENGTH_LONG
//                                    )
//                                        .show()
//                                }
//                            }
//
//                        })
//                    }

                }


            }
            btn_Cancel -> {
                finish()
            }
            btn_BackCreateOrUpdate -> {
                finish()
            }
     }
    }
}