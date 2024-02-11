package com.example.undiaku.ui.fragment

import android.animation.IntEvaluator
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.undiaku.databinding.FragmentNumberPickerBinding
import com.example.undiaku.model.MinMaxModel
import com.example.undiaku.model.PreferencesManager
import kotlin.random.Random

@Suppress("DEPRECATION")
class NumberPickerFragment : Fragment() {

    private var _binding: FragmentNumberPickerBinding? = null
    private val binding get() = _binding!!

    lateinit var minMaxModel: MinMaxModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNumberPickerBinding.inflate(inflater, container, false)
        val view = binding.root

        val preferencesManager = PreferencesManager(requireContext())
        minMaxModel = preferencesManager.getMinMaxModel()

        binding.tvMinNumber.text = minMaxModel.minNumber.toString()
        binding.tvMaxNumber.text = minMaxModel.maxNumber.toString()
        binding.tvResultNumber.text = minMaxModel.resultNumber.toString()

        setActionForButton()
        return view
    }

    private fun setActionForButton() {
        binding.fbSetNumber.setOnClickListener {
            val setNumberPickerFragment = SetNumberPickerFragment()
            setNumberPickerFragment.setTargetFragment(this, 0)
            setNumberPickerFragment.show(parentFragmentManager, "SetNumberPickerFragment")
        }

        binding.btGetNumber.setOnClickListener {
            if (minMaxModel.minNumber <= minMaxModel.maxNumber) {
                getNumber()
            } else {
                Toast.makeText(requireContext(), "Invalid range", Toast.LENGTH_SHORT).show()
            }
        }

        binding.fbResetNumber.setOnClickListener {
            val defaultNumber = 0

            minMaxModel = MinMaxModel(defaultNumber, defaultNumber, defaultNumber)

            val preferencesManager = PreferencesManager(requireContext())
            preferencesManager.saveMinMaxModel(minMaxModel)

            val targetFragment = targetFragment
            if (targetFragment is NumberPickerFragment) {

                targetFragment.updateMinMaxTextViews()
            }
            updateMinMaxTextViews()
        }
    }

    private fun getNumber() {
        val randomNumber = Random.nextInt(minMaxModel.minNumber, minMaxModel.maxNumber + 1)
        val resultTextView = binding.tvResultNumber

        val animator = ValueAnimator.ofObject(
            IntEvaluator(),
            0,
            randomNumber
        )
        animator.duration = 2000

        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            resultTextView.text = animatedValue.toString()
        }

        animator.start()
    }

    fun updateMinMaxTextViews() {
        binding.tvMinNumber.text = minMaxModel.minNumber.toString()
        binding.tvMaxNumber.text = minMaxModel.maxNumber.toString()
        binding.tvResultNumber.text = minMaxModel.resultNumber.toString()
    }


}