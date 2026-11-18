import java.util.Scanner;

public class Combate {

    public static boolean mostrarmenucombate(Scanner teclado, Personaje heroe,
        Personaje enemigo, int[] pociones, Armas[] inventarioArmas,
        HabilidadEspecial habilidadElegida, int[] usosHabilidad)
        {

         System.out.println("\nTe enfrentas a " + enemigo.getNombre());

        String opcion;

        do {
            System.out.println("\n-- Combate --");

            Proyecto.mostrarVida(heroe);

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
                    if (Math.random() * 100 < inventarioArmas[0].getProbabilidadFallo()) {

                    System.out.println("¡Has fallado el ataque!");

                    contraataca = true;
                    break;
            
                    }

                    int danyoHeroe = (int) (Math.random() *
                            (inventarioArmas[0].getAtaqueMax()
                            - inventarioArmas[0].getAtaqueMin() + 1)
                            + inventarioArmas[0].getAtaqueMin());

                    enemigo.setVida(enemigo.getVida() - danyoHeroe);

                    System.out.println("Has infligido " + danyoHeroe + " de daño.");

                    if (enemigo.getVida() == 0) {
                        System.out.println("Has derrotado a " + enemigo.getNombre() + ".");

                        if (!enemigo.getNombre().equals("Guardian final")) {

                            System.out.println("¿Quieres curarte con una poción? (si/no)");
                            String respuesta = teclado.nextLine();

                            if (respuesta.equalsIgnoreCase("si")) {
                                
                                    if (Proyecto.contarPociones(pociones) == 0) {
                                        System.out.println("No te quedan pociones.");
                                    } else if (heroe.getVida() == heroe.getVidaMax()) {
                                        System.out.println("Ya tienes la vida al máximo.");
                                    } else {
                                        for (int i = 0; i < pociones.length; i++) {

                                            if (pociones[i] > 0) {

                                                heroe.setVida(heroe.getVida() + pociones[i]);
                                                pociones[i] = 0;

                                                System.out.println("Has usado una poción.");
                                                Proyecto.mostrarVida(heroe);

                                                break;

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

                    if (Proyecto.contarPociones(pociones) == 0) {
                        System.out.println("No te quedan pociones.");
                    } else if (heroe.getVida() == heroe.getVidaMax()) {
                        System.out.println("Ya tienes la vida al máximo.");
                    } else {

                        for (int i = 0; i < pociones.length; i++) {

                            if (pociones[i] > 0) {

                                heroe.setVida(heroe.getVida() + pociones[i]);

                                pociones[i] = 0;

                                System.out.println("Has usado una poción.");
                                Proyecto.mostrarVida(heroe);

                                contraataca = true;
                                break;
                            }
                        }
                    }

                    break;

                case "3":
                    Proyecto.mostrarInventario(heroe, pociones, inventarioArmas);
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

                Proyecto.mostrarVida(heroe);

                if (heroe.getVida() == 0) {
                    System.out.println("Has sido derrotado.");
                }
            }

        } while (!opcion.equals("4")
                && heroe.getVida() > 0
                && enemigo.getVida() > 0);

        return enemigo.getVida() == 0 && heroe.getVida() > 0;
    }
    
}
