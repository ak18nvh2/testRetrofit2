package com.example.testretrofit2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.dialog_processbar.*
import kotlinx.android.synthetic.main.dialog_select.*
import kotlinx.android.synthetic.main.dialog_select.tv_TitleOfCustomDialogConfirm
import kotlinx.android.synthetic.main.dialog_yes_no.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(), View.OnClickListener,
    ContactAdapter.IRecyclerViewWithHomeActivity {
    private var list: ArrayList<Contact>? = ArrayList<Contact>()
    private val REQUEST_HOME_TO_CREATE_OR_UPDATE = 10
    var employeeAdapter: ContactAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var rvEmployees: RecyclerView = findViewById(R.id.rv_Employees)
        employeeAdapter = ContactAdapter(this, this)
        rvEmployees.setHasFixedSize(true)
        rvEmployees.layoutManager = LinearLayoutManager(this)

        list?.let { employeeAdapter?.setList(it) }
        rvEmployees.adapter = employeeAdapter
        btn_ReadData.setOnClickListener(this)
        btn_CreateNewEmployee.setOnClickListener(this)
       // readData()
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_CreateNewEmployee -> {
                var intent = Intent(this, CreateAndUpdateEmployee::class.java)
                intent.putExtra("BUTTON", 2)
                startActivityForResult(intent, REQUEST_HOME_TO_CREATE_OR_UPDATE)
            }
            btn_ReadData -> {
                readData()

            }
        }
    }

    private fun readData() {
            this.list?.clear()
            tv_ResponseCode.text = "Loading: "
        val dialog = MaterialDialog(this)
            .noAutoDismiss()
            .customView(R.layout.dialog_processbar)
        dialog.setCancelable(false)
        var callGet = RetrofitClient.instance.getContacts()
        dialog.btn_CancelUpdate.setOnClickListener(){
            callGet.cancel()
            dialog.dismiss()
        }
        dialog.show()
            callGet.enqueue(object : Callback<FJson> {

                override fun onFailure(call: Call<FJson>, t: Throwable) {
                    dialog.dismiss()
                    Toast.makeText(
                        applicationContext,
                        "co van de ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    tv_ResponseCode.text = "onFailure: " + t.message
                    Log.d("AAAAARead Failure", t.message)



                }

                override fun onResponse(call: Call<FJson>, response: Response<FJson>) {
                    dialog.dismiss()
                    tv_ResponseCode.text = "response code: " + response.code().toString()
                    if (response.isSuccessful) {
                        list = response.body()?.contacts as ArrayList<Contact>?
                        employeeAdapter?.setList(list!!)

                    } else {
                        Toast.makeText(
                            applicationContext,
                            "lam gi co gi," + response.code().toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("AAAAARead response !", response.message() + response.code())
                    }
                }
            }

            )



    }
//
    override fun doSomeThingOnClick(contact: Contact) {
//        var intent = Intent(this, DetailActivity::class.java)
//        var bundle = Bundle()
//        bundle.putSerializable("EMPLOYEE", employee)
//        intent.putExtras(bundle)
//        startActivity(intent)
    Toast.makeText(this, contact.email, Toast.LENGTH_LONG).show()
    }

    override fun doSomeThingOnLongClick(contact: Contact) {
        Toast.makeText(this, contact.contactId, Toast.LENGTH_LONG).show()
    }
//        val dialogSelect = MaterialDialog(this)
//            .noAutoDismiss()
//            .customView(R.layout.dialog_select)
//
//        dialogSelect.setCancelable(false)
//        dialogSelect.show()
//        dialogSelect.btn_CancelDialog.setOnClickListener() {
//            dialogSelect.dismiss()
//        }
//        dialogSelect.btn_Delete.setOnClickListener() {
//          // window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
//            dialogSelect.dismiss()
//            val dialogYesNo = MaterialDialog(this)
//                .noAutoDismiss()
//                .customView(R.layout.dialog_yes_no)
//            dialogYesNo.show()
//            dialogYesNo.setCancelable(false)
//            dialogYesNo.tv_TitleOfCustomDialogConfirm.text = "Are you sure delete this?"
//            dialogYesNo.btn_CancelDialogConfirm.setOnClickListener() {
//                dialogYesNo.dismiss()
//            }
//            dialogYesNo.btn_AcceptDiaLogConFirm.setOnClickListener(){
//                dialogYesNo.dismiss()
//                val callDelete =  RetrofitClient.instance.deleteEmployee(employee.id!!)
//                val dialog = MaterialDialog(this)
//                    .noAutoDismiss()
//                    .customView(R.layout.dialog_processbar)
//                dialog.show()
//                dialog.setCancelable(false)
//                dialog.btn_CancelUpdate.setOnClickListener(){
//                    callDelete.cancel()
//                    dialog.dismiss()
//                }
//                callDelete.enqueue(object: Callback<FileJson3>{
//
//                    override fun onFailure(call: Call<FileJson3>, t: Throwable) {
//                        dialog.dismiss()
//                       // window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
//                        Toast.makeText(applicationContext, "fail ${t.message}", Toast.LENGTH_SHORT).show()
//                        Log.d("AAAAAADelete Failure",t.message )
//
//                    }
//
//                    override fun onResponse(call: Call<FileJson3>, response: Response<FileJson3>) {
//                        dialog.dismiss()
//                        if(response.isSuccessful){
//                            Log.d("AAAAAADelete Successful",response.body()?.message )
//                           // window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
//                            Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT).show()
//                            readData()
//                        } else {
//                            Log.d("AAAAAADelete response !",response.message())
//                            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
//                            Toast.makeText(applicationContext, "k xoa duoc ${response.message()} ${response.code()}", Toast.LENGTH_SHORT).show()
//                        }
//
//                    }
//
//                })
//
//
//            }
//
//        }
//        dialogSelect.btn_Change.setOnClickListener() {
//            var intent = Intent(this, CreateAndUpdateEmployee::class.java)
//            var bundle = Bundle()
//            bundle.putSerializable("EMPLOYEE2", employee)
//            intent.putExtras(bundle)
//            intent.putExtra("BUTTON", 1)
//            startActivityForResult(intent, REQUEST_HOME_TO_CREATE_OR_UPDATE)
//            dialogSelect.dismiss()
//        }
//
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_HOME_TO_CREATE_OR_UPDATE) {
//            if (resultCode == Activity.RESULT_OK) {
//                Toast.makeText(this, "Saved successfully!", Toast.LENGTH_SHORT).show()
//                readData()
//            }
//        }
//    }
}