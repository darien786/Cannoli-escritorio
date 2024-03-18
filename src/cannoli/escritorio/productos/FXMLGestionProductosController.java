/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.escritorio.productos;

import cannoli.modelo.dao.ProductoDAO;
import cannoli.modelo.pojo.Mensaje;
import cannoli.modelo.pojo.Producto;
import cannoli.utils.Utilidades;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class FXMLGestionProductosController implements Initializable {

    private ObservableList<Producto> productos;
    
    @FXML
    private TableView<Producto> tvProductos;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colDescripcion;
    @FXML
    private TableColumn colPrecio;
    @FXML
    private TableColumn colCantidad;
    @FXML
    private TableColumn colFechaElaboracion;
    @FXML
    private TableColumn colFechaVencimiento;
    @FXML
    private TableColumn colEstatus;
    @FXML
    private TableColumn colCategoria;
    @FXML
    private TextField tfBuscarProducto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        solicitarInformacionProductos();
        cargarInformacionTabla();
    }    
    
    private void solicitarInformacionProductos(){
        List<Producto> listaProductos = ProductoDAO.obtenerProducto();
        productos = FXCollections.observableArrayList(listaProductos);
        tvProductos.setItems(productos);
    }
    
    private void cargarInformacionTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        colEstatus.setCellValueFactory(new PropertyValueFactory("nombreEstatus"));
        colCategoria.setCellValueFactory(new PropertyValueFactory("nombreCategoria"));
        colFechaElaboracion.setCellValueFactory(new PropertyValueFactory("fechaElaboracion"));
        colFechaVencimiento.setCellValueFactory(new PropertyValueFactory("fechaVencimiento"));
        colCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
    }
    

    @FXML
    private void btnRegistrar(ActionEvent event) {
        try{
            Stage stage = new Stage();
            
            FXMLLoader load = new FXMLLoader(getClass().getResource("/cannoli/escritorio/productos/FXMLFormularioProductos.fxml"));
            Parent vista = load.load();
            
            FXMLFormularioProductosController controller = load.getController();
            controller.obtenerProducto(null);
            
            Scene scene = new Scene(vista);
            stage.setScene(scene);
            stage.showAndWait();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }

    @FXML
    private void btnEditar(ActionEvent event) {
        Producto producto = tvProductos.getSelectionModel().getSelectedItem();
        if(producto != null){
            try{
            Stage stage = new Stage();
            FXMLLoader load = new FXMLLoader(getClass().getResource("/cannoli/escritorio/productos/FXMLFormularioProductos.fxml"));
            Parent vista = load.load();
            
            FXMLFormularioProductosController controller = load.getController();
            controller.obtenerProducto(producto);
            
            Scene scene = new Scene(vista);
            stage.setScene(scene);
            stage.setTitle("Modificar producto");
            stage.showAndWait();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Para modificar se debe seleccionar un producto de la tabla", Alert.AlertType.WARNING);
        }
        
    }

    @FXML
    private void btnEliminar(ActionEvent event) {
        Producto producto = tvProductos.getSelectionModel().getSelectedItem();
        if(producto != null){
            try{
            
            Mensaje mensaje = ProductoDAO.eliminarProducto(producto);
            if(!mensaje.getError()){
                Utilidades.mostrarAlertaSimple("Eliminación", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            }else{
                Utilidades.mostrarAlertaSimple("Error", mensaje.getMensaje(), Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Para modificar se debe seleccionar un producto de la tabla", Alert.AlertType.WARNING);
        }
    }
    
}
