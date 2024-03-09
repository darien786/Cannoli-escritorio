/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.escritorio;

import cannoli.modelo.dao.EmpleadoDAO;
import cannoli.modelo.dao.EstatusDAO;
import cannoli.modelo.dao.RolDAO;
import cannoli.modelo.pojo.DatosRegistroEmpleado;
import cannoli.modelo.pojo.Empleado;
import cannoli.modelo.pojo.Estatus;
import cannoli.modelo.pojo.Mensaje;
import cannoli.modelo.pojo.Persona;
import cannoli.modelo.pojo.Rol;
import cannoli.utils.Utilidades;
import static cannoli.utils.Utilidades.seleccionarImagen;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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

    private DatosRegistroEmpleado empleadoRegistrado;
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
    private void btnGuardar(ActionEvent event) throws IOException {
        guardarInformacionEmpleado();

        Mensaje mensaje = EmpleadoDAO.registrarEmpleado(empleadoRegistrado);
        if (!mensaje.getError()) {
            Utilidades.mostrarAlertaSimple("Registro", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
        } else {
            Utilidades.mostrarAlertaSimple("Error", mensaje.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void guardarInformacionEmpleado() throws IOException {
        if (empleado != null) {
            
        }else{
            if (!validarDatos()) {
                llenarDatosEmpleado();
            }
        }
    }
    
    public void obtenerEmpleado(Empleado empleado){
        if(empleado != null){
            this.empleado = empleado;
            obtenerInformacionCompleta(empleado.getIdEmpleado());
        }
    }
    
    public void obtenerInformacionCompleta(Integer idEmpleado){
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(idEmpleado);
        
        Empleado nuevo = EmpleadoDAO.obtenerEmpleadoPorId(empleado);
        if(nuevo != null){
            llenarCamposEmpleado(nuevo);
        }
    }
    
    private void llenarCamposEmpleado(Empleado empleado){
        tfNombre.setText(empleado.getNombreEmpleado());
        tfCurp.setText(empleado.getCurp());
        tfTelefono.setText(empleado.getTelefono());
        cbSexo.getSelectionModel().select("Femenino");
        cbEstatus.getSelectionModel().select(empleado.getEstatus() - 1);
        cbRol.getSelectionModel().select(empleado.getRol() -1);
        
        Image image = Utilidades.decodificarImagenBase64(empleado.getFotografiaBase64());
        ivEmpleado.setImage(image);
        
    }

    private void llenarDatosEmpleado() throws IOException {
        Persona personaEmpleado = new Persona();
        Empleado empleadoNuevo = new Empleado();

        personaEmpleado.setNombrePersona(tfNombre.getText().trim().toString());
        personaEmpleado.setApellidoPaterno(tfApellidoPaterno.getText().trim().toString());
        personaEmpleado.setApellidoMaterno(tfApellidoMaterno.getText().trim().toString());
        personaEmpleado.setTelefono(tfTelefono.getText().trim().toString());
        personaEmpleado.setSexo(cbSexo.getValue().toString());

        empleadoNuevo.setCurp(tfCurp.getText().trim().toString());
        empleadoNuevo.setCorreo(tfCorreo.getText().trim().toString());
        empleadoNuevo.setUsername(tfUsername.getText().trim().toString());
        empleadoNuevo.setContrasenia(tfPassword.getText().trim().toString());
        empleadoNuevo.setEstatus(cbEstatus.getValue().getIdEstatus());
        empleadoNuevo.setRol(cbRol.getValue().getIdRol());

        String ruta = "C:/Cannoli/empleados/" + empleadoNuevo.getCurp() + "/" + empleadoNuevo.getCurp() + ".png";
        String imagenBase64 = Utilidades.convertirImagenABase64(imagen);

        empleadoNuevo.setFotografia(ruta);
        empleadoNuevo.setFotografiaBase64(imagenBase64);

        empleadoRegistrado = new DatosRegistroEmpleado();

        empleadoRegistrado.setEmpleado(empleadoNuevo);
        empleadoRegistrado.setPersona(personaEmpleado);
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

        if (empleado == null) {
            cbEstatus.setDisable(true);
            cbEstatus.getSelectionModel().select(0);
        } else {
            cbEstatus.setDisable(false);
        }
    }

    private void cargarInformacionSexo() {
        List<String> listaSexo = new ArrayList<>();
        listaSexo.add("Masculino");
        listaSexo.add("Femenino");

        olSexo = FXCollections.observableArrayList();
        olSexo.addAll(listaSexo);
        cbSexo.setItems(olSexo);

        if (empleado == null) {
            cbSexo.getSelectionModel().select(0);
        }
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
