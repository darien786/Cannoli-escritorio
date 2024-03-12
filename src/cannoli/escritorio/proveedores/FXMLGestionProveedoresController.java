/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.escritorio.proveedores;

import cannoli.escritorio.interfaz.IRespuesta;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.pojo.Proveedor;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLGestionProveedoresController implements Initializable, IRespuesta {

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
        //List<Empresa> listaEmpresa = EmpresaDAO.obtenerEmpresas();
        //if (!listaEmpresa.isEmpty()) {
            try {

                FXMLLoader loadVista = new FXMLLoader(getClass().getResource("/cannoli/escritorio/proveedores/FXMLFromularioRegistrarProveedor.fxml"));
                Parent vista = loadVista.load();

                FXMLFromularioRegistrarProveedorController controller = loadVista.getController();
                //controller.inicializarInformacion(this, idEmpresa);
                controller.inicializarInformacion(this);

                Scene escena = new Scene(vista);
                Stage stageActual = new Stage();
                stageActual.initModality(Modality.APPLICATION_MODAL);

                stageActual.setScene(escena);
                stageActual.setTitle("Registrar Proveedor");
                stageActual.setResizable(false);
                stageActual.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        //} //else {
            //Utilidades.mostrarAlertaSimple("Acción denegada. ", "No se puede agregar una sucursal hasta que haya al menos una empresa registrada.", Alert.AlertType.WARNING);
        //}
    }

    @FXML
    private void btnEditar(ActionEvent event) {
        
    }

    @FXML
    private void btnEliminar(ActionEvent event) {
        
    }

    @Override
    public void notificarGuardado(String nombre) {
    }
    
}
