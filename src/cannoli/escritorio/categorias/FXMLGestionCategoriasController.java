/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.escritorio.categorias;

import cannoli.modelo.dao.CategoriaDAO;
import cannoli.modelo.pojo.Categoria;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author cr7_k
 */
public class FXMLGestionCategoriasController implements Initializable {

    private ObservableList<Categoria> categorias;
    
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colEstatus;
    @FXML
    private TextField tfBuscarEmpleado;
    @FXML
    private TableView<Categoria> tvCategorias;
    @FXML
    private TableColumn<?, ?> colDescripcion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtenerCategorias();
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
    
    
    private void configurarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombreCategoria"));
        colEstatus.setCellValueFactory(new PropertyValueFactory("nombreEstatus"));
    }
    
    private void obtenerCategorias(){
        List<Categoria> listaCategorias = CategoriaDAO.obtenerCategorias();
        categorias = FXCollections.observableArrayList();
        categorias.setAll(listaCategorias);
        tvCategorias.setItems(categorias);
    }
    
}
