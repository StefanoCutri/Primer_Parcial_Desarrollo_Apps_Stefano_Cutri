package edu.uade.primerparcial.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import edu.uade.primerparcial.model.Pokemon
import edu.uade.primerparcial.logic.PokemonViewModel

/**
 * Pantalla principal de la Pokédex.
 *
 * Esta capa solo se encarga de dibujar lo que el ViewModel le da.
 * No calcula nada, no accede a datos directamente.
 * Observa el StateFlow del ViewModel con collectAsState() y
 * Compose recompone la pantalla automáticamente cuando la lista cambia.
 *
 * Se usa LazyColumn para renderizar solo los ítems visibles en pantalla,
 * lo que hace la lista eficiente sin importar cuántos pokémon haya.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    viewModel: PokemonViewModel = viewModel()
) {
    val pokemons by viewModel.pokemons.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pokédex",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            items(items = pokemons, key = { it.id }) { pokemon ->
                PokemonItem(pokemon = pokemon)
            }
        }
    }
}
/**
 * Ítem individual de la lista.
 *
 * Recibe un pokémon ya listo y lo muestra.
 * No sabe de dónde vino el dato ni cómo se calculó el sprite.
 * Usa Coil (AsyncImage) para cargar la imagen desde la URL de forma asíncrona.
 */

@Composable
fun PokemonItem(pokemon: Pokemon) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                model = pokemon.spriteUrl,
                contentDescription = pokemon.nameFormatted,
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = pokemon.nameFormatted,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "N.º ${pokemon.id}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}