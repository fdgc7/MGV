package com.fdanielgarcia.mygermanvocabulary.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.fdanielgarcia.mygermanvocabulary.MGVApplication
import com.fdanielgarcia.mygermanvocabulary.R
import com.fdanielgarcia.mygermanvocabulary.data.SubstantiveList
import com.fdanielgarcia.mygermanvocabulary.databinding.FragmentShowSubstantiveBinding
import com.fdanielgarcia.mygermanvocabulary.domain.Substantive
import com.fdanielgarcia.mygermanvocabulary.use_cases.ListManagement
import com.fdanielgarcia.mygermanvocabulary.use_cases.VocabularyManagement
import kotlinx.coroutines.launch

class ShowSubstantiveFragment : Fragment() {
    val vocabularyManagement by lazy { VocabularyManagement() }
    val listManagement by lazy { ListManagement(requireActivity() as Activity) }
    val exampleManagement by lazy { (requireActivity().application as MGVApplication).exampleManagement }
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

        val vocabularyType = arguments?.getString("vocabularyType") ?: "AllSubstantives"
        substantiveList = listManagement.loadList(vocabularyType) as SubstantiveList

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

        binding.buttonExample.visibility = View.INVISIBLE
        binding.buttonExample.setOnClickListener {
            binding.buttonExample.isEnabled = false
            lifecycleScope.launch {
                val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
                val minWords = prefs.getString("preference_example_min_words", "5")?.toIntOrNull() ?: 5
                val maxWords = prefs.getString("preference_example_max_words", "20")?.toIntOrNull() ?: 20
                val word = vocabularyManagement.removeSubstantivePlural(substantive.name)
                val result = exampleManagement.generateExample(word, "noun", minWords, maxWords)
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
        binding.textViewName.text = vocabularyManagement.removeSubstantivePlural(substantive.name)
        binding.textViewMeaning.text = ""
        binding.buttonExample.visibility = View.INVISIBLE
    }

    fun showMeaning() {
        binding.textViewName.setTextColor(getColor(requireContext(), substantive.gender.color))
        binding.textViewMeaning.setTextColor(getColor(requireContext(), substantive.gender.color))
        binding.textViewName.text = substantive.gender.article + " " + substantive.name
        binding.textViewMeaning.text = substantive.meaning
        binding.buttonExample.visibility = View.VISIBLE
    }
}