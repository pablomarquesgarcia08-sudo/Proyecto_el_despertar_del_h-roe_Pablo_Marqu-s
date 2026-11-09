public class Guerrero implements HabilidadEspecial {

    //Esta clase Guerrero es la habilidad de aragorn

    @Override
    public int usarHabilidad() {
       
        System.out.println("Has realizado un ataque poderoso!");
        return 20;
    }
    
}
