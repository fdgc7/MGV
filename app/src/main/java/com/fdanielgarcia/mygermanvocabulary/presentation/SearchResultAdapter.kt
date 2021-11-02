package com.fdanielgarcia.mygermanvocabulary.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fdanielgarcia.mygermanvocabulary.R
import com.fdanielgarcia.mygermanvocabulary.data.VocabularyList
import com.fdanielgarcia.mygermanvocabulary.domain.*

class SearchResultAdapter(
    private val context: Context,
    private var vocabularyList: VocabularyList
) : RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    class SearchResultViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textViewContentLabel: TextView = view.findViewById(R.id.text_view_content_label)
        val textViewContent: TextView = view.findViewById(R.id.text_view_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent, false)
        return SearchResultViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val vocabulary = vocabularyList.element(position)
        when {
            vocabulary is Substantive -> {
                holder.textViewContentLabel.text =
                    context.resources.getString(R.string.substantive_content_label)
                holder.textViewContent.setTextColor(
                    ContextCompat.getColor(
                        context,
                        vocabulary.gender.color
                    )
                )
                holder.textViewContent.text =
                    vocabulary.gender.article + " " + vocabulary.name + " | " + vocabulary.meaning
            }
            vocabulary is Verb -> {
                holder.textViewContentLabel.text =
                    context.resources.getString(R.string.verb_content_label)
                holder.textViewContent.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.unidentified
                    )
                )
                holder.textViewContent.text =
                    vocabulary.infinitive + " | " +
                    vocabulary.present + " | " +
                    vocabulary.past + " | " +
                    vocabulary.perfect + " | " +
                    vocabulary.meaning
            }
            vocabulary is Adjective -> {
                holder.textViewContentLabel.text =
                    context.resources.getString(R.string.adjective_content_label)
                holder.textViewContent.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.unidentified
                    )
                )
                holder.textViewContent.text =
                    vocabulary.adjective + " | " + vocabulary.meaning
            }
            vocabulary is Adverb -> {
                holder.textViewContentLabel.text =
                    context.resources.getString(R.string.adverb_content_label)
                holder.textViewContent.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.unidentified
                    )
                )
                holder.textViewContent.text =
                    vocabulary.adverb + " | " + vocabulary.meaning
            }
            vocabulary is Conjunction -> {
                holder.textViewContentLabel.text =
                    context.resources.getString(R.string.conjunction_content_label)
                holder.textViewContent.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.unidentified
                    )
                )
                holder.textViewContent.text =
                    vocabulary.conjunction + " | " + vocabulary.meaning
            }
            vocabulary is Preposition -> {
                holder.textViewContentLabel.text =
                    context.resources.getString(R.string.preposition_content_label)
                holder.textViewContent.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.unidentified
                    )
                )
                holder.textViewContent.text =
                    vocabulary.preposition + " | " + vocabulary.meaning
            }
        }
    }

    override fun getItemCount() = vocabularyList.size()

    fun updateData(newVocabularyList: VocabularyList) {
        vocabularyList = newVocabularyList
        notifyDataSetChanged()
    }
}