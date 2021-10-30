package com.fdanielgarcia.mygermanvocabulary.presentation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fdanielgarcia.mygermanvocabulary.R
import com.fdanielgarcia.mygermanvocabulary.data.*
import com.fdanielgarcia.mygermanvocabulary.databinding.FragmentTestVocabularyBinding
import com.fdanielgarcia.mygermanvocabulary.use_cases.ListManagement

class TestVocabularyFragment : Fragment() {
    val listManagement by lazy { ListManagement(activity as Activity) }
    private var _binding: FragmentTestVocabularyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTestVocabularyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonTestAllSubstantives.setOnClickListener {
            val substantiveList = listManagement.loadList("AllSubstantives") as SubstantiveList
            val bundle = Bundle()
            bundle.putParcelable("List", substantiveList)
            findNavController().navigate(R.id.action_TestVocabularyFragment_to_ShowSubstantiveFragment, bundle)
        }
        binding.buttonTestAllSubstantives.isEnabled = listManagement.hasData("AllSubstantives")

        binding.buttonTestMasculineSubstantives.setOnClickListener {
            val substantiveList = listManagement.loadList("MasculineSubstantives") as SubstantiveList
            val bundle = Bundle()
            bundle.putParcelable("List", substantiveList)
            findNavController().navigate(R.id.action_TestVocabularyFragment_to_ShowSubstantiveFragment, bundle)
        }
        binding.buttonTestMasculineSubstantives.isEnabled = listManagement.hasData("MasculineSubstantives")

        binding.buttonTestFeminineSubstantives.setOnClickListener {
            val substantiveList = listManagement.loadList("FeminineSubstantives") as SubstantiveList
            val bundle = Bundle()
            bundle.putParcelable("List", substantiveList)
            findNavController().navigate(R.id.action_TestVocabularyFragment_to_ShowSubstantiveFragment, bundle)
        }
        binding.buttonTestFeminineSubstantives.isEnabled = listManagement.hasData("FeminineSubstantives")

        binding.buttonTestNeuterSubstantives.setOnClickListener {
            val substantiveList = listManagement.loadList("NeuterSubstantives") as SubstantiveList
            val bundle = Bundle()
            bundle.putParcelable("List", substantiveList)
            findNavController().navigate(R.id.action_TestVocabularyFragment_to_ShowSubstantiveFragment, bundle)
        }
        binding.buttonTestNeuterSubstantives.isEnabled = listManagement.hasData("NeuterSubstantives")

        binding.buttonTestVerbs.setOnClickListener {
            val verbList = listManagement.loadList("Verbs") as VerbList
            val bundle = Bundle()
            bundle.putParcelable("List", verbList)
            findNavController().navigate(R.id.action_TestVocabularyFragment_to_ShowVerbFragment, bundle)
        }
        binding.buttonTestVerbs.isEnabled = listManagement.hasData("Verbs")

        binding.buttonTestAdjectives.setOnClickListener {
            val adjectiveList = listManagement.loadList("Adjectives") as AdjectiveList
            val bundle = Bundle()
            bundle.putString("showOtherFragmentTitle",activity?.resources?.getString(R.string.show_adjective_fragment_label))
            bundle.putParcelable("List", adjectiveList)
            findNavController().navigate(R.id.action_TestVocabularyFragment_to_ShowOtherFragment, bundle)
        }
        binding.buttonTestAdjectives.isEnabled = listManagement.hasData("Adjectives")

        binding.buttonTestAdverbs.setOnClickListener {
            val adverbList = listManagement.loadList("Adverbs") as AdverbList
            val bundle = Bundle()
            bundle.putString("showOtherFragmentTitle",activity?.resources?.getString(R.string.show_adverb_fragment_label))
            bundle.putParcelable("List", adverbList)
            findNavController().navigate(R.id.action_TestVocabularyFragment_to_ShowOtherFragment, bundle)
        }
        binding.buttonTestAdverbs.isEnabled = listManagement.hasData("Adverbs")

        binding.buttonTestConjunctions.setOnClickListener {
            val conjunctionList = listManagement.loadList("Conjunctions") as ConjunctionList
            val bundle = Bundle()
            bundle.putString("showOtherFragmentTitle",activity?.resources?.getString(R.string.show_conjunction_fragment_label))
            bundle.putParcelable("List", conjunctionList)
            findNavController().navigate(R.id.action_TestVocabularyFragment_to_ShowOtherFragment, bundle)
        }
        binding.buttonTestConjunctions.isEnabled = listManagement.hasData("Conjunctions")

        binding.buttonTestPrepositions.setOnClickListener {
            val prepositionList = listManagement.loadList("Prepositions") as PrepositionList
            val bundle = Bundle()
            bundle.putString("showOtherFragmentTitle",activity?.resources?.getString(R.string.show_preposition_fragment_label))
            bundle.putParcelable("List", prepositionList)
            findNavController().navigate(R.id.action_TestVocabularyFragment_to_ShowOtherFragment, bundle)
        }
        binding.buttonTestPrepositions.isEnabled = listManagement.hasData("Prepositions")

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_TestVocabularyFragment_to_DefaultFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}