package com.example.testretrofit2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Employee
import java.util.zip.Inflater


class EmployeeAdapter(var mContext: Context) : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {
    private var list: ArrayList<Employee>? = ArrayList<Employee>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.tv_Text)
    }

    fun setList(list: ArrayList<Employee>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_employee, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = "ID : " + list?.get(position)?.id + ", name: " + list?.get(position)
            ?.employeeName + ", salary: " + list?.get(position)
            ?.employeeSalary + ", age: " + list?.get(position)
            ?.employeeAge + ", img: " + list?.get(position)
            ?.profileImage

    }
}