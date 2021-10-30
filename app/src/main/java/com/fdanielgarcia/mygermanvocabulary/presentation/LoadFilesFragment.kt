package com.fdanielgarcia.mygermanvocabulary.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fdanielgarcia.mygermanvocabulary.R
import com.fdanielgarcia.mygermanvocabulary.databinding.FragmentLoadFilesBinding
import com.fdanielgarcia.mygermanvocabulary.use_cases.FileManagement

class LoadFilesFragment : Fragment() {
    val fileManagement by lazy { FileManagement(activity as Activity) }
    private lateinit var loadMasculineSubstantiveLauncher: ActivityResultLauncher<Intent>
    private lateinit var loadFeminineSubstantiveLauncher: ActivityResultLauncher<Intent>
    private lateinit var loadNeuterSubstantiveLauncher: ActivityResultLauncher<Intent>
    private lateinit var loadVerbLauncher: ActivityResultLauncher<Intent>
    private lateinit var loadAdjectiveLauncher: ActivityResultLauncher<Intent>
    private lateinit var loadAdverbLauncher: ActivityResultLauncher<Intent>
    private lateinit var loadConjunctionLauncher: ActivityResultLauncher<Intent>
    private lateinit var loadPrepositionLauncher: ActivityResultLauncher<Intent>
    private var _binding: FragmentLoadFilesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadMasculineSubstantiveLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                manageResult(result,"MasculineSubstantives")
            }

        loadFeminineSubstantiveLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                manageResult(result,"FeminineSubstantives")
            }

        loadNeuterSubstantiveLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                manageResult(result,"NeuterSubstantives")
            }

        loadVerbLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                manageResult(result,"Verbs")
            }

        loadAdjectiveLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                manageResult(result,"Adjectives")
            }

        loadAdverbLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                manageResult(result,"Adverbs")
            }

        loadConjunctionLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                manageResult(result,"Conjunctions")
            }

        loadPrepositionLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                manageResult(result,"Prepositions")
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoadFilesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLoadMasculineSubstantives.setOnClickListener {
            fileManagement.loadFile(loadMasculineSubstantiveLauncher)
        }

        binding.buttonLoadFeminineSubstantives.setOnClickListener {
            fileManagement.loadFile(loadFeminineSubstantiveLauncher)
        }

        binding.buttonLoadNeuterSubstantives.setOnClickListener {
            fileManagement.loadFile(loadNeuterSubstantiveLauncher)
        }

        binding.buttonLoadVerbs.setOnClickListener {
            fileManagement.loadFile(loadVerbLauncher)
        }

        binding.buttonLoadAdjectives.setOnClickListener {
            fileManagement.loadFile(loadAdjectiveLauncher)
        }

        binding.buttonLoadAdverbs.setOnClickListener {
            fileManagement.loadFile(loadAdverbLauncher)
        }

        binding.buttonLoadConjunctions.setOnClickListener {
            fileManagement.loadFile(loadConjunctionLauncher)
        }

        binding.buttonLoadPrepositions.setOnClickListener {
            fileManagement.loadFile(loadPrepositionLauncher)
        }

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_LoadFilesFragment_to_DefaultFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun manageResult(result: ActivityResult, fileType: String) {
        if (result.resultCode == Activity.RESULT_OK) {
            Toast.makeText(
                activity,
                fileManagement.loadCSV(fileType, result.data?.data!!)
                    .toString() + " " +
                        activity?.resources?.getString(R.string.records_loaded),
                Toast.LENGTH_LONG
            ).show()
        } else {
            Toast.makeText(
                activity,
                activity?.resources?.getString(R.string.file_not_loaded),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}