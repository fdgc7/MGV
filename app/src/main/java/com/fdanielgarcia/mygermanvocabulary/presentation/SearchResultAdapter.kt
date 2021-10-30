package com.fdanielgarcia.mygermanvocabulary.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fdanielgarcia.mygermanvocabulary.R
import com.fdanielgarcia.mygermanvocabulary.data.*
import com.fdanielgarcia.mygermanvocabulary.domain.Adjective
import com.fdanielgarcia.mygermanvocabulary.domain.Adverb
import com.fdanielgarcia.mygermanvocabulary.domain.Conjunction
import com.fdanielgarcia.mygermanvocabulary.domain.Preposition

class SearchResultAdapter(
    private val context: Context,
    private val vocabularyList: VocabularyList
) : RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    class SearchResultViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textViewContentLabel: TextView = view.findViewById(R.id.text_view_content_label)
        val textViewContent: TextView = view.findViewById(R.id.text_view_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent, false)

        return SearchResultViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val vocabulary = vocabularyList.element(position)
        when {
            vocabulary is Adjective -> {
                holder.textViewContentLabel.text = context.resources.getString(R.string.adjective_content_label)
                holder.textViewContent.text = (vocabulary as Adjective).adjective + " / " + (vocabulary as Adjective).meaning
            }
        }
    }

    override fun getItemCount() = vocabularyList.size()
}