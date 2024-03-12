/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.escritorio.proveedores;

import cannoli.escritorio.interfaz.IRespuesta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLFromularioRegistrarProveedorController implements Initializable, IRespuesta {
    private IRespuesta observador;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void inicializarInformacion(IRespuesta observador) {
        this.observador = observador;
    }

    @Override
    public void notificarGuardado(String nombre) {
    }
}
