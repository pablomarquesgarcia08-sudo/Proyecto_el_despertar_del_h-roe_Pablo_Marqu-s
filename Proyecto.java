import java.util.Scanner;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Proyecto {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.println("El despertar del héroe");

        String[] nombresRanking = new String[10];
        int[] puntuacionesRanking = new int[10];
        int cantidadJugadores = cargarRanking(nombresRanking, puntuacionesRanking);
        String iniciar;

        do {
            System.out.println("¿Quieres iniciar el juego? (si/no)");
            iniciar = teclado.nextLine();

            if (iniciar.equalsIgnoreCase("si")) {
                System.out.println("El juego se ha iniciado correctamente");
            } else if (iniciar.equalsIgnoreCase("no")) {
                System.out.println("Has decidido salir del juego");
            } else {
                System.out.println("Respuesta incorrecta. Escribe si o no.");
            }

        } while (!iniciar.equalsIgnoreCase("si")
                && !iniciar.equalsIgnoreCase("no"));

        if (iniciar.equalsIgnoreCase("si")) {

            String opcion;

            do {
                System.out.println("\n-- Menú principal --");
                System.out.println("1. Nueva partida");
                System.out.println("2. Consultar ranking");
                System.out.println("3. Ayuda");
                System.out.println("4. Salir");

                System.out.println("\nSelecciona una opción");

                opcion = teclado.nextLine();

                switch (opcion) {

                   case "1":
                        cantidadJugadores = iniciarPartida(teclado, nombresRanking, puntuacionesRanking, cantidadJugadores);
                        break;

                   case "2":
                        mostrarRanking(teclado, nombresRanking, puntuacionesRanking, cantidadJugadores);
                        break;

                    case "3":
                        mostrarAyuda(teclado);
                        break;

                    case "4":
                        System.out.println("Saliendo del juego...");
                        break;

                    default:
                        System.out.println("Opción incorrecta.");
                        break;
                }

            } while (!opcion.equals("4"));
        }
    }

    public static int iniciarPartida(Scanner teclado, String[] nombresRanking, int[] puntuacionesRanking, int cantidadJugadores) 
    { 
        

        String nombre;

        System.out.println("Iniciando nueva partida...");

        do {
            System.out.print("\nIntroduce el nombre del héroe: ");
            nombre = teclado.nextLine();

            if (nombre.isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacío");
            }

        } while (nombre.isEmpty());

        Personaje heroe = new Personaje(nombre, 100, 0, 10, 20);

        Personaje enemigo1 = new Personaje("Centinela", 40, 0, 5, 15);
        Personaje enemigo2 = new Personaje("Caballero", 60, 0, 5, 15);
        Personaje enemigo3 = new Personaje("Guardian final", 80, 0, 5, 15);

        Armas[] inventarioArmas = new Armas[1];
        int[] pociones = { 25, 25, 25 };
        HabilidadEspecial habilidadElegida = seleccionarClase(teclado);
        int[] usosHabilidad = {1};

        inventarioArmas[0] = seleccionarArma(teclado);

        System.out.println("Hola " + nombre + ", ahora eres un héroe.");
        mostrarVida(heroe);

        int puntuacion = explorarInicio(teclado, heroe, enemigo1, enemigo2, enemigo3, pociones, inventarioArmas, habilidadElegida, usosHabilidad);

        System.out.println("Tu puntuacion final es: " + puntuacion);

        cantidadJugadores = insertarPuntuacion(nombre, puntuacion,
        nombresRanking, puntuacionesRanking, cantidadJugadores);

        guardarRanking(nombresRanking, puntuacionesRanking, cantidadJugadores);

        return cantidadJugadores;

        
    }

    public static int explorarInicio(Scanner teclado, Personaje heroe, Personaje enemigo1, Personaje enemigo2, Personaje enemigo3, int[] pociones, Armas[] inventarioArmas, HabilidadEspecial habilidadElegida, int[] usosHabilidad) 
    { 

        String opcion;
        int puntuacion = 0;

        System.out.println("\nDespiertas en una aldea abandonada...");

        do {
            mostrarVida(heroe);

            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Caminar");
            System.out.println("2. Hablar con el comerciante");
            System.out.println("3. Ver inventario");
            System.out.println("4. Abandonar la partida");
            

            opcion = teclado.nextLine();

            switch (opcion) {

                case "1":

                    System.out.println("\nAvanzas hacia la fortaleza y te encuentras con el Centinela.");

                    if (mostrarmenucombate(teclado, heroe, enemigo1, pociones, inventarioArmas, habilidadElegida, usosHabilidad)) {
                        
                        System.out.println("Avanzas y te encuentras con un Caballero.");
                        puntuacion += 100;

                        if (mostrarmenucombate(teclado, heroe, enemigo2, pociones, inventarioArmas, habilidadElegida, usosHabilidad)) {

                            System.out.println("Avanzas y te encuentras con el Guardian final.");
                            puntuacion += 100;

                            if (mostrarmenucombate(teclado, heroe, enemigo3, pociones, inventarioArmas, habilidadElegida, usosHabilidad)) {

                                System.out.println("¡Has completado la aventura!");
                                puntuacion += 100;
                                puntuacion += heroe.getVida();
                            }
                        }
                    }
                    return puntuacion;

                case "2":
                    System.out.println("Comerciante: Ten cuidado con los guardianes.");
                    break;

                case "3":
                    mostrarInventario(heroe, pociones, inventarioArmas);
                    break;

                case "4":
                    System.out.println("Has decidido abandonar la aventura.");
                    return puntuacion;

                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }

        } while (!opcion.equals("4"));
        
        return puntuacion;
    }

    public static boolean mostrarmenucombate(Scanner teclado, Personaje heroe,
            Personaje enemigo, int[] pociones, Armas[] inventarioArmas, HabilidadEspecial habilidadElegida, int[] usosHabilidad) {

        System.out.println("\nTe enfrentas a " + enemigo.getNombre());

        String opcion;

        do {
            System.out.println("\n-- Combate --");

            mostrarVida(heroe);

            System.out.println("Vida de " + enemigo.getNombre() + ": "
                    + enemigo.getVida() + "/" + enemigo.getVidaMax());

            System.out.println("1. Atacar");
            System.out.println("2. Curarse");
            System.out.println("3. Ver inventario");
            System.out.println("4. Rendirse");
            System.out.println("5. Usar habilidad especial (1 uso por partida)");

            opcion = teclado.nextLine();

            boolean contraataca = false;

            switch (opcion) {

                case "1":

                    int danyoHeroe = (int) (Math.random() *
                            (heroe.getAtaqueMax() - heroe.getAtaqueMin() + 1)
                            + heroe.getAtaqueMin());

                    enemigo.setVida(enemigo.getVida() - danyoHeroe);

                    System.out.println("Has infligido " + danyoHeroe + " de daño.");

                    if (enemigo.getVida() == 0) {
                        System.out.println("Has derrotado a " + enemigo.getNombre() + ".");

                        if (!enemigo.getNombre().equals("Guardian final")) {

                            System.out.println("Quieres curarate con una poción? (si/no)");
                            String respuesta = teclado.nextLine();

                            if (respuesta.equalsIgnoreCase("si")) {
                                {
                                    if (contarPociones(pociones) == 0) {
                                        System.out.println("No te quedan pociones.");
                                    } else if (heroe.getVida() == heroe.getVidaMax()) {
                                        System.out.println("Ya tienes la vida al máximo.");
                                    } else {
                                        for (int i = 0; i < pociones.length; i++) {

                                            if (pociones[i] > 0) {

                                                heroe.setVida(heroe.getVida() + pociones[i]);
                                                pociones[i] = 0;

                                                System.out.println("Has usado una poción.");
                                                mostrarVida(heroe);

                                                break;

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        contraataca = true;
                    }

                    break;

                case "2":

                    if (contarPociones(pociones) == 0) {
                        System.out.println("No te quedan pociones.");
                    } else if (heroe.getVida() == heroe.getVidaMax()) {
                        System.out.println("Ya tienes la vida al máximo.");
                    } else {

                        for (int i = 0; i < pociones.length; i++) {

                            if (pociones[i] > 0) {

                                heroe.setVida(heroe.getVida() + pociones[i]);

                                pociones[i] = 0;

                                System.out.println("Has usado una poción.");
                                mostrarVida(heroe);

                                contraataca = true;
                                break;
                            }
                        }
                    }

                    break;

                case "3":
                    mostrarInventario(heroe, pociones, inventarioArmas);
                    break;

                case "4":
                    System.out.println("Te has rendido.");
                    System.out.println("La aventura ha terminado.");
                    return false;
                
                 case "5": 
                    if (usosHabilidad[0] == 0) {
                        System.out.println("Ya has usado tu habilidad especial en esta partida.");
                    } else { 
                        int danyo = habilidadElegida.usarHabilidad();

                        enemigo.setVida(enemigo.getVida() - danyo);

                        usosHabilidad[0] = 0;

                        System.out.println("Has causado " + danyo + " de daño.");

        if (enemigo.getVida() > 0) {
            contraataca = true;
        }
                    }
                    break;

                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }

            if (contraataca && enemigo.getVida() > 0) {

                int danyoEnemigo = (int) (Math.random() *
                        (enemigo.getAtaqueMax() - enemigo.getAtaqueMin() + 1)
                        + enemigo.getAtaqueMin());

                heroe.setVida(heroe.getVida() - danyoEnemigo);

                System.out.println("El enemigo te ha infligido "
                        + danyoEnemigo + " de daño.");

                mostrarVida(heroe);

                if (heroe.getVida() == 0) {
                    System.out.println("Has sido derrotado.");
                }
            }

        } while (!opcion.equals("4")
                && heroe.getVida() > 0
                && enemigo.getVida() > 0);

        return enemigo.getVida() == 0 && heroe.getVida() > 0;
    }

    public static void mostrarVida(Personaje heroe) {

        System.out.println("Vida de " + heroe.getNombre()
                + ": " + heroe.getVida()
                + "/" + heroe.getVidaMax());
    }

    public static int contarPociones(int[] pociones) {

        int cantidad = 0;

        for (int i = 0; i < pociones.length; i++) {

            if (pociones[i] > 0) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public static void mostrarInventario(Personaje heroe, int[] pociones, Armas[] inventarioArmas) {

        mostrarVida(heroe);

        System.out.println("Pociones disponibles: "
                + contarPociones(pociones));

        System.out.println("Tu arma actual es: " + inventarioArmas[0].getNombre());
    }

    public static Armas seleccionarArma(Scanner teclado) {

        String opcion;

        while (true) {
            System.out.println("\nSelecciona un arma:");
            System.out.println("1. Colmillo Sombrio (10-14 de daño y 30% de probabilidad de esquivar)");
            System.out.println("2. Juramento de Acero (12-17 de daño y 15% de probabilidad de esquivar)");
            System.out.println("3. Ira del Titan (15-20 de daño y 0% de probabilidad de esquivar)");

            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    return new Armas("Colmillo Sombrio", 10, 14, 30);
                case "2":
                    return new Armas("Juramento de Acero", 12, 17, 15);
                case "3":
                    return new Armas("Ira del Titan", 15, 20, 0);

                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }
        }
    }

    public static void mostrarAyuda(Scanner teclado) {

        System.out.println("\n====================================");
        System.out.println("       EL DESPERTAR DEL HEROE");
        System.out.println("         GUIA DEL AVENTURERO");
        System.out.println("====================================");

        System.out.println("\n--- LA HISTORIA ---");
        System.out.println("Despiertas en una aldea abandonada.");
        System.out.println("Una antigua fortaleza se alza ante ti.");
        System.out.println("Tres enemigos custodian el camino.");
        System.out.println("Tu mision es derrotarlos y sobrevivir.");

        System.out.println("\n--- OBJETIVO ---");
        System.out.println("Derrota a los tres enemigos en orden:");
        System.out.println("1. Centinela: 40 puntos de vida.");
        System.out.println("2. Caballero: 60 puntos de vida.");
        System.out.println("3. Guardian final: 80 puntos de vida.");

        System.out.println("\n--- COMO JUGAR ---");
        System.out.println("Comienzas con 100 puntos de vida.");
        System.out.println("Desde la aldea puedes explorar,");
        System.out.println("hablar con el comerciante o ver tu inventario.");
        System.out.println("Selecciona Caminar para iniciar la aventura.");

        System.out.println("\n--- DURANTE EL COMBATE ---");
        System.out.println("1. Atacar: causas entre 10 y 20 de daño.");
        System.out.println("2. Curarse: recuperas hasta 25 de vida.");
        System.out.println("3. Inventario: consultas tus pociones.");
        System.out.println("4. Rendirse: abandonas el combate.");

        System.out.println("\n--- POCIONES ---");
        System.out.println("Dispones de 3 pociones para toda la aventura.");
        System.out.println("Cada una recupera hasta 25 puntos de vida.");
        System.out.println("Nunca puedes superar los 100 puntos de vida.");

        System.out.println("\n--- REGLAS ---");
        System.out.println("Los enemigos causan entre 5 y 15 de daño.");
        System.out.println("Los enemigos contraatacan si sobreviven");
        System.out.println("a tu ataque o utilizas una pocion valida.");
        System.out.println("Consultar el inventario no consume turno.");
        System.out.println("Tu vida se conserva entre enfrentamientos.");

        System.out.println("\n--- VICTORIA Y DERROTA ---");
        System.out.println("Ganas al derrotar al Guardian final.");
        System.out.println("Pierdes si tu vida llega a cero.");
        System.out.println("Tambien puedes abandonar la aventura.");

        System.out.println("\nBuena suerte, aventurero.");
        System.out.println("====================================\n");

        System.out.println("Presiona Enter para volver al menú principal...");
        teclado.nextLine();

    }

    public static HabilidadEspecial seleccionarClase(Scanner teclado) {

    String opcion;

    while (true) {

        System.out.println("\nSelecciona tu clase:");
        System.out.println("1. Guerrero (Aragorn)");
        System.out.println("2. Mago (Gandalf)");
        System.out.println("3. Arquero (Legolas)");

        opcion = teclado.nextLine();

        switch (opcion) {

            case "1":
                System.out.println("Has elegido Guerrero.");
                return new Guerrero();

            case "2":
                System.out.println("Has elegido Mago.");
                return new Mago();

            case "3":
                System.out.println("Has elegido Arquero.");
                return new Arquero();

            default:
                System.out.println("Opción incorrecta.");
                continue;
            }
        }
    }

    public static int cargarRanking(String[] nombresRanking,
        int[] puntuacionesRanking)
    {

        File archivo = new File("Ranking.xml");

        if (!archivo.exists()) 
        {
        return 0;
        }

        int cantidadJugadores = 0;

        try {
            DocumentBuilderFactory fabrica =
                    DocumentBuilderFactory.newInstance();

            fabrica.setFeature(
                    "http://apache.org/xml/features/nonvalidating/load-external-dtd",
                    false);

            fabrica.setFeature(
                    "http://xml.org/sax/features/external-general-entities",
                    false);

            fabrica.setFeature(
                    "http://xml.org/sax/features/external-parameter-entities",
                    false);

            DocumentBuilder constructor = fabrica.newDocumentBuilder();

            Document documento = constructor.parse(archivo);
            documento.getDocumentElement().normalize();

            NodeList lista = documento.getElementsByTagName("jugador");

            for (int i = 0; i < lista.getLength(); i++) {

                Element jugador = (Element) lista.item(i);

                String nombre = jugador.getElementsByTagName("nombre")
                    .item(0).getTextContent();

                int puntuacion = Integer.parseInt(
                    jugador.getElementsByTagName("puntuacion")
                            .item(0).getTextContent().trim());

                cantidadJugadores = insertarPuntuacion(
                    nombre, puntuacion,
                    nombresRanking, puntuacionesRanking,
                    cantidadJugadores);
            }

        } catch (Exception e) {
            System.out.println("Error leyendo ranking: " + e.getMessage());
        }

        return cantidadJugadores;
    }

    public static void mostrarRanking(Scanner teclado, String[] nombresRanking,
        int[] puntuacionesRanking, int cantidadJugadores) {

        System.out.println("\n===== RANKING DE HEROES =====");

        if (cantidadJugadores == 0) {
            System.out.println("Todavia no hay puntuaciones.");
        } else {

            for (int i = 0; i < cantidadJugadores; i++) {

                System.out.println((i + 1) + ". "
                        + nombresRanking[i] + " - "
                        + puntuacionesRanking[i] + " puntos");
            }
        }

        System.out.println("\nPresiona Enter para volver al menú principal...");
        teclado.nextLine();


    }

    public static int insertarPuntuacion(String nombre, int puntuacion,
        String[] nombresRanking, int[] puntuacionesRanking,
        int cantidadJugadores) {

        int posicion = 0;

        while (posicion < cantidadJugadores
            && puntuacion <= puntuacionesRanking[posicion]) {

            posicion++;
        }

        if (posicion >= nombresRanking.length) {
        return cantidadJugadores;
        }

        int ultimaPosicion = cantidadJugadores;

        if (ultimaPosicion == nombresRanking.length) {
            ultimaPosicion = nombresRanking.length - 1;
        }

        for (int i = ultimaPosicion; i > posicion; i--) {

            nombresRanking[i] = nombresRanking[i - 1];
            puntuacionesRanking[i] = puntuacionesRanking[i - 1];
        }

        nombresRanking[posicion] = nombre;
        puntuacionesRanking[posicion] = puntuacion;

        if (cantidadJugadores < nombresRanking.length) {
            cantidadJugadores++;
        }

        return cantidadJugadores;
    }

    public static void guardarRanking(String[] nombresRanking,
        int[] puntuacionesRanking, int cantidadJugadores) {

        try {
            DocumentBuilderFactory fabrica =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder constructor = fabrica.newDocumentBuilder();

            Document documento = constructor.newDocument();

            Element raiz = documento.createElement("Ranking");
            documento.appendChild(raiz);

            for (int i = 0; i < cantidadJugadores; i++) {

                Element jugador = documento.createElement("jugador");
                raiz.appendChild(jugador);

                Element nombre = documento.createElement("nombre");
                nombre.setTextContent(nombresRanking[i]);
                jugador.appendChild(nombre);

                Element puntuacion = documento.createElement("puntuacion");
                puntuacion.setTextContent(String.valueOf(puntuacionesRanking[i]));
                jugador.appendChild(puntuacion);
            }

            TransformerFactory transformerFabrica = TransformerFactory.newInstance();

            Transformer transformer = transformerFabrica.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "Ranking.dtd");

            DOMSource origen = new DOMSource(documento);

            StreamResult destino = new StreamResult(new File("Ranking.xml"));

            transformer.transform(origen, destino);

            System.out.println("Ranking guardado correctamente.");

        } catch (Exception e) {
        System.out.println("Error guardando ranking: " + e.getMessage());
        }
    }
}
