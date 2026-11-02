import java.util.Scanner;

public class Proyecto {

    public static void main(String[] args)
    {
        Scanner teclado = new Scanner (System.in);

        System.out.println("El despertar del héroe");
        String iniciar;
        int canPociones = 3;
        int vida;
        int vidaMax = 100;
        int pocion = 25;
        int ataqueMax = 20;
        int ataqueMin = 10;
        int vidaMin = 0;
        int vidaenemigo1 = 40;
        int vidaenemigo2 = 60;
        int vidaenemigo3 = 80;
        int ataqueenemigoMin = 5;
        int ataqueenemigoMax = 15;
        int danyoHeroe;
        int danyoEnemigo;

        do {
            
            System.out.println("¿Quieres iniciar el juego? (si/no)");

            iniciar = teclado.nextLine();

            if (iniciar.equalsIgnoreCase("si"))
                {
                System.out.println("El juego se ha iniciado correctamente");
                }
            else if (iniciar.equalsIgnoreCase("no")) 
                {
                System.out.println("Has decidido salir del juego");
                }
            else {
                System.out.println("Respuesta incorrecta. Escribe si o no.");
            }

        } while (!iniciar.equalsIgnoreCase("si") &&
                 !iniciar.equalsIgnoreCase("no"));

        if (iniciar.equalsIgnoreCase("si"))
        {
            int opcion;

            do 
            {
            System.out.println("\n -- Menú principal --");

            System.out.println("1. Nueva partida");
            System.out.println("2. Consultar ranking");
            System.out.println("3. Ayuda");
            System.out.println("4. Salir");

            System.out.println("\nSelecciona una opción");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:   
                     iniciarPartida(teclado);
                    break;

                case 2:
                     System.out.println("Mostrando ranking..."); 
                    break;

                case 3:
                     System.out.println("Mostrando ayuda..."); 
                    break;

                case 4:
                     System.out.println("Saliendo del juego..."); 
                    break;

                default:
                    System.out.println("Opción incorrecta, selecciona 1, 2, 3 o 4.");
                    break;
                    
                }
            } while (opcion < 1 && opcion < 4 );

            
                

        }
    }

    public static void iniciarPartida(Scanner teclado)
    {
        String nombre;

        System.out.println("Iniciando nueva partida...");

        do {
        
        System.out.print("\nIntroduce el nombre del héroe: ");
        nombre = teclado.nextLine();

        if (nombre.isEmpty()) {
            System.out.println("\nError: El nombre no puede estar vacío");
        }
        
        } while (nombre.isEmpty());

         Personaje heroe = new Personaje(nombre, 100, 0, 10, 20);
         System.out.println("Hola " + nombre + ", ahora eres un héroe.");

         System.out.println("Vida: " + heroe.getVida());
         System.out.println("Ataque mínimo: " + heroe.getAtaqueMin());
         System.out.println("Ataque máximo: " + heroe.getAtaqueMax());
         
         explorarInicio(teclado, heroe);


    }

    public static void explorarInicio(Scanner teclado, Personaje heroe)
    {
    String opcion;

    System.out.println("\nDespiertas en una aldea abandonada...");
    mostrarVida(heroe);

    do {
        System.out.println("\n¿Qué deseas hacer?");
        System.out.println("1. Caminar");
        System.out.println("2. Hablar con el comerciante");
        System.out.println("3. Ver inventario");
        System.out.println("4. Abandonar la partida");

        opcion = teclado.nextLine();

        switch (opcion) {

            case "1":
                mostrarmenucombate(teclado, heroe);
                break;

            case "2":
                System.out.println("Comerciante: Ten cuidado con los guardianes.");
                break;

            case "3":
                System.out.println("Este es tu inventario.");
                break;

            case "4":
                System.out.println("Has decidido abandonar la aventura.");
                break;

            default:
                System.out.println("Opción incorrecta.");
                break;
        }

        } while (!opcion.equals("1") && !opcion.equals("4"));
    }

    public static void mostrarmenucombate(Scanner teclado, Personaje heroe)
    {
        System.out.println("\nAvanzas hacia la fortaleza...");
        System.out.println("\nUn enemigo aparece frente a ti.");

        Personaje enemigo = new Personaje("Centinela", 40, 0, 5, 15);

        String opcion;

        do 
        {
            System.out.println("\n--Combate--");
            System.out.println("\n¿Que quieres hacer?");
            System.out.println("1. Atacar");
            System.out.println("2. Curarse");
            System.out.println("3. Ver inventario");
            System.out.println("4. Rendirse");

            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("Has elegido atacar.");

                    int danyoHeroe = (int) (Math.random() * (heroe.getAtaqueMax() - heroe.getAtaqueMin() + 1) + heroe.getAtaqueMin());
                    int vidaenemigo1 = enemigo.getVida() - danyoHeroe;

                    if (vidaenemigo1 <= 0) {
                        System.out.println("Has derrotado al enemigo.");
                    }

                    enemigo.setVida(vidaenemigo1);
                    System.out.println("Has infligido " + danyoHeroe + " de daño al enemigo. Vida restante del enemigo: " + enemigo.getVida());

                    if (enemigo.getVida() > 0) {
                        int danyoEnemigo = (int) (Math.random() * (enemigo.getAtaqueMax() - enemigo.getAtaqueMin() + 1) + enemigo.getAtaqueMin());
                        int vidaHeroe = heroe.getVida() - danyoEnemigo;

                        heroe.setVida(vidaHeroe);
                        System.out.println("El enemigo te ha infligido " + danyoEnemigo + " de daño. Tu vida restante: " + heroe.getVida());
                    }

                    if (heroe.getVida() == 0){
                        System.out.println("Has sido derrotado por el enemigo.");
                    }
                    else {
                        System.out.println("Has derrotado al Centinela.");
                    }
                    break;
                 case "2":
                    System.out.println("Has elegido curarte.");
                    break;
                 case "3":
                    System.out.println("Mostrando inventario...");
                    break;
                 case "4":
                    System.out.println("Te has rendido.");
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }
        }while (opcion != "4");

    }

    public static void mostrarVida(Personaje heroe)
    {
            System.out.println("Vida de " + heroe.getNombre()
            + ": " + heroe.getVida()
            + "/" + heroe.getVidaMax());
    }
     
}
