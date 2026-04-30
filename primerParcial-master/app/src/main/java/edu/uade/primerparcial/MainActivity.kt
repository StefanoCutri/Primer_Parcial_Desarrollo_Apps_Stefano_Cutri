package edu.uade.primerparcial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import edu.uade.primerparcial.ui.PokemonListScreen
import edu.uade.primerparcial.ui.theme.PrimerParcialTheme

/**
 * Punto de entrada de la aplicación.
 *
 * Su única responsabilidad es aplicar el tema visual y mostrar
 * la pantalla principal. No contiene lógica de negocio ni accede a datos.
 *
 * En el código original, toda la lógica, el modelo, el repositorio
 * y los composables estaban mezclados acá. Ahora cada cosa
 * vive en su propia capa.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrimerParcialTheme {
                PokemonListScreen()
            }
        }
    }
}