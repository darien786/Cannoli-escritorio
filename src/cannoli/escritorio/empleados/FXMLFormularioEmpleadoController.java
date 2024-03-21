/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.escritorio.empleados;

import cannoli.modelo.dao.EmpleadoDAO;
import cannoli.modelo.dao.EstatusDAO;
import cannoli.modelo.dao.RolDAO;
import cannoli.modelo.pojo.DatosRegistroEmpleado;
import cannoli.modelo.pojo.Empleado;
import cannoli.modelo.pojo.Estatus;
import cannoli.modelo.pojo.Mensaje;
import cannoli.modelo.pojo.Persona;
import cannoli.modelo.pojo.Rol;
import cannoli.utils.Constantes;
import cannoli.utils.Utilidades;
import static cannoli.utils.Utilidades.seleccionarImagen;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author cr7_k
 */
public class FXMLFormularioEmpleadoController implements Initializable {

    private File imagen = null;
    private Image image;
    private DatosRegistroEmpleado datosEmpleado;
    private Empleado empleado = null;
    private ObservableList olEstatus;
    private ObservableList olSexo;
    private ObservableList olRol;

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCurp;
    @FXML
    private Label lbErrorNombre;
    @FXML
    private Label lbErrorApellidoPaterno;
    @FXML
    private Label lbErrorApellidoMaterno;
    @FXML
    private Label lbErrorCURP;
    @FXML
    private TextField tfCorreo;
    @FXML
    private Label lbErrorEmail;
    @FXML
    private Label lbErrorCURP1;
    @FXML
    private Label lbErrorCURP2;
    @FXML
    private TextField tfTelefono;
    @FXML
    private ComboBox<String> cbSexo;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    private ComboBox<Rol> cbRol;
    @FXML
    private Label lbErrorUsername;
    @FXML
    private Label lbErrorRol;
    @FXML
    private Label lbErrorPassword;
    @FXML
    private Label lbErrorRol1;
    @FXML
    private ComboBox<Estatus> cbEstatus;
    @FXML
    private ImageView ivEmpleado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarInformacionEstatus();
        cargarInformacionSexo();
        cargarInformacionRol();
    }
    
    @FXML
    private void btnGuardar(ActionEvent event) {
        
        if(empleado == null){
            this.datosEmpleado = new DatosRegistroEmpleado();
        }
        
        if(!validarDatos()){
            Mensaje mensaje = null;
            if(empleado.getIdEmpleado() != null){
                  guardarInformacionEmpleado(this.datosEmpleado);
                  mensaje = EmpleadoDAO.modificarEmpleado(this.datosEmpleado);
                  if(!mensaje.getError()){
                      Utilidades.mostrarAlertaSimple("Modificación", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
                  }else{
                      Utilidades.mostrarAlertaSimple("Error", mensaje.getMensaje(), Alert.AlertType.ERROR);
                  }
                  
            }else{
                  guardarInformacionEmpleado(datosEmpleado);
                  mensaje = EmpleadoDAO.registrarEmpleado(datosEmpleado);
                  if(!mensaje.getError()){
                      Utilidades.mostrarAlertaSimple("Registro", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
                  }else{
                      Utilidades.mostrarAlertaSimple("Error", mensaje.getMensaje(), Alert.AlertType.ERROR);
                  }
            }
        }
    }
    
    public void obtenerEmpleado(Empleado empleado){
        if(empleado != null){
            this.empleado = empleado;
            this.datosEmpleado = EmpleadoDAO.obtenerEmpleadoPorId(empleado.getIdEmpleado());
            llenarCamposEmpleado(this.datosEmpleado);
            tfCurp.setDisable(true);
            tfUsername.setDisable(true);
            cbRol.setDisable(true);
        }else if(empleado == null){
            cbEstatus.setDisable(true);
        }
    }
    
    private void guardarInformacionEmpleado(DatosRegistroEmpleado datosEmpleado){
        
        try {
            
            Empleado empleadoNuevo = new Empleado();
            Persona persona = new Persona();
            
            String ruta = Constantes.PATH_EMPLEADO + tfCurp.getText().trim().toString() + "/"+tfCurp.getText().trim().toString() +".png";
            String imagenBase64;
            if(imagen == null ){
                imagenBase64 = Utilidades.imageToBase64(image);
            }else{
                 imagenBase64= Utilidades.convertirImagenABase64(imagen);
            }
            if(this.empleado.getPersona() != null && this.empleado.getIdEmpleado() != null){
                persona.setIdPersona(this.empleado.getPersona());
                empleadoNuevo.setIdEmpleado(this.empleado.getIdEmpleado());
            }
            
            persona.setNombrePersona(tfNombre.getText().trim().toString());
            persona.setApellidoPaterno(tfApellidoPaterno.getText().trim().toString());
            persona.setApellidoMaterno(tfApellidoMaterno.getText().trim().toString());
            persona.setSexo(cbSexo.getValue().toString());
            persona.setTelefono(tfTelefono.getText().trim().toString());
            empleadoNuevo.setCurp(tfCurp.getText().trim().toString());
            empleadoNuevo.setUsername(tfUsername.getText().trim().toString());
            empleadoNuevo.setCorreo(tfCorreo.getText().trim().toString());
            empleadoNuevo.setContrasenia(tfPassword.getText().trim().toString());
            empleadoNuevo.setRol(cbRol.getValue().getIdRol());
            empleadoNuevo.setEstatus(cbEstatus.getValue().getIdEstatus());
            empleadoNuevo.setFotografia(ruta);
            empleadoNuevo.setFotografiaBase64(imagenBase64);
            
            datosEmpleado.setEmpleado(empleadoNuevo);
            datosEmpleado.setPersona(persona);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLFormularioEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    private void llenarCamposEmpleado(DatosRegistroEmpleado empleado){
        tfNombre.setText(empleado.getPersona().getNombrePersona());
        tfApellidoPaterno.setText(empleado.getPersona().getApellidoPaterno());
        tfApellidoMaterno.setText(empleado.getPersona().getApellidoMaterno());
        tfCorreo.setText(empleado.getEmpleado().getCorreo());
        tfUsername.setText(empleado.getEmpleado().getUsername());
        tfPassword.setText(empleado.getEmpleado().getContrasenia());
        tfCurp.setText(empleado.getEmpleado().getCurp());
        tfTelefono.setText(empleado.getPersona().getTelefono());
        cbSexo.getSelectionModel().select(empleado.getPersona().getSexo());
        cbEstatus.getSelectionModel().select(empleado.getEmpleado().getEstatus() - 1);
        cbRol.getSelectionModel().select(empleado.getEmpleado().getRol() - 1);
        
        image = Utilidades.decodificarImagenBase64(empleado.getEmpleado().getFotografiaBase64());
        ivEmpleado.setImage(image);
        
    }


    private Boolean validarDatos() {
        Boolean error = false;

        if (tfNombre.getText().isEmpty()) {
            error = true;
            lbErrorNombre.setText("Datos no validos");
        }
        if (tfApellidoPaterno.getText().isEmpty()) {
            error = true;
            lbErrorApellidoPaterno.setText("Datos no validos");
        }
        if (tfApellidoMaterno.getText().isEmpty()) {
            error = true;
            lbErrorApellidoMaterno.setText("Datos no validos");
        }
        if (tfCurp.getText().isEmpty()) {
            error = true;

        }
        if (tfCorreo.getText().isEmpty()) {
            error = true;
        }
        if (tfUsername.getText().isEmpty()) {
            error = true;
        }
        if (tfPassword.getText().isEmpty()) {
            error = true;
        }
        return error;
    }

    private void cargarInformacionEstatus() {
        List<Estatus> listaEstatus = EstatusDAO.obtenerEstatus();
        olEstatus = FXCollections.observableArrayList();
        olEstatus.addAll(listaEstatus);
        cbEstatus.setItems(olEstatus);
        cbEstatus.getSelectionModel().select(0);
    }

    private void cargarInformacionSexo() {
        List<String> listaSexo = new ArrayList<>();
        listaSexo.add("Masculino");
        listaSexo.add("Femenino");

        olSexo = FXCollections.observableArrayList();
        olSexo.addAll(listaSexo);
        cbSexo.setItems(olSexo);
        cbSexo.getSelectionModel().select(0);
    }

    private void cargarInformacionRol() {
        List<Rol> listaRoles = RolDAO.obtenerRoles();
        olRol = FXCollections.observableArrayList(listaRoles);
        cbRol.setItems(olRol);
        if (empleado == null) {
            cbRol.getSelectionModel().select(0);
        }
    }

    @FXML
    private void btnBuscarImagen(MouseEvent event) {
        Window ventanaPadre = tfNombre.getScene().getWindow();

        imagen = seleccionarImagen(ventanaPadre);

        if (imagen != null) {
            mostrarEnImageview(imagen);
        }
    }

    private void mostrarEnImageview(File arhivoImagen) {
        try {
            BufferedImage buffer = ImageIO.read(arhivoImagen);
            Image imagen = SwingFXUtils.toFXImage(buffer, null);
            ivEmpleado.setImage(imagen);
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error al cargar",
                    "Error al intentar visualizar la imagen seleccionada",
                    Alert.AlertType.ERROR);
        }
    }
}
