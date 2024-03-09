/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.modelo.pojo;

/**
 *
 * @author cr7_k
 */
public class Estatus {
    private Integer idEstatus;
    private String nombreEstatus;

    public Estatus(Integer idEstatus, String nombreEstatus) {
        this.idEstatus = idEstatus;
        this.nombreEstatus = nombreEstatus;
    }

    public Estatus() {
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public String getNombreEstatus() {
        return nombreEstatus;
    }

    public void setNombreEstatus(String nombreEstatus) {
        this.nombreEstatus = nombreEstatus;
    }

    @Override
    public String toString() {
        return nombreEstatus;
    }
    
    
}
