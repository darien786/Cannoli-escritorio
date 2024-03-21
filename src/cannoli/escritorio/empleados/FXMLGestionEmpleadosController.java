/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.escritorio.empleados;

import cannoli.modelo.dao.EmpleadoDAO;
import cannoli.modelo.pojo.Empleado;
import cannoli.modelo.pojo.Producto;
import cannoli.utils.Utilidades;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cr7_k
 */
public class FXMLGestionEmpleadosController implements Initializable {

    private ObservableList<Empleado> empleados;
    private FilteredList<Empleado> listaEmpleados;
    
    @FXML
    private TableView<Empleado> tvEmpleados;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colCorreo;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private TableColumn colRol;
    @FXML
    private TableColumn colEstatus;
    @FXML
    private TextField tfBuscarEmpleado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtenerInformacionEmpleados();
        cargarInformacionEmpleados();
        configurarFiltro();
    }    


    @FXML
    private void btnRegistrar(ActionEvent event) {
        try{
            Stage stage = new Stage();
            
            FXMLLoader load = new FXMLLoader(getClass().getResource("FXMLFormularioEmpleado.fxml"));
            Parent vista = load.load();
            
            FXMLFormularioEmpleadoController controlador = load.getController();
            controlador.obtenerEmpleado(null);
            
            Scene scene = new Scene(vista);
            stage.setScene(scene);
            stage.setTitle("Registrar empleado");
            stage.showAndWait();
            
            obtenerInformacionEmpleados();
            configurarFiltro();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void btnEditar(ActionEvent event) {
        Empleado empleado = tvEmpleados.getSelectionModel().getSelectedItem();
        if(empleado != null){
            try{
            Stage stage = new Stage();
            
            FXMLLoader load = new FXMLLoader(getClass().getResource("FXMLFormularioEmpleado.fxml"));
            Parent vista = load.load();
            
            FXMLFormularioEmpleadoController controlador = load.getController();
            controlador.obtenerEmpleado(empleado);
            
            Scene scene = new Scene(vista);
            stage.setScene(scene);
            stage.setTitle("Modificar empleado");
            stage.showAndWait();
            
            obtenerInformacionEmpleados();
            configurarFiltro();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Para modificar un empleado se debe seleccionar uno de la tabla", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void btnEliminar(ActionEvent event) {
        Empleado empleado = tvEmpleados.getSelectionModel().getSelectedItem();
        if(empleado != null){
            
            obtenerInformacionEmpleados();
            configurarFiltro();
            
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Para poder eliminar, se debe seleccionar un empleado de la tabla", Alert.AlertType.WARNING);
        }
        
    }
    
    private void cargarInformacionEmpleados(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombreEmpleado"));
        colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        colEstatus.setCellValueFactory(new PropertyValueFactory("nombreEstatus"));
        colCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
        colRol.setCellValueFactory(new PropertyValueFactory("nombreRol"));
    }
    
    private void obtenerInformacionEmpleados(){
        List<Empleado> listaEmpleados = EmpleadoDAO.obtenerEmpleados();
        empleados = FXCollections.observableArrayList(listaEmpleados);
        tvEmpleados.setItems(empleados);
    }
    
    private void configurarFiltro() {

        listaEmpleados = new FilteredList<>(empleados, b -> true);

        tfBuscarEmpleado.textProperty().addListener((observable, oldValue, newValue) -> {
            listaEmpleados.setPredicate(empleado -> {
                if (newValue == null || newValue.trim().isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (empleado.getNombreEmpleado() != null && empleado.getNombreEmpleado().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                if(empleado.getNombreRol() != null && empleado.getNombreRol().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                return false;
            });
        });

        SortedList<Empleado> sortedData = new SortedList<>(listaEmpleados);
        sortedData.comparatorProperty().bind(tvEmpleados.comparatorProperty());
        tvEmpleados.setItems(sortedData);
    }
    
    
}
