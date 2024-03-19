/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.escritorio.proveedores;

import cannoli.escritorio.interfaz.IRespuesta;
import cannoli.modelo.dao.ProveedorDAO;
import cannoli.modelo.pojo.Mensaje;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import cannoli.modelo.pojo.Proveedor;
import cannoli.utils.Utilidades;
import java.util.Optional;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLGestionProveedoresController implements Initializable, IRespuesta {

    private ObservableList<Proveedor> proveedores;
    private List<Proveedor> listaProveedor;
    private FilteredList<Proveedor> listaProveedoresFiltro;

    @FXML
    private TableView<Proveedor> tvProveedores;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private TableColumn colEmpresa;
    @FXML
    private TextField tfBuscarProveedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        proveedores = FXCollections.observableArrayList();
        configurarTabla();
        descargarProveedores();
        //configurarListener();
        configurarFiltro();
    }

    public void descargarProveedores() {
        listaProveedor = ProveedorDAO.obtenerProveedores();
        proveedores.clear();
        proveedores.addAll(listaProveedor);
        tvProveedores.setItems(proveedores);
    }

    public void configurarTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory("nombreProveedor"));
        colEmpresa.setCellValueFactory(new PropertyValueFactory("empresa"));
        colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
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
        Proveedor proveedorSeleccion = tvProveedores.getSelectionModel().getSelectedItem();
        if (proveedorSeleccion != null) {
            Optional<ButtonType> respuestaEliminar = Utilidades.mostrarAlertaConfirmacion("Confirmar eliminación", "¿Estás seguro de eliminar al proveedor " + proveedorSeleccion.getNombreProveedor()+ "?");
            if (respuestaEliminar.get() == ButtonType.OK) {
                eliminarProveedor(proveedorSeleccion.getIdProveedor());
            }
        } else {
            Utilidades.mostrarAlertaSimple("Selección requerida", "Debe seleccionar un proveedor para su eliminación", Alert.AlertType.WARNING);
        }
    }

    private Mensaje eliminarProveedor(Integer idProveedor) {
        Mensaje mensaje = ProveedorDAO.eliminarProveedor(idProveedor);
        if (mensaje.getError() != true) {
            Utilidades.mostrarAlertaSimple("Proveedor eliminado", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            descargarProveedores();
            configurarFiltro();
        } else {
            Utilidades.mostrarAlertaSimple("Error al eliminar Proveedor", mensaje.getMensaje(), Alert.AlertType.ERROR);
        }
        return mensaje;
    }

    @Override
    public void notificarGuardado(String nombre) {
    }

    private void configurarFiltro() {

        listaProveedoresFiltro = new FilteredList<>(proveedores, b -> true);

        tfBuscarProveedor.textProperty().addListener((observable, oldValue, newValue) -> {
            listaProveedoresFiltro.setPredicate(proveedor -> {
                if (newValue == null || newValue.trim().isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (proveedor.getNombreProveedor() != null && proveedor.getNombreProveedor().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                if (proveedor.getEmpresa() != null && proveedor.getEmpresa().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Proveedor> sortedData = new SortedList<>(listaProveedoresFiltro);
        sortedData.comparatorProperty().bind(tvProveedores.comparatorProperty());
        tvProveedores.setItems(sortedData);
    }

    /*private void configurarListener() {
        tvProveedores.setOnMouseClicked(evtm -> {
            Proveedor proveedorSeleccionado = (Proveedor) tvProveedores.getSelectionModel().getSelectedItem();
            if (evtm.getClickCount() == 2) {
                int seleccion = tvProveedores.getSelectionModel().getSelectedIndex();
                //eliminarProveedor();
                //editarProveedor(proveedorSeleccionado);
            }
        });
    }*/

}
