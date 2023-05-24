package com.example.geeksforgeeksandroidchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geeksforgeeksandroidchallenge.ViewModel.ApiViewModel
import com.example.geeksforgeeksandroidchallenge.databinding.FragmentArticleBinding
import kotlinx.coroutines.launch


class ArticleFragment : Fragment() {
    private val viewModel: ApiViewModel by viewModels()
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        // Set up your views using the binding object
        //binding.photosGrid.layoutManager = LinearLayoutManager(requireContext())
        // ... Set up other views
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.status.observe(viewLifecycleOwner) { status ->
                // Handle status changes here
            }

            viewModel.thumbnail.observe(viewLifecycleOwner) { thumbnails ->
                binding.thumbnailTextView.text = thumbnails.joinToString("\n") { it.thumbnail }
            }

            viewModel.title.observe(viewLifecycleOwner) { titles ->
                binding.titleTextView.text = titles.joinToString("\n") { it.title }
            }

            viewModel.pubdate.observe(viewLifecycleOwner) { pubdates ->
                binding.pubdateTextView.text = pubdates.joinToString("\n") { it.pubDate }
            }
        }
    }
}