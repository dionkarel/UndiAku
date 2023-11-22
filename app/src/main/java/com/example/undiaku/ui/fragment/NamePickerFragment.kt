package com.example.undiaku.ui.fragment

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.undiaku.adapter.NameAdapter
import com.example.undiaku.databinding.FragmentNamePickerBinding
import com.example.undiaku.model.ListNameModel

class NamePickerFragment : Fragment() {

    private var _binding: FragmentNamePickerBinding? = null
    private val binding get() = _binding!!

    private val names = ArrayList<ListNameModel>()
    private lateinit var nameAdapter: NameAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNamePickerBinding.inflate(inflater, container, false)
        val view = binding.root

        nameAdapter = NameAdapter()
        binding.rvListName.layoutManager = GridLayoutManager(activity, 4)
        binding.rvListName.adapter = nameAdapter

        setActionForButton()

        return view
    }

    private fun setActionForButton() {
        binding.btAddName.setOnClickListener {
            val newName = binding.edtAddName.text.toString()
            if (newName.isNotEmpty()) {
                names.add(ListNameModel(newName))
                nameAdapter.submitList(ArrayList(names))
                binding.edtAddName.text.clear()
            } else {
                Toast.makeText(this.activity, "Please insert the name", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btGetName.setOnClickListener {
            if (names.isNotEmpty()) {
                val newData = ArrayList(names.shuffled())

                val animator = ValueAnimator.ofFloat(0f, 1f)
                animator.duration = 2000
                animator.addUpdateListener { animation ->
                    val fraction = animation.animatedFraction
                    val shuffledData = ArrayList(newData)
                    for (i in 0 until newData.size) {
                        val newIndex = (fraction * i + (1 - fraction) * (newData.size - 1)).toInt()
                        shuffledData[i] = newData[newIndex]
                    }
                    nameAdapter.submitList(shuffledData)
                    binding.tvResultName.text = shuffledData[0].name
                }
                animator.start()
            } else {
                Toast.makeText(this.activity, "List name is empty", Toast.LENGTH_SHORT).show()
            }
        }

        binding.fbResetListName.setOnClickListener {
            if (names.isNotEmpty()) {
                names.clear()
                nameAdapter.submitList(ArrayList(names))
                binding.tvResultName.text = "" // Reset the displayed name
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}