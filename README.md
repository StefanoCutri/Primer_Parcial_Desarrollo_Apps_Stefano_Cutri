# Primer Parcial — Stefano Cutri
## Lo que hice
El código original tenía todo junto en un solo archivo MainActivity.kt: el modelo, el repositorio, el ViewModel y los composables mezclados sin ninguna separación. Mi trabajo fue reorganizarlo en capas con responsabilidades claras.

## Estructura que armé
```
edu.uade.primerparcial/
├── data/
│   └── PokemonRepository.kt
├── logic/
│   └── PokemonViewModel.kt
├── model/
│   └── Pokemon.kt
├── ui/
│   └── PokemonScreens.kt
└── MainActivity.kt
```

## Por qué separé cada cosa así
En model puse la definición de qué es un pokémon para esta app. Incluye las propiedades calculadas para obtener el id, la URL del sprite y el nombre formateado. No depende de nada más.
En data puse el repositorio, que es el único lugar que sabe de dónde vienen los datos. Hoy devuelve una lista hardcodeada, pero si mañana quisiera traer los datos de una API real, solo tendría que tocar este archivo sin afectar el resto.
En logic puse el ViewModel, que hace de puente entre los datos y la pantalla. Guarda el estado con StateFlow y le pide la lista al repositorio. La UI nunca habla directamente con el repositorio, siempre pasa por acá.
En ui puse los composables que dibujan la pantalla. Solo reciben el estado y lo muestran, no calculan nada ni acceden a datos por su cuenta.
MainActivity la dejé lo más limpia posible: aplica el tema y muestra la pantalla principal, nada más.

## Lo que eliminé
Saqué los composables de borrador que no llegaron a la versión final, el código de ejemplo que genera Android Studio por defecto y algunos fragmentos que no hacían nada útil en el proyecto.
