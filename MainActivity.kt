package com.example.pokeapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: PokemonAdapter
    private val client = AsyncHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recyclerPokemon)
        adapter = PokemonAdapter(mutableListOf())
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        val url = "https://pokeapi.co/api/v2/pokemon?limit=151"

        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                val results = json.jsonObject.getJSONArray("results")
                val list = mutableListOf<Pokemon>()

                for (i in 0 until results.length()) {
                    val obj = results.getJSONObject(i)
                    val name = obj.getString("name")
                    val urlStr = obj.getString("url") // ends with /pokemon/{id}/

                    // Extract ID from URL safely
                    val trimmed = urlStr.trimEnd('/')
                    val id = trimmed.substringAfterLast('/').toIntOrNull() ?: continue

                    // Small sprite (fast to load). You can swap to official-artwork if you want.
                    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

                    list.add(Pokemon(id = id, name = name, imageUrl = imageUrl))
                }

                adapter.setData(list)
            }

            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                // Optional: show a Toast or empty state
            }
        })
    }
}
