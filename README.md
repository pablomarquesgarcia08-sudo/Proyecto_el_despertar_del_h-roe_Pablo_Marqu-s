# El despertar del héroe

**Aventura de rol por turnos en Java | Proyecto de 1.º de Desarrollo de Aplicaciones Multiplataforma (DAM)**

## Descripción

*El despertar del héroe* es un videojuego de aventura textual que se ejecuta en la consola. El jugador despierta en una aldea abandonada y debe abrirse paso hasta una antigua fortaleza, derrotando a tres enemigos consecutivos. Para sobrevivir tendrá que administrar su vida, sus pociones y las decisiones que tome durante los combates.

El proyecto se desarrolla como práctica de programación en Java, aplicando menús, estructuras de control, métodos, arrays y clases. Se trata de un **prototipo en desarrollo**, por lo que algunas funcionalidades previstas en el enunciado todavía no están implementadas.

## Cómo se juega

Desde el menú principal se puede iniciar una partida, consultar la sección de ayuda, acceder a la opción de ranking o salir del juego.

Al comenzar una partida, el jugador:

1. Introduce el nombre de su héroe y selecciona un arma.
2. Explora la aldea, donde puede hablar con el comerciante o consultar su inventario.
3. Avanza hacia la fortaleza y afronta tres combates por turnos.
4. Decide en cada turno si atacar, utilizar una poción, consultar el inventario o rendirse.

La aventura se completa al derrotar al Guardián final. Si el héroe pierde toda su vida, la partida termina. Actualmente, abandonar la exploración o rendirse en combate cierra el programa.

## Reglas y mecánicas implementadas

- **Vida inicial:** 100 puntos. La vida no puede superar el máximo ni bajar de cero.
- **Ataque del héroe:** daño aleatorio de 10 a 20 puntos.
- **Ataque de los enemigos:** daño aleatorio de 5 a 15 puntos.
- **Pociones:** tres por partida, con una recuperación de hasta 25 puntos de vida cada una.
- **Inventario:** muestra las pociones restantes y el arma seleccionada.
- **Combate por turnos:** el enemigo contraataca si sobrevive al ataque o si el héroe utiliza una poción válida. Consultar el inventario o elegir una opción incorrecta no provoca contraataque.
- **Curación entre combates:** tras vencer a los dos primeros enemigos se ofrece la posibilidad de usar una poción. No aparece esa pregunta tras la victoria final.
- **Ayuda:** guía del aventurero con la historia, el objetivo y las reglas del juego.

### Enemigos

| Enemigo | Vida | Daño |
| --- | ---: | ---: |
| Centinela | 40 | 5–15 |
| Caballero | 60 | 5–15 |
| Guardián final | 80 | 5–15 |

### Armas disponibles

| Arma | Daño definido | Esquiva definida |
| --- | --- | ---: |
| Colmillo Sombrío | 10–14 | 30 % |
| Juramento de Acero | 12–17 | 15 % |
| Ira del Titán | 15–20 | 0 % |

**Estado del sistema de armas:** el jugador puede seleccionar un arma y verla en el inventario. Sus valores están definidos en `Armas.java`, pero **todavía no modifican el daño ni activan la esquiva durante el combate**; por ahora se utilizan los valores de ataque de `Personaje`.

## Estructura del código

| Archivo | Responsabilidad |
| --- | --- |
| `Proyecto.java` | Inicio del programa, menús, exploración, combates, inventario, selección de arma y ayuda. |
| `Personaje.java` | Datos del héroe y de los enemigos: nombre, vida y rangos de ataque. |
| `Armas.java` | Datos de cada arma: nombre, daño mínimo y máximo y probabilidad de esquiva. |
| `README.md` | Descripción del proyecto e instrucciones para ejecutarlo. |

La lógica principal se reparte en métodos, mientras que los objetos `Personaje` y `Armas` agrupan los datos relacionados con los personajes y su equipamiento.

## Requisitos y ejecución

Se necesita un **JDK de Java** instalado (por ejemplo, JDK 17 o 21). No se utilizan bibliotecas externas. El proyecto puede abrirse en Visual Studio Code o ejecutarse desde una terminal.

Para comprobar la versión de Java disponible:

```bash
java -version
javac -version
```

Desde la carpeta donde están los tres archivos `.java`, compilar y ejecutar:

```bash
javac -encoding UTF-8 Proyecto.java Personaje.java Armas.java
java Proyecto
```

También se puede abrir la carpeta en **Visual Studio Code** con las extensiones de Java y ejecutar la clase `Proyecto`, que contiene el método `main`.

## Funcionalidades pendientes

De acuerdo con los requisitos del proyecto académico, quedan por desarrollar o completar:

- **Puntuación:** 100 puntos por enemigo derrotado y bonificación equivalente a la vida restante cuando se gana la aventura.
- **Ranking:** diez mejores resultados, ordenados mediante arrays y conservados en un fichero XML. La opción del menú existe, pero todavía muestra un mensaje provisional.
- **Fin de partida:** adaptar el abandono y la rendición para regresar al menú principal en vez de cerrar toda la aplicación.
- **Validación y pruebas:** ampliar las comprobaciones de entradas y documentar casos de prueba, incluidos empates y ranking lleno cuando este se implemente.
- **Sistema de armas:** aplicar al combate los rangos de daño y las probabilidades de esquiva definidas para cada arma.

Las ampliaciones opcionales se abordarán sin sustituir los requisitos obligatorios de la actividad.

## Control de versiones

El desarrollo se registra mediante **Git y GitHub**, con commits descriptivos. La clase `Armas.java` se trabajó en una rama independiente llamada `armas` y posteriormente se integró en `main` mediante un merge (`f82cee9`).

**Repositorio:** [El despertar del héroe en GitHub](https://github.com/pablomarquesgarcia08-sudo/Proyecto_el_despertar_del_h-roe_Pablo_Marqu-s)

## Contexto académico

Proyecto individual de la **primera evaluación de 1.º de DAM**, relacionado con los módulos de Programación, Entornos de Desarrollo y Lenguaje de Marcas. Su objetivo es practicar la lógica de programación, la organización del código, las pruebas y el control de versiones.

La memoria técnica y la defensa del proyecto complementan este README y documentarán las pruebas, las decisiones de implementación y las herramientas de apoyo utilizadas.