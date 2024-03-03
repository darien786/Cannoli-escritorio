/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.escritorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author cr7_k
 */
public class FXMLHomeController implements Initializable {

    @FXML
    private Label lbNombreUsuario;
    @FXML
    private Label lbBotonEmpresa;
    @FXML
    private Button btnEmpresa;
    @FXML
    private Label lbEmpresa;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfCurp;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfRol;
    @FXML
    private TextField tfEmpresa;
    @FXML
    private ImageView ivUsuarios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnIrPantallaEmpresa(ActionEvent event) {
    }

    @FXML
    private void btnIrPantallaPromocion(ActionEvent event) {
    }

    @FXML
    private void btnIrPantallaCupon(ActionEvent event) {
    }

    @FXML
    private void btnIrPantallaSucursal(ActionEvent event) {
    }

    @FXML
    private void btnCerrarSesion(ActionEvent event) {
    }

    @FXML
    private void btnIrPantallaEmpleados(ActionEvent event) {
        
    }
    
}
