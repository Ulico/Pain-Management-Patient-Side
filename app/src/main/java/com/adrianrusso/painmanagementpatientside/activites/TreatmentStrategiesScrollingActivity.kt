package com.adrianrusso.painmanagementpatientside.activites

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setMargins
import com.adrianrusso.painmanagementpatientside.adapters.MedicationListAdapter
import com.adrianrusso.painmanagementpatientside.adapters.MedicationPagerAdapter
import com.adrianrusso.painmanagementpatientside.databinding.ActivityTreatmentStrategiesScrollingBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser
import com.adrianrusso.painmanagementpatientside.models.Submission
import io.realm.mongodb.App
import java.util.*

class TreatmentStrategiesScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTreatmentStrategiesScrollingBinding

    private lateinit var nonMedicationStrategiesCheckboxes: List<CheckBox?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTreatmentStrategiesScrollingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.toolbarLayout.title = title
//        binding.content.horizontalScrollView?.adapter = MedicationPagerAdapter(this)

        binding.content.listView?.adapter = MedicationListAdapter(this)
//        val temp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150 * AppUser.medications.size)
//        temp.setMargins(20)
//        binding.content.listView?.layoutParams = temp

//        if (AppUser.medications.isEmpty())
//            binding.content.horizontalScrollView?.visibility = View.GONE

        nonMedicationStrategiesCheckboxes = listOf(
            binding.content.sleep,
            binding.content.journaled,
            binding.content.physicalTherapy,
            binding.content.shower,
            binding.content.yoga,
            binding.content.painTherapy,
            binding.content.meditation,
            binding.content.heatPack,
            binding.content.relaxingHobby,
            binding.content.exercise,
            binding.content.icePack
        )
    }

    fun onSubmit(view: View) {
        val s = Submission()


        val tempList: MutableList<String> = mutableListOf()

        nonMedicationStrategiesCheckboxes.forEach { c ->
            run {
                if (c != null) {
                    if (c.isChecked) {
                        tempList.add(c.text.toString().lowercase(Locale.getDefault()))
                    }
                }
            }
        }
        Log.d("TEST", tempList.toString())
        if (tempList.isNotEmpty())
            s.nonMedicationStrategies = tempList.toList()

        AppUser.sendSubmission(s, false)

        finish()

    }

    override fun onResume() {
        super.onResume()
        (binding.content.listView?.adapter as MedicationListAdapter).notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        val temp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150 * AppUser.medications.size)
        temp.setMargins(20)
        binding.content.listView?.layoutParams = temp
    }

    fun onAdd(view: View) {
        Log.d("Models", AppUser.medications.toString())
        startActivity(Intent(this, AddMedicationActivity::class.java))
    }
}