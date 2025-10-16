# AND101 Project 6 - CYOAPI Part 2: RecyclerView Edition

Submitted by: **Cameron Smith**

Time spent: **6** hours spent in total

## Summary

**PokePeek Recycler** is an Android app that **displays a scrollable list of Pokémon from the PokéAPI, showing each Pokémon’s sprite, name, and ID, with live data fetched via AsyncHTTPClient.**

If I had to describe this project in three (3) emojis, they would be: **🧩📡🐾**

## Application Features

<!-- (This is a comment) Please be sure to change the [ ] to [x] for any features you completed.  If a feature is not checked [x], you might miss the points for that item! -->

The following REQUIRED features are completed:

- [x] Make an API call to an API of your choice using AsyncHTTPClient
- [x] Implement a RecyclerView to display a list of entries from the API
- [x] Display at least three (3) pieces of data for each RecyclerView item

The following STRETCH features are implemented:

- [x] Add a UI element for the user to interact with API further (tap item → toast with name/ID)
- [x] Show a `Toast` when an item is clicked
- [x] Add item dividers with `DividerItemDecoration`

The following EXTRA features are implemented:

- [x] Capitalize Pokémon names and zero-pad IDs (e.g., `#001`)
- [x] Efficient image loading with Glide
- [x] Extract ID from API URL to avoid extra network calls

## Video Demo

Here's a video / GIF that demos all of the app's implemented features:

<img src='http://i.imgur.com/your-demo-gif.gif' title='Video Demo' width='' alt='Video Demo' />

GIF created with **Kap**

## Notes

- Used **PokéAPI** endpoint: `https://pokeapi.co/api/v2/pokemon?limit=151`
- Parsed `results[].name` and extracted ID from `results[].url`, then built sprite URL:
  `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/{id}.png`
- Main learnings: wiring a RecyclerView to live API data, basic list item optimization, and handling JSON safely.

## License

Copyright **2025** **Cameron Smith**

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
