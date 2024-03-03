/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.modelo.dao;

import cannoli.modelo.ConexionHTTP;
import cannoli.modelo.pojo.CodigoHTTP;
import cannoli.modelo.pojo.Empleado;
import cannoli.modelo.pojo.MensajeAutenticacion;
import cannoli.utils.Constantes;
import com.google.gson.Gson;
import java.net.HttpURLConnection;

/**
 *
 * @author cr7_k
 */
public class AutenticacionDAO {
    
    public static MensajeAutenticacion validarSesion(String username, String password) {
        MensajeAutenticacion respuestaWS = new MensajeAutenticacion();
        Gson gson = new Gson();
        Empleado empleado = new Empleado();
        
        empleado.setUsername(username);
        empleado.setContrasenia(password);
        
        String url = Constantes.URL_WS + "autenticacion/inicioSesionEscritorio";
        String parametros = gson.toJson(empleado);
        
        CodigoHTTP respuestaConexion = ConexionHTTP.peticionPOST(url, parametros);

        if (respuestaConexion.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            respuestaWS = gson.fromJson(respuestaConexion.getContenido(), MensajeAutenticacion.class);
        } else {
            respuestaWS.setError(true);
            respuestaWS.setMensaje("Hubo un error al realizar la petición, favor de intentarlo mas tarde");
        }

        return respuestaWS;
    }
}
