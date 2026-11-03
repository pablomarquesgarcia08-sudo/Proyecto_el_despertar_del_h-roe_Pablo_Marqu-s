public class Armas {

    private String nombre;
    private int ataqueMax;
    private int ataqueMin;
    private int probabilidadesquivar;

    public Armas(String nombre, int ataqueMin, int ataqueMax, int probabilidadesquivar) {
        this.nombre = nombre;
        this.ataqueMin = ataqueMin;
        this.ataqueMax = ataqueMax;
        this.probabilidadesquivar = probabilidadesquivar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAtaqueMax() {
        return ataqueMax;
    }

    public void setAtaqueMax(int ataqueMax) {
        this.ataqueMax = ataqueMax;
    }

    public int getAtaqueMin() {
        return ataqueMin;
    }

    public void setAtaqueMin(int ataqueMin) {
        this.ataqueMin = ataqueMin;
    }

    public int getProbabilidadesquivar() {
        return probabilidadesquivar;
    }

    public void setProbabilidadesquivar(int probabilidadesquivar) {
        this.probabilidadesquivar = probabilidadesquivar;
    }
    
}
