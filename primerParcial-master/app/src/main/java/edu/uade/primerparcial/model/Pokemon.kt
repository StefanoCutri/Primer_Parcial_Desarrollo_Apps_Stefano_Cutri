package edu.uade.primerparcial.model
/**
 * Modelo de datos que representa un pokémon.
 *
 * Vive en la capa 'model' porque es una estructura de datos pura
 * que no pertenece exclusivamente a ninguna capa — la usan el repositorio,
 * el ViewModel y la UI.
 *
 * Las propiedades calculadas (id, spriteUrl, nameFormatted) están acá
 * porque son transformaciones directas del dato, no lógica de negocio.
 */

data class Pokemon(
    val name: String,
    val url: String
) {
    val id: Int
        get() = url.trimEnd('/').substringAfterLast('/').toIntOrNull() ?: 0

    val spriteUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

    val nameFormatted: String
        get() = name.replaceFirstChar { it.uppercase() }
}