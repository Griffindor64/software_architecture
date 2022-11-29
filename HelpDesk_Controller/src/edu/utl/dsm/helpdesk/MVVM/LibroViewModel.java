
package edu.utl.dsm.helpdesk.MVVM;

/**
 *
 * @author Vargas
 */
public class LibroViewModel {
    private int id_lbr;
    private String nombre_lbr, descripcion_lbr, tema_lbr;
    
    public LibroViewModel(String nombre_lbr, String descripcion_lbr, String tema_lbr) {
        this.nombre_lbr = nombre_lbr;
        this.descripcion_lbr = descripcion_lbr;
        this.tema_lbr = tema_lbr;
    }
    
    public int getId_lbr() {
        return id_lbr;
    }

    public void setId_lbr(int id_lbr) {
        this.id_lbr = id_lbr;
    }

    public String getNombre_lbr() {
        return nombre_lbr;
    }

    public void setNombre_lbr(String nombre_lbr) {
        this.nombre_lbr = nombre_lbr;
    }

    public String getDescripcion_lbr() {
        return descripcion_lbr;
    }

    public void setDescripcion_lbr(String descripcion_lbr) {
        this.descripcion_lbr = descripcion_lbr;
    }

    public String getTema_lbr() {
        return tema_lbr;
    }

    public void setTema_lbr(String tema_lbr) {
        this.tema_lbr = tema_lbr;
    }

    public LibroViewModel() {
    }

    public LibroViewModel(int id_lbr, String nombre_lbr, String descripcion_lbr, String tema_lbr) {
        this.id_lbr = id_lbr;
        this.nombre_lbr = nombre_lbr;
        this.descripcion_lbr = descripcion_lbr;
        this.tema_lbr = tema_lbr;
    }

    @Override
    public String toString() {
        return "LibroViewModel{" + "id_lbr=" + id_lbr + ", nombre_lbr=" + nombre_lbr + ", descripcion_lbr=" + descripcion_lbr + ", tema_lbr=" + tema_lbr + '}';
    }

}
