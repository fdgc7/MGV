package com.fdanielgarcia.mygermanvocabulary.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fdanielgarcia.mygermanvocabulary.R
import com.fdanielgarcia.mygermanvocabulary.data.VerbList
import com.fdanielgarcia.mygermanvocabulary.databinding.FragmentShowVerbBinding
import com.fdanielgarcia.mygermanvocabulary.domain.Verb
import com.fdanielgarcia.mygermanvocabulary.use_cases.VocabularyManagement

class ShowVerbFragment : Fragment() {
    val vocabularyManagement by lazy { VocabularyManagement() }
    private lateinit var verbList: VerbList
    private lateinit var verb: Verb

    private var _binding: FragmentShowVerbBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        verbList = arguments?.getParcelable<VerbList>("List")!!

        _binding = FragmentShowVerbBinding.inflate(inflater, container, false)
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
            showNextInfinitive()
        }

        binding.constraintLayoutAll.setOnTouchListener(object :
            OnSwipeTouchListener(requireContext()) {
            override fun onSwipeLeft() {
                showNextInfinitive()
            }

            override fun onSwipeBottom() {
                showMeaning()
            }

            override fun onClick() {
                showMeaning()
            }
        })

        binding.buttonQuit.setOnClickListener {
            findNavController().navigate(R.id.action_ShowVerbFragment_to_TestVocabularyFragment)

        }

        showNextInfinitive()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showNextInfinitive() {
        verb = verbList.randomElement()
        binding.textViewInfinitive.text = vocabularyManagement.removeVerbHint(verb.infinitive)
        binding.textViewPresent.text = ""
        binding.textViewPast.text = ""
        binding.textViewPerfect.text = ""
        binding.textViewMeaning.text = ""
    }

    fun showMeaning() {
        binding.textViewInfinitive.text = verb.infinitive
        binding.textViewPresent.text = verb.present
        binding.textViewPast.text = verb.past
        binding.textViewPerfect.text = verb.perfect
        binding.textViewMeaning.text = verb.meaning
    }
}