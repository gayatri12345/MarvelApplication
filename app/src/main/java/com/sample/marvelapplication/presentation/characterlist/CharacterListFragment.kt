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
import com.sample.domain.model.MarvelCharacter
import com.sample.marvelapplication.R
import com.sample.marvelapplication.databinding.FragmentCharactersBinding
import com.sample.marvelapplication.presentation.CharacterViewModelFactory
import com.sample.marvelapplication.module.CharacterModule
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_characters.*

/**
 * Fragment for Character List
 */
@AndroidEntryPoint
class CharacterListFragment : Fragment() {
    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var characterListViewModel: CharacterListViewModel
    private lateinit var characterList: List<MarvelCharacter>
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
        setAdapter()
        characterListViewModel.getCharacterList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_characters, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    private fun setObserver() {
        characterListViewModel.isLoad.observe(viewLifecycleOwner, {
            it?.let { visibility ->
                pbLoading.visibility = if (visibility) View.VISIBLE else View.GONE
            }
        })

        characterListViewModel.characterList.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()) {
                rvCharacterList.visibility = View.GONE
                pbLoading.visibility = View.GONE
                tvError.visibility = View.VISIBLE
                binding.errorMessage = ERROR_MESSAGE
            } else {
                characterList = it
                initRecyclerView(it)
            }
        })
    }

    private fun onListItemClick(position: Int) {
        val characterId = characterList[position].id
        val direction = CharacterListFragmentDirections.toCharacterDetailsFragment(characterId)
        findNavController().navigate(direction)
    }

    private fun initRecyclerView(result: List<MarvelCharacter>) {
        binding.rvCharacterList.visibility = View.VISIBLE
        tvError.visibility = View.GONE
        binding.rvCharacterList.layoutManager = LinearLayoutManager(requireContext())
        characterListAdapter.addData(result)
        binding.characterListAdapter = characterListAdapter
    }

    private fun setAdapter() {
        characterListAdapter = CharacterListAdapter { position ->
            onListItemClick(position)
        }
    }

    companion object {
        private const val ERROR_MESSAGE = "Something Went Wrong!! Please Try Again"
    }
}
