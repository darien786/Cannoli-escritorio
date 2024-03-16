/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.modelo.dao;

import cannoli.modelo.ConexionHTTP;
import cannoli.modelo.pojo.CodigoHTTP;
import cannoli.modelo.pojo.Empleado;
import cannoli.modelo.pojo.Mensaje;
import cannoli.modelo.pojo.Producto;
import cannoli.utils.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cr7_k
 */
public class ProductoDAO {
    
    
    public static List<Producto> obtenerProducto(){
        List<Producto> productos = new ArrayList<>();
        String url = Constantes.URL_WS + "productos/obtenerProductos";
        CodigoHTTP respuesta = ConexionHTTP.peticionGET(url);
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            Type listaProductos = new TypeToken<ArrayList<Producto>>(){}.getType();
            productos = gson.fromJson(respuesta.getContenido(), listaProductos);
        }
        
        return productos;
    }
    
    public static Producto obtenerProductoPorId(Integer idProducto){
        Producto producto = null;
        String url = Constantes.URL_WS + "productos/obtenerProductoPorId/" + idProducto;
        CodigoHTTP respuesta = ConexionHTTP.peticionGET(url);
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            producto = gson.fromJson(respuesta.getContenido(), Producto.class);
        }
        
        return producto;
    }
    
    public static Mensaje registrarProducto(Producto producto){
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "productos/registrarProducto";
        
        Gson gson = new Gson();
        String parametros = gson.toJson(producto);
        CodigoHTTP respuesta = ConexionHTTP.peticionPOST(url, parametros);
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        }else{
            mensaje.setError(Boolean.TRUE);
            mensaje.setMensaje("Petición incorrecta, favor de verificarlo");
        }
        
        return mensaje;
    }
    
    public static Mensaje modificarProducto(Producto producto){
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "productos/editarProducto";
        Gson gson = new Gson();
        String parametros = gson.toJson(producto);
        CodigoHTTP respuesta = ConexionHTTP.peticionPUT(url, parametros);
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        }else{
            mensaje.setError(Boolean.TRUE);
            mensaje.setMensaje("Peticion incorrecta, favor de verificarlo");
        }
        
        return mensaje;
    }
    
    public static Mensaje eliminarProducto(Producto producto){
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "productos/eliminarProducto";
        Gson gson = new Gson();
        String parametros = gson.toJson(producto);
        CodigoHTTP respuesta = ConexionHTTP.peticionDELETE(url, parametros);
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        }else{
            mensaje.setError(Boolean.TRUE);
            mensaje.setMensaje("Peticion incorrecta, favor de verificarlo");
        }
        
        return mensaje;
    }
}
