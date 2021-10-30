package com.fdanielgarcia.mygermanvocabulary.presentation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fdanielgarcia.mygermanvocabulary.data.VocabularyList
import com.fdanielgarcia.mygermanvocabulary.databinding.FragmentSearchBinding
import com.fdanielgarcia.mygermanvocabulary.domain.Vocabulary
import com.fdanielgarcia.mygermanvocabulary.use_cases.ListManagement


class SearchFragment : Fragment() {
    val listManagement by lazy { ListManagement(activity as Activity) }
    private var vocabularyList = VocabularyList()
    private lateinit var vocabulary: Vocabulary
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).hideFab()
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            adapter = SearchResultAdapter(context, vocabularyList)
            setHasFixedSize(true)
        }

        binding.editTextSearch.setText("")
        binding.buttonSearch.setOnClickListener {
            search()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).showFab()
        _binding = null
    }

    fun search() {
        vocabularyList = listManagement.searchResultList(binding.editTextSearch.text.toString())
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}