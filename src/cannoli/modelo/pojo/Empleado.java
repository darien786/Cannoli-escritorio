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
public class Empleado {
    private Integer idEmpleado;
    private Integer persona;
    private String curp;
    private String correo;
    private String username;
    private String contrasenia;
    private String fotografia;
    private Integer rol;
    private Integer estatus;
    private String nombreEmpleado;
    private String telefono;
    
    public Empleado() {
    }

    public Empleado(Integer idEmpleado, Integer persona, String curp, String correo, String username, String contrasenia, String fotografia, Integer rol, Integer estatus) {
        this.idEmpleado = idEmpleado;
        this.persona = persona;
        this.curp = curp;
        this.correo = correo;
        this.username = username;
        this.contrasenia = contrasenia;
        this.fotografia = fotografia;
        this.rol = rol;
        this.estatus = estatus;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getPersona() {
        return persona;
    }

    public void setPersona(Integer persona) {
        this.persona = persona;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
