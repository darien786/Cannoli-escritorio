/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.escritorio.categorias;

import cannoli.modelo.dao.CategoriaDAO;
import cannoli.modelo.pojo.Categoria;
import cannoli.modelo.pojo.Empleado;
import cannoli.modelo.pojo.Mensaje;
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
public class FXMLGestionCategoriasController implements Initializable {

    private ObservableList<Categoria> categorias;
    private FilteredList<Categoria> listaCategorias;
    
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colEstatus;
    @FXML
    private TableView<Categoria> tvCategorias;
    @FXML
    private TableColumn colDescripcion;
    @FXML
    private TextField tfBuscarCategoria;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtenerCategorias();
        configurarTabla();
        configurarFiltro();
    }    

    @FXML
    private void btnRegistrar(ActionEvent event) {
        try{
            Stage stage = new Stage();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cannoli/escritorio/categorias/FXMLFormularioCategoria.fxml"));
            Parent vista = loader.load();
            
            FXMLFormularioCategoriaController controlador = loader.getController();
            controlador.obtenerCategoria(null);
            
            stage.setScene(new Scene(vista));
            stage.showAndWait();
            
            obtenerCategorias();
            configurarFiltro();
                    
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEditar(ActionEvent event) {
        Categoria categoriaSeleccionada = tvCategorias.getSelectionModel().getSelectedItem();
        if(categoriaSeleccionada != null){
            try{
            Stage stage = new Stage();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cannoli/escritorio/categorias/FXMLFormularioCategoria.fxml"));
            Parent vista = loader.load();
            
            FXMLFormularioCategoriaController controlador = loader.getController();
            controlador.obtenerCategoria(categoriaSeleccionada);
            
            stage.setScene(new Scene(vista));
            stage.showAndWait();
            
            obtenerCategorias();
            configurarFiltro();
                    
        }catch(Exception e){
            e.printStackTrace();
        }
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Para modificar una categoria, primero debes seleccionarla", Alert.AlertType.WARNING);
        }
        
    }

    @FXML
    private void btnEliminar(ActionEvent event) {
        Categoria categoria = tvCategorias.getSelectionModel().getSelectedItem();
        if(categoria != null){
            Mensaje mensaje = CategoriaDAO.eliminarCategoria(categoria);
            
            Utilidades.mostrarAlertaConfirmacion("Aviso", "¿Esta seguro de eliminar esta categoría?");
            
            if(!mensaje.getError()){
                Utilidades.mostrarAlertaSimple("Eliminación", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
            }else{
                Utilidades.mostrarAlertaSimple("Error", mensaje.getMensaje(), Alert.AlertType.ERROR);
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Para eliminar una categoria se debe seleccionar una categoria de la tabla", Alert.AlertType.WARNING);
        }
    }
    
    
    private void configurarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombreCategoria"));
        colEstatus.setCellValueFactory(new PropertyValueFactory("nombreEstatus"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
    }
    
    private void obtenerCategorias(){
        List<Categoria> listaCategorias = CategoriaDAO.obtenerCategorias();
        categorias = FXCollections.observableArrayList();
        categorias.setAll(listaCategorias);
        tvCategorias.setItems(categorias);
    }
    
    private void configurarFiltro() {

        listaCategorias = new FilteredList<>(categorias, b -> true);

        tfBuscarCategoria.textProperty().addListener((observable, oldValue, newValue) -> {
            listaCategorias.setPredicate(categoria -> {
                if (newValue == null || newValue.trim().isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                
                if(categoria.getNombreCategoria() != null && categoria.getNombreCategoria().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                if(categoria.getNombreEstatus() != null && categoria.getNombreEstatus().toLowerCase().contains(lowerCaseFilter)){
                return true;
                 }
                
                return false;
            });
        });

        SortedList<Categoria> sortedData = new SortedList<>(listaCategorias);
        sortedData.comparatorProperty().bind(tvCategorias.comparatorProperty());
        tvCategorias.setItems(sortedData);
    }
    
}
