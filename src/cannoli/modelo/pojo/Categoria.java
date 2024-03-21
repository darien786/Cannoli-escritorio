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
public class Categoria {
    private Integer idCategoria;
    private String nombreCategoria;
    private String descripcion;
    private Integer estatus;
    private String nombreEstatus;

    public Categoria() {
    }

    public Categoria(Integer idCategoria, String nombreCategoria, String descripcion, Integer estatus, String nombreEstatus) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcion = descripcion;
        this.estatus = estatus;
        this.nombreEstatus = nombreEstatus;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public String getNombreEstatus() {
        return nombreEstatus;
    }

    public void setNombreEstatus(String nombreEstatus) {
        this.nombreEstatus = nombreEstatus;
    }
    
    @Override
    public String toString(){
        return nombreCategoria;
    }
}
