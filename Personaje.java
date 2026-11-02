public class Personaje 
{

    private String nombre;
    private int vida; 
    private int vidaMax;
    private int vidaMin;
    private int ataqueMin;
    private int ataqueMax;


    public Personaje(String nombre, int vidaMax, int vidaMin, int ataqueMin, int ataqueMax)
    {
        this.nombre = nombre;
        this.vidaMax = vidaMax;
        this.vidaMin = vidaMin;
        this.vida = vidaMax;
        this.ataqueMin = ataqueMin;
        this.ataqueMax = ataqueMax;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getVida() {
        return vida;
    }


    public void setVida(int vida) {
        if (vida < vidaMin) {
            this.vida = vidaMin;
        } else if (vida > vidaMax) {
            this.vida = vidaMax;
        } else {
            this.vida = vida;
        }
    }


    public int getVidaMax() {
        return vidaMax;
    }


    public void setVidaMax(int vidaMax) {
        this.vidaMax = vidaMax;
    }


    public int getVidaMin() {
        return vidaMin;
    }


    public void setVidaMin(int vidaMin) {
        this.vidaMin = vidaMin;
    }


    public int getAtaqueMin() {
        return ataqueMin;
    }


    public void setAtaqueMin(int ataqueMin) {
        this.ataqueMin = ataqueMin;
    }


    public int getAtaqueMax() {
        return ataqueMax;
    }


    public void setAtaqueMax(int ataqueMax) {
        this.ataqueMax = ataqueMax;
    }

    
}
