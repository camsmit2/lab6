package com.example.lab6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokemonAdapter(
    private val items: MutableList<Pokemon>
) : RecyclerView.Adapter<PokemonAdapter.PokemonVH>() {

    class PokemonVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.imgSpriteItem)
        val name: TextView = itemView.findViewById(R.id.txtNameItem)
        val idText: TextView = itemView.findViewById(R.id.txtIdItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return PokemonVH(view)
    }

    override fun onBindViewHolder(holder: PokemonVH, position: Int) {
        val p = items[position]
        holder.name.text = p.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
        holder.idText.text = "#%03d".format(p.id)

        Glide.with(holder.itemView)
            .load(p.imageUrl)
            .into(holder.img)
    }

    override fun getItemCount(): Int = items.size

    fun setData(newItems: List<Pokemon>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
