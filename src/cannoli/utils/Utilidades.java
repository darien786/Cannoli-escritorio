/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Optional;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    
    public static Optional<ButtonType> mostrarAlertaConfirmacion(String titulo, String confirmacion) {
        Alert dialogoAlertaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        dialogoAlertaConfirmacion.setTitle(titulo);
        dialogoAlertaConfirmacion.setHeaderText(null);
        dialogoAlertaConfirmacion.setContentText(confirmacion);

        return dialogoAlertaConfirmacion.showAndWait();
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

        Image imagen = new Image(new ByteArrayInputStream(decodeImage));
        return imagen;
    }
     
    public static String convertirImagenABase64(File archivoImagen) throws IOException {
        byte[] bytesDeImagen = Files.readAllBytes(archivoImagen.toPath());
        return Base64.getEncoder().encodeToString(bytesDeImagen);
    }
    
    public static String imageToBase64(Image image) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            // Escribir la imagen en un stream de bytes
            javax.imageio.ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Codificar los bytes en Base64 y devolver la cadena resultante
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }
}
