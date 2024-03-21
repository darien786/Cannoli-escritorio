/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.modelo.dao;

import cannoli.modelo.ConexionHTTP;
import cannoli.modelo.pojo.CodigoHTTP;
import cannoli.modelo.pojo.DatosRegistroEmpleado;
import cannoli.modelo.pojo.Empleado;
import cannoli.modelo.pojo.Estatus;
import cannoli.modelo.pojo.Mensaje;
import cannoli.utils.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cr7_k
 */
public class EmpleadoDAO {
    
    public static List<Empleado> obtenerEmpleados(){
        List<Empleado> empleados = new ArrayList<>();
        String url = Constantes.URL_WS + "empleados/obtenerEmpleados";
        CodigoHTTP respuesta = ConexionHTTP.peticionGET(url);
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            Type arrayListEmpleados = new TypeToken<ArrayList<Empleado>>(){}.getType();
            empleados = gson.fromJson(respuesta.getContenido(), arrayListEmpleados);
        }
        
        return empleados;
    }
    
    public static Mensaje registrarEmpleado(DatosRegistroEmpleado empleado){
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "empleados/registrarEmpleados";

        Gson gson = new Gson();
        String parametros = gson.toJson(empleado);
        CodigoHTTP respuesta = ConexionHTTP.peticionPOST(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            mensaje.setError(true);
            mensaje.setMensaje("Error en la petición para registrar el empleado");
        }

        return mensaje;
    }
    
    public static Mensaje modificarEmpleado(DatosRegistroEmpleado empleado){
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "empleados/modificarEmpleado";
        
        Gson gson = new Gson();
        String parametros = gson.toJson(empleado);
        CodigoHTTP respuesta = ConexionHTTP.peticionPUT(url, parametros);
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        }else{
            mensaje.setError(Boolean.TRUE);
            mensaje.setMensaje("Error en la petición para modificar al empleado");
        }
        
        return mensaje;
    }
    
    public static DatosRegistroEmpleado obtenerEmpleadoPorId(Integer idEmpleado){
        DatosRegistroEmpleado empleadoSolicitado = null;
        String url = Constantes.URL_WS + "empleados/obtenerEmpleadoPorId/" + idEmpleado;
        CodigoHTTP respuesta = ConexionHTTP.peticionGET(url);
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
             Gson gson = new Gson();
            empleadoSolicitado = gson.fromJson(respuesta.getContenido(), DatosRegistroEmpleado.class);
        }
        
        return empleadoSolicitado;
    }
    
}
