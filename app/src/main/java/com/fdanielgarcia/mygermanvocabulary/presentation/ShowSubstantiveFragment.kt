package com.fdanielgarcia.mygermanvocabulary.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fdanielgarcia.mygermanvocabulary.R
import com.fdanielgarcia.mygermanvocabulary.data.SubstantiveList
import com.fdanielgarcia.mygermanvocabulary.databinding.FragmentShowSubstantiveBinding
import com.fdanielgarcia.mygermanvocabulary.domain.Substantive

class ShowSubstantiveFragment : Fragment() {
    private lateinit var substantiveList: SubstantiveList
    private lateinit var substantive: Substantive

    private var _binding: FragmentShowSubstantiveBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        substantiveList = arguments?.getParcelable<SubstantiveList>("List")!!

        _binding = FragmentShowSubstantiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonShow.setOnClickListener {
            showMeaning()
        }

        binding.constraintLayoutAll.setOnClickListener {
            showMeaning()
        }

        binding.buttonNext.setOnClickListener {
            showNextName()
        }

        binding.constraintLayoutAll.setOnTouchListener(object :
            OnSwipeTouchListener(requireContext()) {
            override fun onSwipeLeft() {
                showNextName()
            }

            override fun onSwipeBottom() {
                showMeaning()
            }

            override fun onClick() {
                showMeaning()
            }
        })

        binding.buttonQuit.setOnClickListener {
            findNavController().navigate(R.id.action_ShowSubstantiveFragment_to_TestVocabularyFragment)

        }

        showNextName()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showNextName() {
        substantive = substantiveList.randomElement()
        binding.textViewName.setTextColor(getColor(requireContext(), R.color.unidentified))
        binding.textViewMeaning.setTextColor(getColor(requireContext(), R.color.unidentified))
        binding.textViewName.text = substantive.name
        binding.textViewMeaning.text = ""
    }

    fun showMeaning() {
        binding.textViewName.setTextColor(getColor(requireContext(), substantive.gender.color))
        binding.textViewMeaning.setTextColor(getColor(requireContext(), substantive.gender.color))
        binding.textViewName.text = substantive.gender.article + " " + substantive.name
        binding.textViewMeaning.text = substantive.meaning
    }
}