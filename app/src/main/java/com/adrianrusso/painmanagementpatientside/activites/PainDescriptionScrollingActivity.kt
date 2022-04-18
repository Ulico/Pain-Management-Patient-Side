package com.adrianrusso.painmanagementpatientside.activites

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.adrianrusso.painmanagementpatientside.R
import com.adrianrusso.painmanagementpatientside.databinding.ActivityPainDescriptionScrollingBinding
import com.adrianrusso.painmanagementpatientside.models.AppUser
import com.adrianrusso.painmanagementpatientside.models.Submission
import com.google.android.material.slider.Slider
import java.util.*


class PainDescriptionScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPainDescriptionScrollingBinding

    private lateinit var painDescriptorCheckboxes: List<CheckBox?>
    private lateinit var painTriggersCheckboxes: List<CheckBox?>
    private lateinit var painTimeOfDayCheckboxes: List<CheckBox?>
    private lateinit var painLocationOfPatientsCheckboxes: List<CheckBox?>
    private lateinit var painOtherSymptomsCheckboxes: List<CheckBox?>
    private var toggled: BooleanArray = booleanArrayOf(false, false, false, false, false, false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPainDescriptionScrollingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title

        painDescriptorCheckboxes = listOf(
            binding.content.pounding,
            binding.content.dull,
            binding.content.numbness,
            binding.content.throbbing,
            binding.content.sharp,
            binding.content.pressure,
            binding.content.aching,
            binding.content.localized,
            binding.content.tingling,
            binding.content.burning,
            binding.content.pinching
        )

        painTriggersCheckboxes = listOf(
            binding.content.stress,
            binding.content.clothing,
            binding.content.negativeMood,
            binding.content.anxiety,
            binding.content.weather,
            binding.content.allergicReaction,
            binding.content.caffeine,
            binding.content.smoking,
            binding.content.skippedMeal,
            binding.content.alcohol,
            binding.content.skippedMedication,
            binding.content.sitting,
            binding.content.exercise,
            binding.content.tooMuchSleep,
            binding.content.standing,
            binding.content.inactivity,
            binding.content.lackOfSleep,
            binding.content.walking,
            binding.content.interruptedSleep
        )

        painTimeOfDayCheckboxes = listOf(
            binding.content.morning,
            binding.content.midday,
            binding.content.evening,
            binding.content.atNight
        )

        painLocationOfPatientsCheckboxes = listOf(
            binding.content.atWork,
            binding.content.atHome,
            binding.content.inBed,
            binding.content.inTransit,
            binding.content.outside,
            binding.content.atSchool,
            binding.content.socialSetting
        )

        painOtherSymptomsCheckboxes = listOf(
            binding.content.nausea,
            binding.content.diarrhea,
            binding.content.worsePainIfMoving,
            binding.content.lightHeaded,
            binding.content.constipation
        )

        binding.content.painSlider?.addOnChangeListener(Slider.OnChangeListener { _, value, _ ->
            "${value.toInt()}/10".also {
                binding.content.painLevelRatingText!!.text = it
            }
        })

        binding.content.painSlider2?.addOnChangeListener(Slider.OnChangeListener { _, value, _ ->
            "${value.toInt()}/10".also {
                binding.content.painLevelRatingText2!!.text = it
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onSubmit(view: View) {
        val s = Submission()
        s.painInterferenceWithGeneralActivities = binding.content.painSlider?.value?.toInt()
        s.painInterferenceWithEnjoyment = binding.content.painSlider2?.value?.toInt()

        val tempList: MutableList<String> = mutableListOf()

        painDescriptorCheckboxes.forEach { c ->
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
            s.painDescriptors = tempList.toList()
        Log.d("TEST", s.painDescriptors.toString())
        tempList.clear()

        painTriggersCheckboxes.forEach { c ->
            run {
                if (c != null) {
                    if (c.isChecked) {
                        tempList.add(c.text.toString().lowercase(Locale.getDefault()))
                    }
                }
            }
        }
        if (tempList.isNotEmpty())
            s.painTriggers = tempList.toList()
        tempList.clear()

        painTimeOfDayCheckboxes.forEach { c ->
            run {
                if (c != null) {
                    if (c.isChecked) {
                        tempList.add(c.text.toString().lowercase(Locale.getDefault()))
                    }
                }
            }
        }
        if (tempList.isNotEmpty())
            s.painTimeOfDay = tempList.toList()
        tempList.clear()

        painLocationOfPatientsCheckboxes.forEach { c ->
            run {
                if (c != null) {
                    if (c.isChecked) {
                        tempList.add(c.text.toString().lowercase(Locale.getDefault()))
                    }
                }
            }
        }
        if (tempList.isNotEmpty())
            s.painLocationOfPatient = tempList.toList()
        tempList.clear()

        painOtherSymptomsCheckboxes.forEach { c ->
            run {
                if (c != null) {
                    if (c.isChecked) {
                        tempList.add(c.text.toString().lowercase(Locale.getDefault()))
                    }
                }
            }
        }
        if (tempList.isNotEmpty())
            s.painOtherSymptoms = tempList.toList()
        tempList.clear()

        if (!binding.content.painDurationHours?.text.isNullOrEmpty() && !binding.content.painDurationMinutes?.text.isNullOrEmpty()) {
            val hours = binding.content.painDurationHours?.text.toString().toInt()
            val minutes = binding.content.painDurationMinutes?.text.toString().toInt()
            if (hours != 0 || minutes != 0)
                s.painDuration = hours * 60 + minutes
        }


        AppUser.sendSubmission(s, false)
        finish()
    }

    fun onDescribePain(view: View) {
        if (toggled[0]) {
            binding.content.describePainGrid?.visibility = View.GONE
        } else {
            binding.content.describePainGrid?.visibility = View.VISIBLE
        }
        toggled[0] = !toggled[0]
    }

    fun onPainTriggers(view: View) {
        if (toggled[1]) {
            binding.content.painTriggersGrid?.visibility = View.GONE
        } else {
            binding.content.painTriggersGrid?.visibility = View.VISIBLE
        }
        toggled[1] = !toggled[1]
    }

    fun onPainDuration(view: View) {
        if (toggled[2]) {
            binding.content.painDurationLayout?.visibility = View.GONE
        } else {
            binding.content.painDurationLayout?.visibility = View.VISIBLE
        }
        toggled[2] = !toggled[2]
    }

    fun onTimeOfDay(view: View) {
        if (toggled[3]) {
            binding.content.timeOfDayGrid?.visibility = View.GONE
        } else {
            binding.content.timeOfDayGrid?.visibility = View.VISIBLE
        }
        toggled[3] = !toggled[3]
    }

    fun onLocation(view: View) {
        if (toggled[4]) {
            binding.content.locationGrid?.visibility = View.GONE
        } else {
            binding.content.locationGrid?.visibility = View.VISIBLE
        }
        toggled[4] = !toggled[4]
    }

    fun onOtherSymptoms(view: View) {
        if (toggled[5]) {
            binding.content.otherSymptomsGrid?.visibility = View.GONE
        } else {
            binding.content.otherSymptomsGrid?.visibility = View.VISIBLE
        }
        toggled[5] = !toggled[5]
    }
}