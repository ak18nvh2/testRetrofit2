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

class CreateAndUpdateEmployee : AppCompatActivity(), View.OnClickListener {
    private var BUTTON_TYPE = 0 //  1 is change profile, 2 is create new employee
    private var employee: Employee = Employee()
    private var fileJson: FileJson? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_update_employee)
        init()
    }


    private fun setDefaultInformation() {
        edt_InputAge.setText(employee?.employeeAge?.toString())
        edt_InputName.setText(employee?.employeeName)
        edt_InputSalary.setText(employee?.employeeSalary?.toString())
    }

    private fun init() {
        btn_Cancel.setOnClickListener(this)
        btn_Save.setOnClickListener(this)
        btn_BackCreateOrUpdate.setOnClickListener(this)
        var intent = intent
        BUTTON_TYPE = intent.getIntExtra("BUTTON", 0)
        var bundle = intent.extras
        if (bundle != null) {
            if (BUTTON_TYPE == 1) {
                this.employee = bundle?.getSerializable("EMPLOYEE2") as Employee
                setDefaultInformation()
            } else if (BUTTON_TYPE == 2) {
                this.fileJson = bundle?.getSerializable("FILEJSON") as FileJson?
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_Save -> {
                val dialog = MaterialDialog(this)
                    .noAutoDismiss()
                    .customView(R.layout.dialog_yes_no)
                dialog.setCancelable(false)
                dialog.tv_TitleOfCustomDialogConfirm.text = "Are you sure save this?"
                dialog.btn_CancelDialogConfirm.setOnClickListener() {
                    dialog.dismiss()
                }
                dialog.btn_AcceptDiaLogConFirm.setOnClickListener() {
                    this.employee.employeeName = edt_InputName.text.toString()
                    this.employee.employeeAge = edt_InputAge.text.toString().toInt()
                    this.employee.employeeSalary = edt_InputSalary.text.toString().toInt()

                    if (BUTTON_TYPE == 2) {
                     val callInsert =   RetrofitClient.instance.insertEmployee(this.employee)
                        callInsert.enqueue(object: Callback<FileJson2>{
                            override fun onFailure(call: Call<FileJson2>, t: Throwable) {
                                Log.d("AAAAA", t.message)
                            }
                            override fun onResponse(
                                call: Call<FileJson2>,
                                response: Response<FileJson2>
                            ) {
                                if(response.isSuccessful){
                                    Log.d("AAAAA",response.body()?.message)
                                } else {
                                    Log.d("AAAAAA",response.code().toString() + response.message().toString())
                                }
                            }
                        })

                    } else if (BUTTON_TYPE == 1) {
                        Toast.makeText(this, "day la save cua update", Toast.LENGTH_SHORT).show()
                        val dialog = MaterialDialog(this)
                            .noAutoDismiss()
                            .customView(R.layout.dialog_processbar)
                        dialog.setCancelable(false)
                        dialog.show()
                        window.setFlags(
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        )
                        val call = RetrofitClient.instance.updateEmployee(
                            this.employee.id!!,
                            this.employee.employeeName!!,
                            this.employee.employeeSalary!!,
                            this.employee.employeeAge!!
                        )
                        dialog.btn_CancelUpdate.setOnClickListener() {
                            call.cancel()
                        }
                        call.enqueue(object : Callback<FileJson2> {
                            override fun onFailure(call: Call<FileJson2>, t: Throwable) {
                                dialog.dismiss()
                                if (call.isCanceled) {

                                    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                                    Toast.makeText(
                                        applicationContext,
                                        "Cancel successful!",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                } else {
                                    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                                    Toast.makeText(
                                        applicationContext,
                                        "k luu duoc ${t.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    Log.d("AAAA", t.message)
                                }

                            }
                            override fun onResponse(call: Call<FileJson2>, response: Response<FileJson2>) {
                                dialog.dismiss()
                                if (response.isSuccessful) {
                                    Log.d("AAAAA",response.body()?.message)
                                    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                                    Toast.makeText(
                                        applicationContext,
                                        "luu thanh cong ${response.body()?.message}",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
//                                    val intent: Intent = Intent()
//                                    setResult(Activity.RESULT_OK, intent)
//                                    finish()
                                } else {
                                    Log.d("AAAAAA",response.code().toString() + response.message().toString())
                                    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                                    dialog.dismiss()
                                    Toast.makeText(
                                        applicationContext,
                                        "k thanh cong ${response.message()}",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                }
                            }

                        })
                    }
                    dialog.dismiss()
                }
                dialog.show()

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