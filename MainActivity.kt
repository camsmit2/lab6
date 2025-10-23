package com.example.lab6

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
        // 👇 Give Kotlin an explicit type so it infers correctly
        adapter = PokemonAdapter(mutableListOf<Pokemon>())
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        val url = "https://pokeapi.co/api/v2/pokemon?limit=151"

        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val results = json.jsonObject.getJSONArray("results")
                val list = mutableListOf<Pokemon>()

                for (i in 0 until results.length()) {
                    val obj = results.getJSONObject(i)
                    val name = obj.getString("name")
                    val urlStr = obj.getString("url")
                    val trimmed = urlStr.trimEnd('/')
                    val id = trimmed.substringAfterLast('/').toIntOrNull() ?: continue

                    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
                    list.add(Pokemon(id = id, name = name, imageUrl = imageUrl))
                }

                adapter.setData(list)
            }

            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                // TODO: Show a Toast or log
            }
        })
    }
}
