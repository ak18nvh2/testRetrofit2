package com.example.testretrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_create_update_employee.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

//        var intent = intent
////        var bundle = intent.extras
////        if( bundle != null ){
////            var employee = bundle.getSerializable("EMPLOYEE") as Employee
////            tv_DetailAge.text = "Age: "+ employee.employeeAge?.toString()
////            tv_DetailName.text = "Name: "+ employee.employeeName
////            tv_DetailSalary.text = "Salary: "+ employee.employeeSalary?.toString()
////            tv_DetailID.text ="ID: "+ employee.id?.toString()
////        }
////        btn_BackDetail.setOnClickListener(){
////            finish()
////        }
    }
}