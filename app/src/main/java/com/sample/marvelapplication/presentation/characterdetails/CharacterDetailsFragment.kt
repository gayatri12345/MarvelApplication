package com.sample.marvelapplication.presentation.characterdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.sample.marvelapplication.R
import com.sample.marvelapplication.databinding.FragmentCharacterDetailsBinding
import com.sample.marvelapplication.presentation.CharacterViewModelFactory
import com.sample.marvelapplication.module.CharacterModule
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment for Character Details
 */
@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {
    private lateinit var characterDetailsViewModel: CharacterDetailsViewModel
    private lateinit var binding: FragmentCharacterDetailsBinding
    private var characterId: Int = DEFAULT_CHARACTER_ID
    private val args by navArgs<CharacterDetailsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterId = args.characterId
        characterDetailsViewModel = ViewModelProvider(
            this,
            CharacterViewModelFactory(
                CharacterModule.getCharacterListUseCase, CharacterModule.getCharacterDetailsUseCase,
            )
        ).get(CharacterDetailsViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_character_details, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = characterDetailsViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterDetailsViewModel.getCharacterDetails(characterId)
    }

    companion object {
        private const val DEFAULT_CHARACTER_ID = 0
    }
}
