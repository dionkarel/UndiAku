package com.example.undiaku.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.undiaku.databinding.FragmentSetNumberPickerBinding
import com.example.undiaku.model.MinMaxModel
import com.example.undiaku.model.PreferencesManager

class SetNumberPickerFragment : DialogFragment() {

    private var _binding: FragmentSetNumberPickerBinding? = null
    private val binding get() = _binding!!

    private lateinit var minMaxModel: MinMaxModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetNumberPickerBinding.inflate(inflater, container, false)
        val view = binding.root

        minMaxModel = (targetFragment as NumberPickerFragment).minMaxModel

        binding.edtSetMinNumber.hint = minMaxModel.minNumber.toString()
        binding.edtSetMaxNumber.hint = minMaxModel.maxNumber.toString()

        setActionForButton()

        return view
    }

    private fun setActionForButton() {
        binding.btSetNumberPicker.setOnClickListener {
            minMaxModel.minNumber = binding.edtSetMinNumber.text.toString().toInt()
            minMaxModel.maxNumber = binding.edtSetMaxNumber.text.toString().toInt()

            val preferencesManager = PreferencesManager(requireContext())
            preferencesManager.saveMinMaxModel(minMaxModel)

            (targetFragment as NumberPickerFragment).updateMinMaxTextViews()

            dialog?.dismiss()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
