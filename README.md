# Practica_Serializacion_Nathan
En esta práctica se nos ha encargado crear una aplicación para el juego de T-Rex de Google.
Nuestro equipo tiene como objetivo definir la estructura de las clases del juego y serializar y deserializar los mismos.

Se ha de poder serializar y deserializar en:
- JSON. Se recomienda la librería Gson.
- XML. Se recomienda la librería JAXB
- Binario

El juego posee una lista de niveles, donde cada nivel tiene
- Tiempo de juego
- Velocidad
- Lista de Cactus
- Lista de Pájaros
- Música

A su vez, el cactus cuenta con:
- Coordenadas del tipo Point2D
- Tamaño
- Nombre

Y el pájaro:
- Tamaño
- Nombre
- Velocidad
- Coordenadas del tipo Point2D


