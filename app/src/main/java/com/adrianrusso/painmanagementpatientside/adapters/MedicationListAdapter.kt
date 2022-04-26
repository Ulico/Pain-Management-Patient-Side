package com.adrianrusso.painmanagementpatientside.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.MeasureSpec
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.adrianrusso.painmanagementpatientside.R
import com.adrianrusso.painmanagementpatientside.models.AppUser


class MedicationListAdapter(val context: Context) :
    BaseAdapter() {


    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return AppUser.medications.size
    }

//    fun getListNames(): List<String> {
//        return list.map { it.name }
//    }

//    fun getNameString(): String {
//        var toReturn = ""
//        list.forEach { toReturn += it.name + ", " }
//        Log.d("test", toReturn.dropLast(2))
//        return toReturn.dropLast(2)
//    }

    override fun getItem(position: Int): Any {
        return AppUser.medications[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = convertView ?: inflater.inflate(R.layout.list_item_medication, parent, false)
        val nameTextView = rowView.findViewById(R.id.medicationText) as TextView
        val doseTextView = rowView.findViewById(R.id.doseText) as TextView
        nameTextView.text = AppUser.medications[position].name
        doseTextView.text = AppUser.medications[position].dose

        return rowView
    }



}