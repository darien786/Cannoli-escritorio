/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.modelo.dao;

import cannoli.modelo.ConexionHTTP;
import cannoli.modelo.pojo.Categoria;
import cannoli.modelo.pojo.CodigoHTTP;
import cannoli.utils.Constantes;
import com.google.gson.Gson;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import cannoli.modelo.pojo.Categoria;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author cr7_k
 */
public class CategoriaDAO {
    
    public static List<Categoria> obtenerCategorias(){
        List<Categoria> listaCategorias = new ArrayList<>();
        String url = Constantes.URL_WS + "categorias/obtenerCategorias";
        CodigoHTTP respuesta = ConexionHTTP.peticionGET(url);
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            Type arrayListCategorias = new TypeToken<ArrayList<Categoria>>(){}.getType();
            listaCategorias = gson.fromJson(respuesta.getContenido(), arrayListCategorias);
        }
        
        return listaCategorias;
    }
    
    
}