package com.sgh21.webservicesmmo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.sgh21.webservicesmmo.R
import com.sgh21.webservicesmmo.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val args: DetailFragmentArgs by navArgs()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        with(binding) {
            val game = args.game

            tittleTextView.text = game.title
            descriptionTextView.text = game.shortDescription
            genreTextView.text = game.platform
            if (game.thumbnail != null){
                Picasso.get().load(game.thumbnail).into(imageView)
            }

        }

        return root
    }
}