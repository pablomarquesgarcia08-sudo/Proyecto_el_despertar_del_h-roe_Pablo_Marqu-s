public class Mago implements HabilidadEspecial {

    //Esta clase mago es la habilidad de gandalf
    @Override
    public int usarHabilidad() {
       
        System.out.println("El mago lanza un hechizo de fuego!");
        return 25;
    }
}
