package com.example.testretrofit2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.dialog_select.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(), View.OnClickListener,
    EmployeeAdapter.IRecyclerViewWithHomeActivity {
    private var list: ArrayList<Employee>? = ArrayList<Employee>()
    private val REQUEST_HOME_TO_CREATE_OR_UPDATE = 10
    private var fileJson: FileJson? = null
    var employeeAdapter: EmployeeAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var rvEmployees: RecyclerView = findViewById(R.id.rv_Employees)
        employeeAdapter = EmployeeAdapter(this, this)
        rvEmployees.setHasFixedSize(true)
        rvEmployees.layoutManager = LinearLayoutManager(this)
        list?.let { employeeAdapter?.setList(it) }
        rvEmployees.adapter = employeeAdapter
        btn_ReadData.setOnClickListener(this)
        btn_CreateNewEmployee.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_CreateNewEmployee -> {
                var intent = Intent(this, CreateAndUpdateEmployee::class.java)
                intent.putExtra("BUTTON", 2)
                var bundle = Bundle()
                bundle.putSerializable("FILEJSON", fileJson)
                intent.putExtras(bundle)
                startActivityForResult(intent, REQUEST_HOME_TO_CREATE_OR_UPDATE)
            }
            btn_ReadData -> {
                readData()

            }
        }
    }

    private fun readData() {
        tv_ResponseCode.text = "response codee: "
        RetrofitClient.instance.getEmployees().enqueue(object : Callback<FileJson> {
            override fun onFailure(call: Call<FileJson>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "co van de ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<FileJson>, response: Response<FileJson>) {
                tv_ResponseCode.text = "response code: " + response.code().toString()
                fileJson = response.body()
                if (response.isSuccessful) {
                    // Toast.makeText(applicationContext, "ok ${fileJson?.data}", Toast.LENGTH_SHORT).show()
                    list = fileJson?.data as ArrayList<Employee>?
                    employeeAdapter?.setList(list!!)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "lam gi co gi," + response.code().toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    override fun doSomeThingOnClick(employee: Employee) {
        var intent = Intent(this, DetailActivity::class.java)
        var bundle = Bundle()
        bundle.putSerializable("EMPLOYEE", employee)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun doSomeThingOnLongClick(employee: Employee) {
        val dialog = MaterialDialog(this)
            .noAutoDismiss()
            .customView(R.layout.dialog_select)

        dialog.btn_CancelDialog.setOnClickListener() {
            dialog.dismiss()
        }
        dialog.btn_Delete.setOnClickListener() {
            RetrofitClient.instance.deleteEmployee(employee.id!!).enqueue(object: Callback<Unit>{
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(applicationContext, "fail ${t.message}", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if(response.isSuccessful){
                        Toast.makeText(applicationContext, "xoa thanh cong", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, "k xoa duoc ${response.message()} ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

            })
            dialog.dismiss()
        }
        dialog.btn_Change.setOnClickListener() {
            var intent = Intent(this, CreateAndUpdateEmployee::class.java)
            var bundle = Bundle()
            bundle.putSerializable("EMPLOYEE2", employee)
            intent.putExtras(bundle)
            intent.putExtra("BUTTON", 1)
            startActivityForResult(intent, REQUEST_HOME_TO_CREATE_OR_UPDATE)
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_HOME_TO_CREATE_OR_UPDATE) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Saved successfully!", Toast.LENGTH_SHORT).show()
                readData()
            }
        }
    }
}