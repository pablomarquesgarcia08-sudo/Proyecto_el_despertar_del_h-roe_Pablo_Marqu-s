import java.util.Scanner;

public class Proyecto {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.println("El despertar del héroe");

        String iniciar;

        do {
            System.out.println("¿Quieres iniciar el juego? (si/no)");
            iniciar = teclado.nextLine();

            if (iniciar.equalsIgnoreCase("si")) {
                System.out.println("El juego se ha iniciado correctamente");
            }
            else if (iniciar.equalsIgnoreCase("no")) {
                System.out.println("Has decidido salir del juego");
            }
            else {
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
                        iniciarPartida(teclado);
                        break;

                    case "2":
                        System.out.println("Mostrando ranking...");
                        break;

                    case "3":
                        System.out.println("Mostrando ayuda...");
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

    public static void iniciarPartida(Scanner teclado) {

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
        int[] pociones = {25, 25, 25};

        inventarioArmas[0] = seleccionarArma(teclado);

        System.out.println("Hola " + nombre + ", ahora eres un héroe.");
        mostrarVida(heroe);

        explorarInicio(teclado, heroe, enemigo1, enemigo2, enemigo3, pociones, inventarioArmas);
    }

    public static void explorarInicio(Scanner teclado, Personaje heroe,
                                      Personaje enemigo1, Personaje enemigo2,
                                      Personaje enemigo3, int[] pociones, Armas[] inventarioArmas) {

        String opcion;

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

                    if (mostrarmenucombate(teclado, heroe, enemigo1, pociones, inventarioArmas)) {

                        System.out.println("Avanzas y te encuentras con un Caballero.");

                        if (mostrarmenucombate(teclado, heroe, enemigo2, pociones, inventarioArmas)) {

                            System.out.println("Avanzas y te encuentras con el Guardian final.");

                            if (mostrarmenucombate(teclado, heroe, enemigo3, pociones, inventarioArmas)) {

                                System.out.println("¡Has completado la aventura!");
                            }
                        }
                    }

                    return;

                case "2":
                    System.out.println("Comerciante: Ten cuidado con los guardianes.");
                    break;

                case "3":
                    mostrarInventario(heroe, pociones, inventarioArmas);
                    break;

                case "4":
                    System.out.println("Has decidido abandonar la aventura.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }

        } while (!opcion.equals("4"));
    }

    public static boolean mostrarmenucombate(Scanner teclado, Personaje heroe,
                                              Personaje enemigo, int[] pociones, Armas[] inventarioArmas) {

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
                                    }
                                    else if (heroe.getVida() == heroe.getVidaMax()) {
                                    System.out.println("Ya tienes la vida al máximo.");
                                    }
                                    else {
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
                    }
                    else {
                        contraataca = true;
                    }

                    break;

                
                case "2":

                    if (contarPociones(pociones) == 0) {
                        System.out.println("No te quedan pociones.");
                    }
                    else if (heroe.getVida() == heroe.getVidaMax()) {
                        System.out.println("Ya tienes la vida al máximo.");
                    }
                    else {

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
                    System.exit(0);
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
}