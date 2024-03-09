/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 *
 * @author cr7_k
 */
public class Utilidades {
    
    public static void mostrarAlertaSimple(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    public static File seleccionarImagen(Window ventanaPadre) {
        FileChooser dialogoSeleccionImagen = new FileChooser();

        dialogoSeleccionImagen.setTitle("Selecciona una imagen");
        FileChooser.ExtensionFilter filtroArchivos = new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpeg", "*.jpg");

        dialogoSeleccionImagen.getExtensionFilters().add(filtroArchivos);

        File foto = dialogoSeleccionImagen.showOpenDialog(ventanaPadre);

        return foto;
    }
    
    public static Image decodificarImagenBase64(String imagenBase64) {
        byte[] decodeImage = Base64.getDecoder().decode(imagenBase64.replaceAll("\\n", ""));
        return new Image(new ByteArrayInputStream(decodeImage));
    }
   
    public static String convertirImagenABase64(File archivoImagen) throws IOException {
        byte[] bytesDeImagen = Files.readAllBytes(archivoImagen.toPath());
        return Base64.getEncoder().encodeToString(bytesDeImagen);
    }
    
}
