/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.modelo;

import cannoli.modelo.pojo.CodigoHTTP;
import cannoli.utils.Constantes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author cr7_k
 */
public class ConexionHTTP {

    private static String deserializar(InputStream contenido) throws IOException {
        InputStreamReader reader = new InputStreamReader(contenido);
        BufferedReader buffer = new BufferedReader(reader);

        String cadenaEntrada;
        StringBuffer cadenaBuffer = new StringBuffer();

        while ((cadenaEntrada = buffer.readLine()) != null) {
            cadenaBuffer.append(cadenaEntrada);
        }

        buffer.close();

        return cadenaBuffer.toString();
    }

    private static String convertirContenido(InputStream contenido) throws IOException {
        InputStreamReader inputLectura = new InputStreamReader(contenido);
        BufferedReader buffer = new BufferedReader(inputLectura);
        String cadenaEntrada;
        StringBuffer cadenaBuffer = new StringBuffer();
        while ((cadenaEntrada = buffer.readLine()) != null) {
            cadenaBuffer.append(cadenaEntrada);
        }
        buffer.close();
        return cadenaBuffer.toString();
    }

    private static String obtenerContenidoWS(InputStream inputWS) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(inputWS));
        String inputLine;
        StringBuffer respuestaEntrada = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            respuestaEntrada.append(inputLine);
        }
        in.close();
        // Convertir StringBuffer en una cadena normal.
        return respuestaEntrada.toString();
    }

    public static CodigoHTTP peticionPOST(String url, String json) {
        CodigoHTTP respuesta = new CodigoHTTP();

        try {
            URL urlServicio = new URL(url);
            HttpURLConnection conexionHTTP = (HttpURLConnection) urlServicio.openConnection();

            conexionHTTP.setRequestMethod("POST");
            conexionHTTP.setRequestProperty("Content-Type", "application/json");
            conexionHTTP.setDoOutput(true);

            //Escribir datos en el cuerpo de la peticion
            OutputStream outputStream = conexionHTTP.getOutputStream();
            outputStream.write(json.getBytes());
            outputStream.flush();
            outputStream.close();
            //Termina la escritura

            int codigoRespuesta = conexionHTTP.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);

            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                respuesta.setContenido(deserializar(conexionHTTP.getInputStream()));
            }
        } catch (MalformedURLException ex) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("ERROR: " + ex.getMessage());
        } catch (IOException ex) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("ERROR: " + ex.getMessage());
        }

        return respuesta;
    }

    public static CodigoHTTP peticionGET(String url) {
        CodigoHTTP respuesta = new CodigoHTTP();
        try {
            URL urlServicio = new URL(url);
            HttpURLConnection conexionHTTP = (HttpURLConnection) urlServicio.openConnection();

            conexionHTTP.setRequestMethod("GET");
            conexionHTTP.setRequestProperty("Content-Type", "application/json");
            conexionHTTP.setDoOutput(true);
            //Termina la escritura

            int codigoRespuesta = conexionHTTP.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);

            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                respuesta.setContenido(deserializar(conexionHTTP.getInputStream()));
            }
        } catch (MalformedURLException ex) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("ERROR: " + ex.getMessage());
        } catch (IOException ex) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("ERROR: " + ex.getMessage());
        }

        return respuesta;
    }

    public static CodigoHTTP peticionPUT(String url, String parametros) {
        CodigoHTTP respuesta = new CodigoHTTP();

        try {
            URL urlServicio = new URL(url);
            HttpURLConnection conexionHTTP = (HttpURLConnection) urlServicio.openConnection();

            conexionHTTP.setRequestMethod("PUT");

            conexionHTTP.setRequestProperty("Content-Type", "application/json");
            conexionHTTP.setDoOutput(true);

            //Escribir datos en el cuerpo de la peticion
            OutputStream outputStream = conexionHTTP.getOutputStream();
            outputStream.write(parametros.getBytes());
            outputStream.flush();
            outputStream.close();
            //Termina la escritura

            int codigoRespuesta = conexionHTTP.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);

            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                respuesta.setContenido(deserializar(conexionHTTP.getInputStream()));
            }
        } catch (MalformedURLException ex) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("ERROR: " + ex.getMessage());
        } catch (IOException ex) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("ERROR: " + ex.getMessage());
        }

        return respuesta;
    }

    public static CodigoHTTP peticionDELETE(String url, String parametros) {
        CodigoHTTP respuesta = new CodigoHTTP();

        try {
            URL urlServicio = new URL(url);
            HttpURLConnection conexionHTTP = (HttpURLConnection) urlServicio.openConnection();

            conexionHTTP.setRequestMethod("DELETE");
            conexionHTTP.setRequestProperty("Content-Type", "application/json");
            conexionHTTP.setDoOutput(true);

            OutputStream outputStream = conexionHTTP.getOutputStream();
            outputStream.write(parametros.getBytes());
            outputStream.flush();
            outputStream.close();

            int codigoRespuesta = conexionHTTP.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);

            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                respuesta.setContenido(deserializar(conexionHTTP.getInputStream()));
            }
        } catch (MalformedURLException ex) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("ERROR: " + ex.getMessage());
        } catch (IOException ex) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("ERROR: " + ex.getMessage());
        }

        return respuesta;
    }

    public static CodigoHTTP peticionUrlEncodeDELETE(String url, String parametros) {
        CodigoHTTP respuesta = new CodigoHTTP();

        try {
            URL urlDestino = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlDestino.openConnection();
            conexionHttp.setRequestMethod("DELETE");
            conexionHttp.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conexionHttp.setDoOutput(true);
            
            OutputStream os = conexionHttp.getOutputStream();
            os.write(parametros.getBytes());
            os.flush();
            os.close();
            
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                respuesta.setContenido(obtenerContenidoWS(conexionHttp.getInputStream()));
            } else {
                respuesta.setContenido("Codigo de respuesta HTTP: " + codigoRespuesta);
            }

        } catch (MalformedURLException e) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error en la direccion de conexion.");
        } catch (IOException io) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: no se pudo realizar la solicitud correspondiente.");
        } catch (Exception e) {

        }
        return respuesta;
    }
    
    public static CodigoHTTP peticionGET(String url,String parametros) {
        CodigoHTTP respuesta = new CodigoHTTP();
        try {
            URL urlServicio = new URL(url);
            HttpURLConnection conexionHTTP = (HttpURLConnection) urlServicio.openConnection();

            conexionHTTP.setRequestMethod("GET");
            conexionHTTP.setRequestProperty("Content-Type", "application/json");
            conexionHTTP.setDoOutput(true);
            //Termina la escritura
            
            OutputStream os = conexionHTTP.getOutputStream();
            os.write(parametros.getBytes());
            os.flush();
            os.close();
            
            int codigoRespuesta = conexionHTTP.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);

            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                respuesta.setContenido(deserializar(conexionHTTP.getInputStream()));
            }
        } catch (MalformedURLException ex) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("ERROR: " + ex.getMessage());
        } catch (IOException ex) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("ERROR: " + ex.getMessage());
        }

        return respuesta;
    }
}
