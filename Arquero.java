public class Arquero implements HabilidadEspecial {

    //Esta clase Arquero es la habilidad de legolas

    @Override
    public int usarHabilidad() {
       
        int primerDisparo = (int) (Math.random() * 5) + 8; 
        int segundoDisparo = (int) (Math.random() * 5) + 8;
        int danyototal = primerDisparo + segundoDisparo;

        System.out.println("El arquero dispara dos flechas precisas!");
        return danyototal;
    }
    
}
