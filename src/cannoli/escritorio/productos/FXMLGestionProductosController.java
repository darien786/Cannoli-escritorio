/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLGestionProductosController implements Initializable {

    @FXML
    private TableView<?> tvEmpleados;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colEstatus;
    @FXML
    private TableColumn<?, ?> colCodigo;
    @FXML
    private TableColumn<?, ?> colDescripcion;
    @FXML
    private TableColumn<?, ?> colPrecio;
    @FXML
    private TableColumn<?, ?> colCantidad;
    @FXML
    private TableColumn<?, ?> colFechaElaboracion;
    @FXML
    private TableColumn<?, ?> colFechaVencimiento;
    @FXML
    private TableColumn<?, ?> colCategoria;
    @FXML
    private TextField tfBuscarProducto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void btnRegistrar(ActionEvent event) {
    }

    @FXML
    private void btnEditar(ActionEvent event) {
    }

    @FXML
    private void btnEliminar(ActionEvent event) {
    }
    
}
