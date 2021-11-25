package com.sample.marvelapplication.presentation.characterdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.sample.domain.model.MarvelCharacter
import com.sample.marvelapplication.R
import com.sample.marvelapplication.databinding.FragmentCharacterDetailsBinding
import com.sample.marvelapplication.presentation.CharacterViewModelFactory
import com.sample.marvelapplication.module.CharacterModule
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_character_details.*
import kotlinx.android.synthetic.main.fragment_characters.pbLoading

/**
 * Fragment for Character Details
 */
@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {
    private lateinit var characterDetailsViewModel: CharacterDetailsViewModel
    private var characterId: Int = DEFAULT_CHARACTER_ID
    private lateinit var binding: FragmentCharacterDetailsBinding
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
        characterDetailsViewModel.getCharacterDetails(characterId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_character_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    private fun setObserver() {
        characterDetailsViewModel.isLoad.observe(viewLifecycleOwner, {
            it?.let { visibility ->
                pbLoading.visibility = if (visibility) View.VISIBLE else View.GONE
            }
        })

        characterDetailsViewModel.characterDetails.observe(
            viewLifecycleOwner,
            {
                if (it != null) {
                    setDataToView(it)
                }
            })
        characterDetailsViewModel.errorMessage.observe(
            viewLifecycleOwner,
            {
                if (it.isNotEmpty()) {
                    tvErrorDetail.visibility = View.VISIBLE
                    pbLoading.visibility = View.GONE
                    tvErrorDetail.text = getString(R.string.error_message)
                }
            })
    }

    private fun setDataToView(marvelCharacter: MarvelCharacter) {
        binding.characterdetails = marvelCharacter
    }

    companion object {
        private const val DEFAULT_CHARACTER_ID = 0
    }
}
