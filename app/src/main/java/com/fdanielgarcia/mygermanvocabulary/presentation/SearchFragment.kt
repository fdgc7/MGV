package com.fdanielgarcia.mygermanvocabulary.presentation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fdanielgarcia.mygermanvocabulary.R
import com.fdanielgarcia.mygermanvocabulary.data.VocabularyList
import com.fdanielgarcia.mygermanvocabulary.databinding.FragmentSearchBinding
import com.fdanielgarcia.mygermanvocabulary.use_cases.ListManagement


class SearchFragment : Fragment() {
    val listManagement by lazy { ListManagement(activity as Activity) }
    private var vocabularyList = VocabularyList()
    private var searchResultAdapter: SearchResultAdapter? = null
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

        searchResultAdapter = SearchResultAdapter(requireContext(), vocabularyList)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = searchResultAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
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
        //Todo: Check number of charaters
        //Todo: Search with follow
        //Todo: Remove upcases
        vocabularyList = listManagement.searchResultList(binding.editTextSearch.text.toString())
        Toast.makeText(
            activity,
            vocabularyList.size().toString() + " " + activity?.resources?.getString(R.string.found),
            Toast.LENGTH_LONG
        ).show()
        searchResultAdapter?.updateData(vocabularyList)
    }
}