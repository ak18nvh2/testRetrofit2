package com.example.testretrofit2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import kotlinx.android.synthetic.main.activity_create_update_employee.*
import kotlinx.android.synthetic.main.dialog_processbar.*
import kotlinx.android.synthetic.main.dialog_select.*
import kotlinx.android.synthetic.main.dialog_yes_no.*
import kotlinx.android.synthetic.main.dialog_yes_no.tv_TitleOfCustomDialogConfirm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateAndUpdateEmployee : AppCompatActivity() {
//    private var BUTTON_TYPE = 0 //  1 is change profile, 2 is create new employee
//    private var employee: Employee = Employee()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_update_employee)
      //  init()
    }


//    private fun setDefaultInformation() {
////        edt_InputAge.setText(employee.employeeAge?.toString())
////        edt_InputName.setText(employee.employeeName)
////        edt_InputSalary.setText(employee.employeeSalary?.toString())
////    }
////
////    private fun init() {
////        btn_Cancel.setOnClickListener(this)
////        btn_Save.setOnClickListener(this)
////        btn_BackCreateOrUpdate.setOnClickListener(this)
////        var intent = intent
////        BUTTON_TYPE = intent.getIntExtra("BUTTON", 0)
////        var bundle = intent.extras
////        if (bundle != null) {
////            if (BUTTON_TYPE == 1) {
////                this.employee = bundle.getSerializable("EMPLOYEE2") as Employee
////                setDefaultInformation()
////            }
////        }
////    }
////
////    override fun onClick(v: View?) {
////        when (v) {
////            btn_Save -> {
////                val dialogYesNo = MaterialDialog(this)
////                    .noAutoDismiss()
////                    .customView(R.layout.dialog_yes_no)
////                dialogYesNo.show()
////                dialogYesNo.setCancelable(false)
////                dialogYesNo.tv_TitleOfCustomDialogConfirm.text = "Are you sure save this?"
////                dialogYesNo.btn_CancelDialogConfirm.setOnClickListener() {
////                    dialogYesNo.dismiss()
////                }
////                dialogYesNo.btn_AcceptDiaLogConFirm.setOnClickListener() {
////                    this.employee.employeeName = edt_InputName.text.toString()
////                    this.employee.employeeAge = edt_InputAge.text.toString().toInt()
////                    this.employee.employeeSalary = edt_InputSalary.text.toString().toInt()
////                    dialogYesNo.dismiss()
////
////                    if (BUTTON_TYPE == 2) {//2 is create new employee
////                     val callInsert =   RetrofitClient.instance.insertEmployee(this.employee)
////                        val dialog = MaterialDialog(this)
////                            .noAutoDismiss()
////                            .customView(R.layout.dialog_processbar)
////                        dialog.show()
////                        dialog.setCancelable(false)
////                        dialog.btn_CancelUpdate.setOnClickListener(){
////                            callInsert.cancel()
////                        }
////                        callInsert.enqueue(object: Callback<FileJson2>{
////                            override fun onFailure(call: Call<FileJson2>, t: Throwable) {
////                                Log.d("AAAAACreate Failure", t.message)
////                                dialog.dismiss()
////                            }
////                            override fun onResponse(
////                                call: Call<FileJson2>,
////                                response: Response<FileJson2>
////                            ) {
////                                if(response.isSuccessful){
////                                    Log.d("AAAAACreate response",response.body()?.message + " " + response.body()?.toString())
////                                    Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT).show()
////                                } else {
////                                    Log.d("AAAAAACreate response !",response.code().toString() + response.message().toString())
////                                }
////                                dialog.dismiss()
////                            }
////                        })
////
////                    } else if (BUTTON_TYPE == 1) {//  1 is change profile
////
////                        val dialog = MaterialDialog(this)
////                            .noAutoDismiss()
////                            .customView(R.layout.dialog_processbar)
////                        dialog.setCancelable(false)
////                        dialog.show()
////
////                        val call = RetrofitClient.instance.updateEmployee(
////                            this.employee.id!!,
////                            this.employee.employeeName!!,
////                            this.employee.employeeSalary!!,
////                            this.employee.employeeAge!!
////                        )
////                        dialog.btn_CancelUpdate.setOnClickListener() {
////                            call.cancel()
////                        }
////                        call.enqueue(object : Callback<FileJson2> {
////                            override fun onFailure(call: Call<FileJson2>, t: Throwable) {
////                                dialog.dismiss()
////                                if (call.isCanceled) {
////                                    Toast.makeText(
////                                        applicationContext,
////                                        "Cancel successful!",
////                                        Toast.LENGTH_SHORT
////                                    )
////                                        .show()
////                                    Log.d("AAAAUpdate failure", "Cancel successful!")
////
////                                } else {
////
////                                    Toast.makeText(
////                                        applicationContext,
////                                        "k luu duoc ${t.message}",
////                                        Toast.LENGTH_LONG
////                                    ).show()
////                                    Log.d("AAAAUpdate failure", t.message)
////                                }
////                                dialog.dismiss()
////
////                            }
////                            override fun onResponse(call: Call<FileJson2>, response: Response<FileJson2>) {
////                                dialog.dismiss()
////                                if (response.isSuccessful) {
////                                    Log.d("AAAAAUpdate",response.body()?.message + response.body()?.toString())
////                                    Toast.makeText(
////                                        applicationContext,
////                                         response.body()?.message,
////                                        Toast.LENGTH_LONG
////                                    )
////                                        .show()
////                                    val intent: Intent = Intent()
////                                    setResult(Activity.RESULT_OK, intent)
////                                    finish()
////                                } else {
////                                    Log.d("AAAAAUpdate",response.code().toString() + " " + response.message())
////                                    Toast.makeText(
////                                        applicationContext,
////                                        "k thanh cong ${response.message()}",
////                                        Toast.LENGTH_LONG
////                                    )
////                                        .show()
////                                }
////                            }
////
////                        })
////                    }
////
////                }
////
////
////            }
////            btn_Cancel -> {
////                finish()
////            }
////            btn_BackCreateOrUpdate -> {
////                finish()
////            }
//     }
//    }
}