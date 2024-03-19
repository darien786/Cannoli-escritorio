/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.modelo.dao;

import cannoli.modelo.ConexionHTTP;
import cannoli.modelo.pojo.CodigoHTTP;
import cannoli.modelo.pojo.Mensaje;
import cannoli.utils.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import cannoli.modelo.pojo.Proveedor;

/**
 *
 * @author Usuario
 */
public class ProveedorDAO {
   
    public static List<Proveedor> obtenerProveedores(){
        List<Proveedor> proveedores = new ArrayList<>();
        String url = Constantes.URL_WS + "proveedores/obtenerProveedores";
        CodigoHTTP respuesta = ConexionHTTP.peticionGET(url);
        
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Type tipoListaProveedor = new TypeToken<List<Proveedor>>(){
            }.getType();
            Gson gson = new Gson();
            proveedores = gson.fromJson(respuesta.getContenido(), tipoListaProveedor);
        }
        return proveedores;
    }
    
    public static Mensaje eliminarProveedor(Integer idProveedor){
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS+"proveedores/eliminarProveedor";
        String parametros = String.format("idProveedor=%d", idProveedor);
        CodigoHTTP respuestaHTTP = ConexionHTTP.peticionUrlEncodeDELETE(url, parametros);
        
        if(respuestaHTTP.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            mensaje = gson.fromJson(respuestaHTTP.getContenido(), Mensaje.class);
        }else{
            mensaje.setError(true);
            mensaje.setMensaje("Error al eliminar el proveedor, porfavor inténtelo más tarde.");
        }
        
        return mensaje;
    }
}
