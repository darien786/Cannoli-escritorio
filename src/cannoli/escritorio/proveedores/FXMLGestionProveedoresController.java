/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.escritorio.proveedores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import modelo.pojo.Proveedor;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLGestionProveedoresController implements Initializable {

    @FXML
    private TableView<Proveedor> tvProveedores;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colTelefono;
    @FXML
    private TableColumn<?, ?> colEstatus;
    @FXML
    private TableColumn<?, ?> colEmpresa;
    @FXML
    private TextField tfBuscarProveedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void inicializarInformacion(){
        
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
