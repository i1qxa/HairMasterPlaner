package com.example.hairmasterplaner.ui.jobBodyList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.hairmasterplaner.R
import com.example.hairmasterplaner.domain.jobElement.JobElementItem

class MySpinnerAdapter(context: Context, listCustomers: List<JobElementItem>) :
    ArrayAdapter<JobElementItem>(context, 0, listCustomers) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position,convertView,parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position,convertView,parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup):View{
        val item = getItem(position)
        val view = LayoutInflater.from(context).inflate(
            R.layout.spinner_item,
            parent,
            false)
        view.findViewById<TextView>(R.id.tvSpinnerItem).text = item?.name
        return view
    }
}