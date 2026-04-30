package edu.uade.primerparcial.logic

import androidx.lifecycle.ViewModel
import edu.uade.primerparcial.model.Pokemon
import edu.uade.primerparcial.data.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel de la pantalla principal.
 *
 * Actúa como intermediario entre los datos y la UI.
 * Guarda el estado de la lista y se lo expone a la pantalla
 * a través de un StateFlow, que Compose puede observar
 * y recomponer automáticamente cuando cambia.
 *
 * La UI nunca habla directamente con el repositorio,
 * siempre pasa por acá.
 */

class PokemonViewModel(
    private val repository: PokemonRepository = PokemonRepository()
) : ViewModel() {

    private val _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons: StateFlow<List<Pokemon>> = _pokemons.asStateFlow()

    init {
        loadPokemons()
    }

    private fun loadPokemons() {
        _pokemons.value = repository.getPokemons()
    }
}