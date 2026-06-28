package com.fdanielgarcia.mygermanvocabulary.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.fdanielgarcia.mygermanvocabulary.MGVApplication
import com.fdanielgarcia.mygermanvocabulary.R
import com.fdanielgarcia.mygermanvocabulary.data.*
import com.fdanielgarcia.mygermanvocabulary.databinding.FragmentShowOtherBinding
import com.fdanielgarcia.mygermanvocabulary.domain.*
import com.fdanielgarcia.mygermanvocabulary.use_cases.ListManagement
import kotlinx.coroutines.launch

class ShowOtherFragment : Fragment() {
    val listManagement by lazy { ListManagement(requireActivity() as Activity) }
    val exampleManagement by lazy { (requireActivity().application as MGVApplication).exampleManagement }
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

        val vocabularyType = arguments?.getString("vocabularyType") ?: ""
        vocabularyList = listManagement.loadList(vocabularyType)

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

        binding.buttonExample.visibility = View.INVISIBLE
        binding.buttonExample.setOnClickListener {
            binding.buttonExample.isEnabled = false
            lifecycleScope.launch {
                val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
                val minWords = prefs.getString("preference_example_min_words", "5")?.toIntOrNull() ?: 5
                val maxWords = prefs.getString("preference_example_max_words", "20")?.toIntOrNull() ?: 20
                val word = when {
                    vocabularyList is AdjectiveList   -> (vocabulary as Adjective).adjective
                    vocabularyList is AdverbList      -> (vocabulary as Adverb).adverb
                    vocabularyList is ConjunctionList -> (vocabulary as Conjunction).conjunction
                    vocabularyList is PrepositionList -> (vocabulary as Preposition).preposition
                    vocabularyList is PronounList     -> (vocabulary as Pronoun).pronoun
                    else -> ""
                }
                val wordType = when {
                    vocabularyList is AdjectiveList   -> "adjective"
                    vocabularyList is AdverbList      -> "adverb"
                    vocabularyList is ConjunctionList -> "conjunction"
                    vocabularyList is PrepositionList -> "preposition"
                    vocabularyList is PronounList     -> "pronoun"
                    else -> ""
                }
                val result = exampleManagement.generateExample(word, wordType, minWords, maxWords)
                result.fold(
                    onSuccess = { sentence ->
                        AlertDialog.Builder(requireContext())
                            .setTitle(getString(R.string.example_dialog_title))
                            .setMessage(sentence)
                            .setPositiveButton(android.R.string.ok, null)
                            .show()
                    },
                    onFailure = {
                        val msg = when (it.message) {
                            "UNAVAILABLE" -> getString(R.string.example_unavailable)
                            "DOWNLOADING" -> getString(R.string.example_downloading)
                            else -> it.message
                        }
                        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
                    }
                )
                binding.buttonExample.isEnabled = true
            }
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
            vocabularyList is PronounList -> {
                binding.textViewTitleOther.text = activity?.resources?.getString(R.string.pronoun)
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
            vocabularyList is PronounList -> {
                vocabulary = vocabularyList.randomElement()
                binding.textViewOther.text = (vocabulary as Pronoun).pronoun
                binding.textViewMeaning.text = ""
            }
        }
        binding.buttonExample.visibility = View.INVISIBLE
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
            vocabularyList is PronounList -> {
                binding.textViewMeaning.text = (vocabulary as Pronoun).meaning
            }
        }
        binding.buttonExample.visibility = View.VISIBLE
    }
}
