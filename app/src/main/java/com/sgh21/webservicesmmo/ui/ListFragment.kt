package com.sgh21.webservicesmmo.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sgh21.webservicesmmo.R
import com.sgh21.webservicesmmo.databinding.FragmentListBinding
import com.sgh21.webservicesmmo.model.Game
import com.sgh21.webservicesmmo.model.GameList
import com.sgh21.webservicesmmo.server.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var gameAdapter: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        gameAdapter = GameAdapter(onItemClicked = {onGameItemClicked(it)})

        binding.gameRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.context)
            adapter = gameAdapter
            setHasFixedSize(false)
        }

        loadMovies()
        return root
    }

    private fun loadMovies() {
        ApiService.create()
            .getListGame()
            .enqueue(object : Callback<GameList>{
                override fun onFailure(call: Call<GameList>, t: Throwable) {
                    Log.d("Error", t.message.toString())
                }

                override fun onResponse(call: Call<GameList>, response: Response<GameList>) {
                    if(response.isSuccessful){
                        val listMovies : MutableList<Game> = response.body() as MutableList<Game>
                        gameAdapter.appendItems(listMovies)
                    }
                }
            })
    }

    private fun onGameItemClicked(game: Game) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(game))
    }
}