package com.example.testretrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import kotlinx.android.synthetic.main.activity_detail_employee.*

class DetailEmployee : AppCompatActivity(), View.OnClickListener {
    private var  BUTTON_TYPE = 0 // 0 is view detail, 1 is change profile, 2 is create new employee
    private var employee : Employee? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_employee)
        init()


    }
    private fun setCanChangeEdt(boolean: Boolean){
        if(!boolean) {
            edt_DetailSalary.inputType = InputType.TYPE_NULL
            edt_DetailAge.inputType = InputType.TYPE_NULL
            edt_DetailName.inputType = InputType.TYPE_NULL
            btn_Cancel.visibility = View.INVISIBLE
            btn_Change.visibility = View.VISIBLE
            btn_Save.visibility = View.INVISIBLE
            btn_Back.visibility = View.VISIBLE
        } else {
            edt_DetailSalary.inputType = InputType.TYPE_CLASS_TEXT
            edt_DetailAge.inputType = InputType.TYPE_CLASS_TEXT
            edt_DetailName.inputType = InputType.TYPE_CLASS_TEXT
            btn_Change.visibility = View.INVISIBLE
            btn_Cancel.visibility = View.VISIBLE
            btn_Back.visibility = View.INVISIBLE
            btn_Save.visibility = View.VISIBLE
        }
    }
    private fun setDefaultInformation(){
        edt_DetailAge.setText(employee?.employeeAge?.toString())
        edt_DetailName.setText(employee?.employeeName)
        edt_DetailSalary.setText(employee?.employeeSalary?.toString())
        setCanChangeEdt(false)
    }
     private fun init() {
         btn_Change.setOnClickListener(this)
         btn_Cancel.setOnClickListener(this)
         btn_Save.setOnClickListener(this)
         btn_Back.setOnClickListener(this)
         btn_Cancel.visibility = View.INVISIBLE
         btn_Save.visibility = View.INVISIBLE
        var intent = intent
        BUTTON_TYPE = intent.getIntExtra("BUTTON", 0)
        var bundle = intent.extras
        if( bundle != null ){
             this.employee = bundle.getSerializable("EMPLOYEE") as Employee
            if( BUTTON_TYPE == 0) {
               setDefaultInformation()
                btn_Change.visibility = View.INVISIBLE
                btn_Cancel.visibility = View.INVISIBLE
            }
        }

    }

    override fun onClick(v: View?) {
        when (v) {
            btn_Change -> {
                setCanChangeEdt(true)
            }
            btn_Save -> {
                setCanChangeEdt(false)
            }
            btn_Cancel -> {
                setDefaultInformation()
            }
            btn_Back -> {
                finish()
            }
        }
    }
}