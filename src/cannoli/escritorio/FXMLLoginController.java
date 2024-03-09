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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        lbErrorUsername.setText("");
        lbErrorPassword.setText("");
    }    
    
    private Boolean verificarDatos(String username, String contrasenia){
        boolean error = false;
        if(username.isEmpty()){
            error = true;
            lbErrorUsername.setText("*Campo obligatorio");
        }
        
        if(contrasenia.isEmpty()){
            error = true;
            lbErrorPassword.setText("*Campo obligatorio");
        }
        
        return error;
    }
    
    private void verificarInicioSesion(String username, String contrasenia){
        MensajeAutenticacion respuestaWS = AutenticacionDAO.validarSesion(username, contrasenia);
        if(!respuestaWS.isError()){
            Utilidades.mostrarAlertaSimple("Credenciales correstas", respuestaWS.getMensaje(), Alert.AlertType.INFORMATION);
            irPantallaHome(respuestaWS.getEmpleado());
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
    
    private void irPantallaHome(Empleado empleado){
        try{
            Stage stage = (Stage) tfUsername.getScene().getWindow();
            
            FXMLLoader load = new FXMLLoader(getClass().getResource("FXMLHome.fxml"));
            Parent vista = load.load();
            
            FXMLHomeController controlador = load.getController();
            controlador.obtenerInformacionEmpleado(empleado);
            
            Scene scene = new Scene(vista);
            stage.setScene(scene);
            stage.show();
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
