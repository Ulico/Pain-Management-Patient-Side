package com.adrianrusso.painmanagementpatientside.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.adrianrusso.painmanagementpatientside.R
import com.adrianrusso.painmanagementpatientside.models.AppUser
import com.adrianrusso.painmanagementpatientside.models.Medication


class GraphPagerAdapter(private val mContext: Context) : PagerAdapter() {


    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val medication: Medication = AppUser.medications[position]
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.pager_item_medication, collection, false)
        "Medicine: ${medication.name}".also {
            (view.findViewById(R.id.nameText) as TextView).text = it
        }
        "Dose: ${medication.dose}".also { (view.findViewById(R.id.doseText) as TextView).text = it }
        "Instruction: ${medication.instruction}".also {
            (view.findViewById(R.id.instructionText) as TextView).text = it
        }
        collection.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }


    override fun getCount(): Int {
        return AppUser.medications.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}