package com.example.testretrofit2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_employee.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var list : ArrayList<Employee>?= ArrayList<Employee>()

    var employeeAdapter : EmployeeAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var rvEmployees : RecyclerView = findViewById(R.id.rv_Employees)
        employeeAdapter = EmployeeAdapter(this)

        var employee = Employee()
        employee.employeeAge= 10
        employee.employeeName= "a"
        employee.employeeSalary = 100000
        employee.id = 100
        list?.add(employee)
        rvEmployees.setHasFixedSize(true)
        rvEmployees.layoutManager = LinearLayoutManager(this)
        list?.let { employeeAdapter?.setList(it) }
        rvEmployees.adapter = employeeAdapter
        btn_ReadData.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_ReadData -> {
                tv_ResponseCode.text = "response codee: "
                RetrofitClient.instance.getEmployees().enqueue(object: Callback<FileJson>{
                    override fun onFailure(call: Call<FileJson>, t: Throwable) {
                        Toast.makeText(applicationContext, "co van de ${t.message}", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<FileJson>, response: Response<FileJson>) {
                        tv_ResponseCode.text = "response code: "+response.code().toString()
                        val fileJson = response.body()
                        if (response.isSuccessful){
                            Toast.makeText(applicationContext, "ok ${fileJson?.data}", Toast.LENGTH_SHORT).show()
                            list = fileJson?.data as ArrayList<Employee>?
                            employeeAdapter?.setList(list!!)
                        } else {
                            Toast.makeText(applicationContext, "lam gi co gi,"+response.code().toString(), Toast.LENGTH_SHORT).show()
                        }
                    }


                })

            }
        }
    }
}