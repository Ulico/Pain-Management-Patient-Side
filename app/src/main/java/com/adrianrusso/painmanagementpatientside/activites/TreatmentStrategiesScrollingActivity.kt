package com.adrianrusso.painmanagementpatientside.activites

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.adrianrusso.painmanagementpatientside.adapters.MedicationPagerAdapter
import com.adrianrusso.painmanagementpatientside.databinding.ActivityTreatmentStrategiesScrollingBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser
import com.adrianrusso.painmanagementpatientside.models.Submission
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
        binding.content.horizontalScrollView?.adapter = MedicationPagerAdapter(this)

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

    override fun onResume() {
        super.onResume()
        binding.content.horizontalScrollView?.adapter?.notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onSubmit(view: View) {
        val s = Submission()

        s.medicationTaken = binding.content.yes?.isChecked

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
}