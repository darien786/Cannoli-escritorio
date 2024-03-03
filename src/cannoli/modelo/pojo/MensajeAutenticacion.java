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
public class MensajeAutenticacion {
    private boolean error;
    private String mensaje;
    private Empleado empleado;

    public MensajeAutenticacion() {
    }

    public MensajeAutenticacion(boolean error, String mensaje, Empleado empleado) {
        this.error = error;
        this.mensaje = mensaje;
        this.empleado = empleado;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
