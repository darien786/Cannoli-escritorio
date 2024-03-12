/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.escritorio;

import cannoli.escritorio.proveedores.FXMLGestionProveedoresController;
import cannoli.modelo.pojo.Empleado;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cr7_k
 */
public class FXMLHomeController implements Initializable {

    private Empleado empleado;

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
    private ImageView ivUsuarios;
    @FXML
    private Label lbNombreEmpleado;
    @FXML
    private TextField tfTelefono;

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

        try {
            Stage stage = (Stage) lbNombreEmpleado.getScene().getWindow();

            FXMLLoader load = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
            Parent vista = load.load();

            Utilidades.mostrarAlertaSimple("Cerrar Sesión", "Adios " + empleado.getNombreEmpleado(), Alert.AlertType.INFORMATION);

            Scene scene = new Scene(vista);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnIrPantallaEmpleados(ActionEvent event) {
        try {
            Stage stage = new Stage();

            FXMLLoader load = new FXMLLoader(getClass().getResource("FXMLGestionEmpleados.fxml"));
            Parent vista = load.load();

            FXMLGestionEmpleadosController controlador = load.getController();
            controlador.obtenerInformacionEmpleados();

            Scene scene = new Scene(vista);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void obtenerInformacionEmpleado(Empleado empleado) {
        this.empleado = empleado;
        if (empleado != null) {
            cargarInformacionEmpleado(empleado);
        }
    }

    private void cargarInformacionEmpleado(Empleado empleado) {
        lbNombreEmpleado.setText(empleado.getNombreEmpleado());
        tfNombre.setText(empleado.getNombreEmpleado());
        tfCurp.setText(empleado.getCurp());
        tfEmail.setText(empleado.getCorreo());
        tfRol.setText(empleado.getNombreRol());
        tfTelefono.setText(empleado.getTelefono());
    }

    @FXML
    private void btnIrPantallaProveedores(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cannoli/escritorio/proveedores/FXMLGestionProveedores.fxml"));
            Parent vista = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(vista));

            FXMLGestionProveedoresController controlador = loader.getController();
            controlador.inicializarInformacion();

            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
