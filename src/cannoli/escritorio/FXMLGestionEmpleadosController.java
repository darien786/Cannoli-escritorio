/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.escritorio;

import cannoli.modelo.dao.EmpleadoDAO;
import cannoli.modelo.pojo.Empleado;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author cr7_k
 */
public class FXMLGestionEmpleadosController implements Initializable {

    private ObservableList<Empleado> empleados;
    
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
    @FXML
    private CheckBox cbNombre;
    @FXML
    private CheckBox cbDireccion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarInformacionEmpleados();
    }    

    @FXML
    private void btnBuscar(ActionEvent event) {
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
    
    private void cargarInformacionEmpleados(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombreEmpleado"));
        colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        colEstatus.setCellValueFactory(new PropertyValueFactory("nombreEstatus"));
        colCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
        colRol.setCellValueFactory(new PropertyValueFactory("nombreRol"));
    }
    
    public void obtenerInformacionEmpleados(){
        List<Empleado> listaEmpleados = EmpleadoDAO.obtenerEmpleados();
        empleados = FXCollections.observableArrayList(listaEmpleados);
        tvEmpleados.setItems(empleados);
    }
    
}
