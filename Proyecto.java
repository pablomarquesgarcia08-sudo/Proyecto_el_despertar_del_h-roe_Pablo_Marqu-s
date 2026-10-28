import java.util.Scanner;

public class Proyecto {

    public static void main(String[] args)
    {
        Scanner teclado = new Scanner (System.in);

        System.out.println("El despertar del héroe");
        String iniciar;
        int canPociones = 3;
        int vidaMax = 100;



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
        System.out.print("Introduce el nombre del héroe: ");

        nombre = teclado.nextLine();

        System.out.println("Hola " + nombre + ", ahora eres un héroe.");
         
    }
     
}
