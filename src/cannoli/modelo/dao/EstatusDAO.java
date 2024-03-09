/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.modelo.dao;

import cannoli.modelo.ConexionHTTP;
import cannoli.modelo.pojo.CodigoHTTP;
import cannoli.modelo.pojo.Estatus;
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
public class EstatusDAO {
    public static List<Estatus> obtenerEstatus(){
        List<Estatus> listaEstatus = new ArrayList<>();
        String url = Constantes.URL_WS + "empleados/obtenerEstatus";
        CodigoHTTP respuesta = ConexionHTTP.peticionGET(url);
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            Type arrayListEstatus = new TypeToken<ArrayList<Estatus>>(){}.getType();
            listaEstatus = gson.fromJson(respuesta.getContenido(), arrayListEstatus);
        }
        
        return listaEstatus;
    }
}
