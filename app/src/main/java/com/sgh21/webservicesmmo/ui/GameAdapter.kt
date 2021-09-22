package com.sgh21.webservicesmmo.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sgh21.webservicesmmo.R
import com.sgh21.webservicesmmo.databinding.GameListItemBinding
import com.sgh21.webservicesmmo.model.Game
import com.sgh21.webservicesmmo.model.GameList
import com.squareup.picasso.Picasso

class GameAdapter(
    private val onItemClicked: (Game) -> Unit,
) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    private var listGame: MutableList<Game> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameAdapter.ViewHolder, position: Int) {
        holder.bind(listGame[position])
        holder.itemView.setOnClickListener { onItemClicked(listGame[position]) }
    }

    override fun getItemCount(): Int = listGame.size

    fun appendItems(newItems: MutableList<Game>){
        listGame.clear()
        listGame.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = GameListItemBinding.bind(view)
        private val context: Context = binding.root.context
        fun bind(game: Game){
            with(binding){
                tittleTextView.text = game.title
                releaseTextView.text = context.getString(R.string.release_info, game.releaseDate)
                platformTextView.text = game.platform
                if (game.thumbnail != null){
                    Picasso.get().load(game.thumbnail).into(pictureImageView)
                }
            }
        }
    }

}