/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.escritorio;

import cannoli.modelo.dao.AutenticacionDAO;
import cannoli.modelo.pojo.Empleado;
import cannoli.modelo.pojo.MensajeAutenticacion;
import cannoli.utils.Utilidades;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author cr7_k
 */
public class FXMLLoginController implements Initializable {
    
    @FXML
    private TextField tfUsername;
    @FXML
    private Label lbErrorUsername;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Label lbErrorPassword;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private Boolean verificarDatos(String username, String contrasenia){
        boolean error = false;
        if(username.isEmpty() || contrasenia.isEmpty()){
            error = true;
        }
        
        return error;
    }
    
    private void verificarInicioSesion(String username, String contrasenia){
        MensajeAutenticacion respuestaWS = AutenticacionDAO.validarSesion(username, contrasenia);
        if(!respuestaWS.isError()){
            Utilidades.mostrarAlertaSimple("Credenciales correstas", respuestaWS.getMensaje(), Alert.AlertType.INFORMATION);
        }else{
            Utilidades.mostrarAlertaSimple("Credenciales incorrectas", respuestaWS.getMensaje(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void btnIniciarSesion(ActionEvent event) {
                
        String username = tfUsername.getText().trim().toString();
        String contrasenia = tfPassword.getText().trim().toString();
        
        if(!verificarDatos(username, contrasenia)){
            verificarInicioSesion(username, contrasenia);
        }
        
    }
}
