package com.fdanielgarcia.mygermanvocabulary.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fdanielgarcia.mygermanvocabulary.R
import com.fdanielgarcia.mygermanvocabulary.data.*
import com.fdanielgarcia.mygermanvocabulary.databinding.FragmentShowOtherBinding
import com.fdanielgarcia.mygermanvocabulary.domain.*

class ShowOtherFragment : Fragment() {
    private lateinit var vocabularyList: VocabularyList
    private lateinit var vocabulary: Vocabulary

    private var _binding: FragmentShowOtherBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        vocabularyList = arguments?.getParcelable<VocabularyList>("List")!!

        _binding = FragmentShowOtherBinding.inflate(inflater, container, false)
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
            findNavController().navigate(R.id.action_ShowOtherFragment_to_TestVocabularyFragment)

        }

        when {
            vocabularyList is AdjectiveList -> {
                binding.textViewTitleOther.text = activity?.resources?.getString(R.string.adjective)
            }
            vocabularyList is AdverbList -> {
                binding.textViewTitleOther.text = activity?.resources?.getString(R.string.adverb)
            }
            vocabularyList is ConjunctionList -> {
                binding.textViewTitleOther.text = activity?.resources?.getString(R.string.conjunction)
            }
            vocabularyList is PrepositionList -> {
                binding.textViewTitleOther.text = activity?.resources?.getString(R.string.preposition)
            }
        }

        showNextName()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showNextName() {
        when {
            vocabularyList is AdjectiveList -> {
                vocabulary = vocabularyList.randomElement()
                binding.textViewOther.text = (vocabulary as Adjective).adjective
                binding.textViewMeaning.text = ""
            }
            vocabularyList is AdverbList -> {
                vocabulary = vocabularyList.randomElement()
                binding.textViewOther.text = (vocabulary as Adverb).adverb
                binding.textViewMeaning.text = ""
            }
            vocabularyList is ConjunctionList -> {
                vocabulary = vocabularyList.randomElement()
                binding.textViewOther.text = (vocabulary as Conjunction).conjunction
                binding.textViewMeaning.text = ""
            }
            vocabularyList is PrepositionList -> {
                vocabulary = vocabularyList.randomElement()
                binding.textViewOther.text = (vocabulary as Preposition).preposition
                binding.textViewMeaning.text = ""
            }
        }
    }

    fun showMeaning() {
        when {
            vocabularyList is AdjectiveList -> {
                binding.textViewMeaning.text = (vocabulary as Adjective).meaning
            }
            vocabularyList is AdverbList -> {
                binding.textViewMeaning.text = (vocabulary as Adverb).meaning
            }
            vocabularyList is ConjunctionList -> {
                binding.textViewMeaning.text = (vocabulary as Conjunction).meaning
            }
            vocabularyList is PrepositionList -> {
                binding.textViewMeaning.text = (vocabulary as Preposition).meaning
            }
        }
    }
}