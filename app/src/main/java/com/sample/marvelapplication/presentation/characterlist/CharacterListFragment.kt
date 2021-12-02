package com.sample.marvelapplication.presentation.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.marvelapplication.R
import com.sample.marvelapplication.databinding.FragmentCharactersBinding
import com.sample.marvelapplication.presentation.CharacterViewModelFactory
import com.sample.marvelapplication.module.CharacterModule
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment for Character List
 */
@AndroidEntryPoint
class CharacterListFragment : Fragment() {
    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var characterListViewModel: CharacterListViewModel
    private lateinit var binding: FragmentCharactersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterListViewModel = ViewModelProvider(
            this,
            CharacterViewModelFactory(
                CharacterModule.getCharacterListUseCase,
                CharacterModule.getCharacterDetailsUseCase
            )
        ).get(CharacterListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_characters, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = characterListViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        characterListViewModel.getCharacterList()
    }

    private fun onListItemClick(position: Int) {
        val characterId = characterListViewModel.characterList.value?.get(position)?.id
        characterId?.let {
            val direction = CharacterListFragmentDirections.toCharacterDetailsFragment(it)
            findNavController().navigate(direction)
        }
    }

    private fun initRecyclerView() {
        binding.rvCharacterList.visibility = View.VISIBLE
        binding.rvCharacterList.layoutManager = LinearLayoutManager(requireContext())
        characterListAdapter = CharacterListAdapter { position ->
            onListItemClick(position)
        }
        binding.characterListAdapter = characterListAdapter
    }
}
