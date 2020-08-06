package com.example.testretrofit2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import kotlinx.android.synthetic.main.activity_create_update_employee.*
import kotlinx.android.synthetic.main.dialog_select.*
import kotlinx.android.synthetic.main.dialog_yes_no.*
import kotlinx.android.synthetic.main.dialog_yes_no.tv_TitleOfCustomDialogConfirm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateAndUpdateEmployee : AppCompatActivity(), View.OnClickListener {
    private var BUTTON_TYPE = 0 //  1 is change profile, 2 is create new employee
    private var employee: Employee? = null
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
            this.employee = bundle?.getSerializable("EMPLOYEE2") as Employee?
            if (BUTTON_TYPE == 1) {
                setDefaultInformation()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_Save -> {
                val dialog = MaterialDialog(this)
                    .noAutoDismiss()
                    .customView(R.layout.dialog_yes_no)
                dialog.tv_TitleOfCustomDialogConfirm.text = "Are you sure save this?"
                dialog.btn_CancelDialogConfirm.setOnClickListener(){
                    dialog.dismiss()
                }
                dialog.btn_AcceptDiaLogConFirm.setOnClickListener(){
                    this.employee?.employeeName = edt_InputName.text.toString()
                    this.employee?.employeeAge = edt_InputAge.text.toString().toInt()
                    this.employee?.employeeSalary = edt_InputSalary.text.toString().toInt()
                    this.employee?.id = 1000
                    this.employee?.profileImage = null
                    if( BUTTON_TYPE == 2){
                        RetrofitClient.instance.insertEmployee(this.employee?.id, this.employee?.employeeName,
                                                                this.employee?.employeeSalary, this.employee?.employeeAge,
                                                                this.employee?.profileImage).enqueue(object: Callback<Employee>{
                            override fun onFailure(call: Call<Employee>, t: Throwable) {
                                Toast.makeText(applicationContext, "k luu duoc ${t.message}", Toast.LENGTH_LONG).show()
                            }

                            override fun onResponse(
                                call: Call<Employee>,
                                response: Response<Employee>
                            ) {
                                if(response.isSuccessful){
                                    Toast.makeText(applicationContext, "luu thanh cong ${response.body()?.employeeName}", Toast.LENGTH_LONG)
                                        .show()
//                                    val intent: Intent = Intent()
//                                    setResult(Activity.RESULT_OK, intent)
//                                    finish()
                                } else {
                                    Toast.makeText(applicationContext, "k thanh cong ${response.message()}", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }

                        })


                    } else if( BUTTON_TYPE == 1){
                        Toast.makeText(this, "day la save cua update", Toast.LENGTH_SHORT).show()
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