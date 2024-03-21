/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.escritorio.categorias;

import cannoli.modelo.dao.CategoriaDAO;
import cannoli.modelo.dao.EstatusDAO;
import cannoli.modelo.pojo.Categoria;
import cannoli.modelo.pojo.Estatus;
import cannoli.modelo.pojo.Mensaje;
import cannoli.utils.Utilidades;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author cr7_k
 */
public class FXMLFormularioCategoriaController implements Initializable {

    private ObservableList estatus;
    private Categoria categoria;
    
    @FXML
    private Label lbTexto;
    @FXML
    private TextField tfNombre;
    @FXML
    private Label lbErrorNombre;
    @FXML
    private Label lbErrorEmail;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private ComboBox<Estatus> cbEstatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarInformacionEstatus();
    }    

    @FXML
    private void btnGuardar(ActionEvent event) {
        if(categoria == null){
            categoria = new Categoria();
        }
        
        if(!validarDatos()){
            Mensaje mensaje = null;
            if(this.categoria != null){
                mensaje = CategoriaDAO.modificarCategoria(categoria);
                if(!mensaje.getError()){
                    Utilidades.mostrarAlertaSimple("Modificar", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
                }else{
                    Utilidades.mostrarAlertaSimple("Error", mensaje.getMensaje(), Alert.AlertType.ERROR);
                }
            }else{
                mensaje = CategoriaDAO.registrarCategoria(categoria);
                if( !mensaje.getError() ){
                    Utilidades.mostrarAlertaSimple("Registro", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
                }else{
                    Utilidades.mostrarAlertaSimple("Error", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
                }
            }
        }
    }
    
    private Boolean validarDatos(){
        boolean error = false;
        
        if(tfNombre.getText().isEmpty()){
            error = true;
        }
        
        if(taDescripcion.getText().isEmpty()){
            error = true;
        }
        if(cbEstatus.getValue() == null){
            error = true;
        }
        
        return error;
    }
    
    public void obtenerCategoria(Categoria categoria){
        if(categoria != null){
            this.categoria = categoria;
            llenarInformacionCategoria(this.categoria);
        }else{
            cbEstatus.setDisable(true);
        }
    }
    
    private void llenarInformacionCategoria(Categoria categoria){
        tfNombre.setText(categoria.getNombreCategoria());
        taDescripcion.setText(categoria.getDescripcion());
        cbEstatus.getSelectionModel().select(categoria.getEstatus() -1);
    }
    
    private void cargarInformacionEstatus(){
        List<Estatus> listaEstatus = EstatusDAO.obtenerEstatus();
        estatus = FXCollections.observableArrayList(listaEstatus);
        cbEstatus.setItems(estatus);
        cbEstatus.getSelectionModel().select(0);
    }
    
}
