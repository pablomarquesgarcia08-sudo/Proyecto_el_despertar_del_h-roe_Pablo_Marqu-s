
# El despertar del héroe

**Aventura de rol por turnos en Java | Proyecto de 1.º de Desarrollo de Aplicaciones Multiplataforma (DAM)**

## Descripción

*El despertar del héroe* es un videojuego de aventura textual desarrollado en Java que se ejecuta desde la consola. El jugador despierta en una aldea abandonada y debe avanzar hasta una antigua fortaleza, enfrentándose a tres enemigos consecutivos. Para sobrevivir deberá administrar sus puntos de vida, utilizar sus pociones y escoger adecuadamente sus acciones durante los combates.

El proyecto se ha desarrollado como parte de la primera evaluación del ciclo formativo de Desarrollo de Aplicaciones Multiplataforma (DAM). Su objetivo es aplicar los conocimientos adquiridos sobre variables, constantes, operadores, estructuras de control, métodos, arrays, clases, interfaces, validación de datos y gestión de archivos XML.

Además de los requisitos principales, se han incorporado diferentes armas, clases de personaje y habilidades especiales para ampliar las posibilidades del juego.

## Cómo se juega

Al ejecutar el programa, el usuario debe indicar si desea iniciar el juego. A continuación, aparece un menú principal con cuatro opciones:

1. Nueva partida.
2. Consultar ranking.
3. Ayuda.
4. Salir.

Al comenzar una nueva partida, el jugador:

1. Introduce el nombre de su héroe. El programa comprueba que no esté vacío.
2. Selecciona una clase de personaje: Guerrero, Mago o Arquero.
3. Escoge una de las tres armas disponibles.
4. Comienza la aventura con 100 puntos de vida y tres pociones.
5. Explora la aldea, donde puede hablar con el comerciante o consultar su inventario.
6. Avanza hacia la fortaleza y se enfrenta a tres enemigos consecutivos.
7. Decide durante cada turno si atacar, curarse, consultar el inventario, rendirse o utilizar su habilidad especial.

La aventura termina cuando el jugador derrota a los tres enemigos, pierde todos sus puntos de vida, se rinde o abandona la partida.

Al finalizar, el programa calcula la puntuación obtenida, actualiza el ranking y regresa al menú principal, permitiendo iniciar una nueva partida sin cerrar la aplicación.

## Reglas y mecánicas implementadas

- **Vida inicial:** el héroe comienza con 100 puntos de vida.
- **Límites de vida:** la vida nunca puede ser inferior a cero ni superar el máximo permitido.
- **Ataque del héroe:** el daño depende del arma seleccionada.
- **Ataque de los enemigos:** daño aleatorio de entre 5 y 15 puntos.
- **Pociones:** el jugador dispone de tres pociones por partida, cada una con una recuperación máxima de 25 puntos de vida.
- **Inventario:** permite consultar la vida del héroe, las pociones disponibles y el arma equipada.
- **Combate por turnos:** el héroe puede atacar, curarse, consultar el inventario, rendirse o usar su habilidad especial.
- **Contraataques:** el enemigo contraataca si sobrevive a un ataque, si el héroe falla su golpe o si utiliza una poción válida durante el combate.
- **Acciones sin contraataque:** consultar el inventario, introducir una opción incorrecta o intentar utilizar una poción que no puede consumirse no provoca un ataque enemigo.
- **Curación entre combates:** al derrotar a un enemigo mediante un ataque normal, se ofrece la posibilidad de utilizar una poción antes de continuar, excepto tras el último enemigo.
- **Habilidades especiales:** cada clase dispone de una habilidad que puede utilizarse una vez por partida.
- **Puntuación:** se obtienen puntos por los enemigos derrotados y una bonificación adicional al completar la aventura.
- **Ranking:** almacena las diez mejores puntuaciones y permite consultarlas desde el menú principal.
- **Persistencia:** las puntuaciones se guardan en un archivo XML y se recuperan al volver a ejecutar el programa.

### Enemigos

Los enemigos aparecen en un orden establecido y cada uno dispone de una cantidad diferente de puntos de vida.

| **Enemigo** | **Vida** | **Daño** |
| --- | ---: | ---: |
| Centinela | 40 | 5–15 |
| Caballero | 60 | 5–15 |
| Guardián final | 80 | 5–15 |

Para completar la aventura es necesario derrotar a los tres enemigos y conservar al menos un punto de vida.

### Armas disponibles

El jugador puede seleccionar una de las tres armas al comenzar una partida. Cada arma tiene un rango de daño y una probabilidad de fallo diferentes.

| **Arma** | **Daño** | **Probabilidad de fallo** |
| --- | ---: | ---: |
| Colmillo Sombrío | 10–14 | 0 % |
| Juramento de Acero | 12–17 | 15 % |
| Ira del Titán | 15–20 | 30 % |

**Colmillo Sombrío:** es el arma más segura. Sus ataques no tienen probabilidad de fallo, aunque su daño máximo es inferior al de las demás.

**Juramento de Acero:** ofrece un equilibrio entre daño y riesgo, con una probabilidad de fallo del 15 %.

**Ira del Titán:** permite realizar ataques más potentes, pero tiene una probabilidad de fallo del 30 %.

El daño se calcula de manera aleatoria dentro del rango definido para cada arma, utilizando **Math.random()**.

Si un ataque falla, el enemigo puede contraatacar siempre que siga con vida.

### Clases y habilidades especiales

Además del arma, el jugador debe seleccionar una clase al comenzar la partida.

Cada clase dispone de una habilidad especial diferente.

| **Clase** | **Personaje** | **Habilidad especial** |
| --- | --- | --- |
| Guerrero | Aragorn | Ataque poderoso que causa 20 puntos de daño |
| Mago | Gandalf | Hechizo de fuego que causa 25 puntos de daño |
| Arquero | Legolas | Dos disparos que causan entre 16 y 24 puntos de daño en total |

Las habilidades especiales están implementadas mediante la interfaz **HabilidadEspecial** y las clases **Guerrero**, **Mago** y **Arquero**.

Cada habilidad especial puede utilizarse una sola vez por partida. El programa controla su disponibilidad e impide volver a utilizarla cuando ya se ha consumido.

## Sistema de puntuación

El videojuego dispone de un sistema de puntuación que permite comparar los resultados de diferentes partidas.

Las reglas son las siguientes:

- **Enemigo derrotado:** se obtienen 100 puntos.
- **Victoria completa:** se añade una bonificación equivalente a los puntos de vida restantes del héroe.
- **Derrota o abandono:** se conserva la puntuación obtenida hasta ese momento, sin recibir la bonificación final.

Por ejemplo, si el jugador derrota a los tres enemigos y termina con 65 puntos de vida:

- Centinela derrotado: 100 puntos.
- Caballero derrotado: 100 puntos.
- Guardián final derrotado: 100 puntos.
- Bonificación por vida restante: 65 puntos.

**Puntuación final: 365 puntos.**

La puntuación se calcula al finalizar la partida y se utiliza para actualizar el ranking.

## Sistema de ranking

El juego incorpora un ranking que almacena las diez mejores puntuaciones obtenidas.

Para gestionar la clasificación se utilizan dos arrays paralelos:

- Un array de tipo **String** que almacena los nombres de los jugadores.
- Un array de tipo **int** que almacena las puntuaciones correspondientes.

La clasificación se mantiene ordenada de mayor a menor puntuación.

Cuando termina una partida, el programa comprueba si el resultado debe incorporarse al ranking.

Si todavía hay posiciones disponibles, añade la nueva puntuación en el lugar correspondiente.

Si el ranking está completo, únicamente se incorpora una puntuación que supere a alguna de las ya registradas.

En caso de empate, se mantiene por delante al jugador que consiguió anteriormente esa puntuación.

El programa también permite consultar el ranking desde el menú principal. Si todavía no existen resultados, muestra un mensaje informativo.

### Almacenamiento del ranking en XML

Las puntuaciones se almacenan en el archivo **Ranking.xml**.

Cada jugador se representa mediante un elemento **jugador**, que contiene su nombre y su puntuación.

Ejemplo:


<jugador>
    <nombre>Pablo</nombre>
    <puntuacion>365</puntuacion>
</jugador>


El archivo XML permite conservar las puntuaciones entre ejecuciones del programa.

Cuando se inicia el videojuego, el método **cargarRanking()** recupera los resultados almacenados.

Cuando finaliza una partida, el método **guardarRanking()** actualiza el archivo con las puntuaciones correspondientes.

### Validación mediante DTD

El archivo **Ranking.dtd** define la estructura permitida del documento XML.

Establece que el elemento principal **Ranking** contiene elementos **jugador** y que cada jugador debe incluir un nombre y una puntuación.

De esta forma, se dispone de una definición formal de la estructura de los datos.

### Presentación mediante XSLT

El archivo **Ranking.xsl** permite transformar los datos del XML en una tabla HTML con un diseño visual.

La transformación XSLT genera una tabla HTML que muestra:

- La posición de cada jugador.
- El nombre del jugador.
- La puntuación obtenida.

Los jugadores se ordenan de mayor a menor puntuación mediante la instrucción xsl:sort. El diseño visual se aplica utilizando una hoja de estilos externa, Ranking.css.

El archivo **Ranking.xml** incluye una referencia a **Ranking.xsl**, que se conserva cuando Java actualiza el ranking.

## Estructura del código

El proyecto está organizado en distintos archivos, cada uno con responsabilidades específicas.

| **Archivo** | **Responsabilidad** |
| --- | --- |
| **Proyecto.java** | Clase principal. Gestiona los menús, las partidas, la exploración, el inventario, las puntuaciones y el ranking. |
| **Combate.java** | Gestiona los combates por turnos, los ataques, las curaciones, las habilidades especiales y los contraataques enemigos. |
| **Personaje.java** | Representa los personajes del juego y gestiona sus datos, como nombre, vida y ataque. |
| **Armas.java** | Define las características de las armas: nombre, daño mínimo, daño máximo y probabilidad de fallo. |
| **HabilidadEspecial.java** | Interfaz que establece el método utilizado por las habilidades especiales. |
| **Guerrero.java** | Implementa la habilidad especial del Guerrero. |
| **Mago.java** | Implementa la habilidad especial del Mago. |
| **Arquero.java** | Implementa la habilidad especial del Arquero. |
| **Ranking.xml** |  Almacena las puntuaciones de los jugadores. |
| **Ranking.dtd`**| Define la estructura del archivo XML. |
| **Ranking.xsl** | Transforma el ranking XML en una tabla visual HTML. |
| **Ranking.css** | Define los estilos de la tabla HTML, centrando el contenido y aplicando un fondo azul claro a las cabeceras. |
| **LeerXML.java** | Lee el archivo Ranking.xml y muestra los nombres y puntuaciones de los jugadores en la consola. |
| **ModificarXML.java** | Permite modificar la puntuación de un jugador en Ranking_prueba.xml sin alterar el ranking original. |
| **.gitignore** | Excluye archivos compilados y otros archivos que no deben incorporarse al repositorio. |
| **README.md** | Documentación general e instrucciones de ejecución del proyecto. |

### Organización de los métodos

La clase principal divide las distintas responsabilidades del programa en métodos.

Entre los más importantes se encuentran:

- **main()**: inicia el programa y gestiona el menú principal.
- **iniciarPartida()**: solicita los datos del jugador y prepara una nueva partida.
- **explorarInicio()**: controla las opciones de exploración y el avance de la aventura.
-  **Combate.mostrarmenucombate()**: método de la clase Combate.java que gestiona los enfrentamientos por turnos, los ataques, las pociones y las habilidades especiales.
- **mostrarVida()**: muestra los puntos de vida actuales del personaje.
- **contarPociones()**: calcula cuántas pociones quedan disponibles.
- **mostrarInventario()**: muestra la información del inventario.
- **seleccionarArma()**: permite elegir el arma del héroe.
- **seleccionarClase()**: permite elegir una clase con su habilidad especial.
- **mostrarAyuda()**: muestra las instrucciones del videojuego.
- **cargarRanking()**: recupera las puntuaciones almacenadas en XML.
- **mostrarRanking()**: muestra la clasificación de jugadores.
- **insertarPuntuacion()**: incorpora una nueva puntuación manteniendo el orden.
- **guardarRanking()**: guarda la clasificación actualizada en XML.

Esta división facilita la lectura del código, evita concentrar todas las instrucciones en el método principal y permite organizar mejor las diferentes funcionalidades.

## Validación de entradas

El programa incorpora comprobaciones para evitar determinados errores durante la ejecución.

Entre las validaciones implementadas se encuentran:

- Comprobación de las respuestas de inicio del programa.
- Validación de las opciones introducidas en los menús.
- Comprobación de que el nombre del héroe no esté vacío.
- Validación de la clase y del arma seleccionadas.
- Comprobación de las pociones disponibles antes de utilizarlas.
- Comprobación del límite máximo de vida antes de curarse.
- Control del número de usos de la habilidad especial.
- Control del número máximo de jugadores del ranking.

Las opciones de los menús se leen como cadenas de texto, lo que permite mostrar un mensaje de error cuando se introduce una opción no válida, incluidos caracteres no numéricos.

## Requisitos y ejecución

Para ejecutar el proyecto se necesita tener instalado un JDK de Java.

El programa no necesita bibliotecas externas, ya que utiliza funcionalidades incluidas en Java.

Puede ejecutarse desde una terminal o mediante Visual Studio Code.

### Comprobar la instalación de Java

Abrir una terminal y ejecutar:

java -version
javac -version

Estos comandos permiten comprobar que Java y su compilador están disponibles.

### Compilar el proyecto

Abrir una terminal en la carpeta donde se encuentran los archivos del proyecto y ejecutar:

javac -encoding UTF-8 Proyecto.java Combate.java Personaje.java Armas.java HabilidadEspecial.java Guerrero.java Mago.java Arquero.java

Este comando compila las clases Java necesarias para ejecutar el videojuego.

### Ejecutar el programa

Una vez compilado, ejecutar:

java Proyecto

Es importante ejecutar el programa desde la carpeta del proyecto para que pueda localizar correctamente el archivo **Ranking.xml**.

### Ejecutar desde Visual Studio Code

También se puede abrir la carpeta del proyecto en Visual Studio Code.

Con las extensiones de Java instaladas, se puede abrir **Proyecto.java** y ejecutar el método **main()** mediante la opción de ejecución del editor.

## Pruebas del proyecto

Durante el desarrollo se han realizado comprobaciones de diferentes partes del programa, especialmente de la navegación entre menús, la ejecución de partidas y la actualización del ranking XML.

Para completar la documentación de las pruebas se deben registrar los casos de prueba con sus entradas, resultados esperados, resultados obtenidos y evidencias.

Entre los casos que deben comprobarse y documentarse se encuentran:

- Introducir un nombre vacío.
- Seleccionar una opción no válida en el menú.
- Introducir letras en un menú numérico.
- Utilizar una poción con la vida al máximo.
- Intentar utilizar una poción cuando no quedan unidades.
- Ganar una partida derrotando a los tres enemigos.
- Perder una partida cuando la vida llega a cero.
- Abandonar una partida y regresar al menú.
- Comprobar el cálculo de la puntuación.
- Consultar el ranking sin puntuaciones.
- Comprobar el orden de las puntuaciones.
- Introducir puntuaciones empatadas.
- Comprobar el comportamiento del ranking con diez jugadores.
- Cerrar y volver a ejecutar el programa para comprobar que se conservan los resultados.
- Comprobar que la referencia XSLT se mantiene después de guardar el XML.

La memoria técnica del proyecto recogerá las pruebas documentadas y sus correspondientes resultados.

## Control de versiones

El desarrollo del proyecto se gestiona mediante **Git y GitHub**.

Se utilizan commits descriptivos para registrar la evolución del código, las correcciones de errores y la incorporación de nuevas funcionalidades.

Durante el desarrollo se han utilizado dos ramas de trabajo independientes:

- **armas:** utilizada para desarrollar y mejorar el sistema de armas del videojuego.
- **combate:** utilizada para separar la lógica de los enfrentamientos de Proyecto.java y trasladarla a Combate.java, mejorando la organización del código.

Ambas ramas se integraron posteriormente en **main** mediante operaciones de merge, conservando el historial de cambios y permitiendo comprobar la evolución del proyecto.

El repositorio incorpora un archivo **.gitignore** para evitar incluir archivos compilados, carpetas de compilación y determinados archivos locales del entorno de desarrollo.

**Repositorio:** [El despertar del héroe en GitHub](https://github.com/pablomarquesgarcia08-sudo/Proyecto_el_despertar_del_h-roe_Pablo_Marqu-s)

## Contexto académico

Proyecto individual correspondiente a la **primera evaluación de 1.º de Desarrollo de Aplicaciones Multiplataforma (DAM)**.

El trabajo está relacionado con los módulos de:

- Programación.
- Entornos de Desarrollo.
- Lenguajes de Marcas y Sistemas de Gestión de Información.

El proyecto permite aplicar conceptos de programación estructurada, utilización de clases e interfaces, gestión de datos mediante arrays, almacenamiento XML, transformaciones XSLT y control de versiones.

Además del código fuente, la entrega académica se complementará con una memoria técnica, las evidencias de las pruebas realizadas y una presentación para la defensa del proyecto.

En la memoria se documentarán las decisiones de diseño, las dificultades encontradas, las soluciones aplicadas, las referencias consultadas y el uso de herramientas de inteligencia artificial como apoyo durante el desarrollo.
