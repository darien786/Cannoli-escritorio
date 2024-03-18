/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannoli.escritorio.productos;

import cannoli.modelo.dao.CategoriaDAO;
import cannoli.modelo.dao.EstatusDAO;
import cannoli.modelo.dao.ProductoDAO;
import cannoli.modelo.pojo.Categoria;
import cannoli.modelo.pojo.Estatus;
import cannoli.modelo.pojo.Mensaje;
import cannoli.modelo.pojo.Producto;
import cannoli.utils.Constantes;
import cannoli.utils.Utilidades;
import static cannoli.utils.Utilidades.seleccionarImagen;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
public class FXMLFormularioProductosController implements Initializable {

    private File imagen;
    private Image image;
    private ObservableList categorias;
    private ObservableList estatus;

    private Producto producto = null;

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfCodigo;
    @FXML
    private Label lbErrorNombre;
    @FXML
    private Label lbErrorApellidoPaterno;
    @FXML
    private Label lbErrorApellidoMaterno;
    @FXML
    private Label lbErrorCURP;
    @FXML
    private Label lbErrorEmail;
    @FXML
    private Label lbErrorCURP1;
    @FXML
    private Label lbErrorCURP2;
    @FXML
    private TextField tfPrecio;
    @FXML
    private TextField tfCantidad;
    @FXML
    private ComboBox<Estatus> cbEstatus;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private ComboBox<Categoria> cbCategoria;
    @FXML
    private DatePicker dpFechaVigencia;
    @FXML
    private ImageView ivProducto;
    @FXML
    private DatePicker dpFechaElaboracion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarInformacionEstatus();
        cargarInformacionCategoria();
    }

    @FXML
    private void btnGuardar(ActionEvent event) {

        if (this.producto == null) {
            this.producto = new Producto();
        }

        if (!validarDatos()) {
            Mensaje mensaje = null;
            if (producto.getIdProducto() != null) {
                llenarProducto(producto);
                mensaje = ProductoDAO.modificarProducto(producto);
                if(!mensaje.getError()){
                    Utilidades.mostrarAlertaSimple("Modificación", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
                }else{
                    Utilidades.mostrarAlertaSimple("Error", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
                }
            } else {
                llenarProducto(producto);
                mensaje = ProductoDAO.registrarProducto(producto);
                if (!mensaje.getError()) {
                    Utilidades.mostrarAlertaSimple("Registro", mensaje.getMensaje(), Alert.AlertType.INFORMATION);
                } else {
                    Utilidades.mostrarAlertaSimple("Error", mensaje.getMensaje(), Alert.AlertType.WARNING);
                }
            }
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

    private void cargarInformacionEstatus() {
        List<Estatus> listaEstatus = EstatusDAO.obtenerEstatus();
        estatus = FXCollections.observableArrayList(listaEstatus);
        cbEstatus.setItems(estatus);
        cbEstatus.getSelectionModel().select(0);
        if (producto != null) {
            cbEstatus.getSelectionModel().select(-1);
        }
    }

    private Boolean validarDatos() {
        boolean error = false;

        if (tfNombre.getText().isEmpty()) {
            error = true;
        }
        if (tfCodigo.getText().isEmpty()) {
            error = true;
        }
        if (tfPrecio.getText().isEmpty()) {
            error = true;
        }
        if (tfCantidad.getText().isEmpty()) {
            error = true;
        }
        if (dpFechaElaboracion.getValue() == null) {
            error = true;
        }
        if (dpFechaVigencia.getValue() == null) {
            error = true;
        }
        if (cbCategoria.getValue() == null) {
            error = true;
        }
        if (cbEstatus.getValue() == null) {
            error = true;
        }
        if (ivProducto.getImage() == null) {
            error = true;
        }
        return error;
    }

    private void llenarProducto(Producto producto) {
        try {
            String ruta = Constantes.PATH_PRODUCTO + tfNombre.getText().trim().toString() + "/" + tfNombre.getText().trim().toString() + ".png";
            String imagenBase64;

            if(imagen == null){
                imagenBase64 = Utilidades.imageToBase64(image);
            }else{
                imagenBase64 = Utilidades.convertirImagenABase64(imagen);
            }
            
            producto.setNombre(tfNombre.getText().trim().toString());
            producto.setCodigo(tfCodigo.getText().trim().toString());
            producto.setPrecio(Float.parseFloat(tfPrecio.getText().trim().toString()));
            producto.setCantidad(Integer.parseInt(tfCantidad.getText().trim().toString()));
            producto.setDescripcion(taDescripcion.getText().trim().toString());
            producto.setCategoria(cbCategoria.getValue().getIdCategoria());
            producto.setEstatus(cbEstatus.getValue().getIdEstatus());
            producto.setFotografia(ruta);
            producto.setFechaElaboracion(dpFechaElaboracion.getValue().toString());
            producto.setFechaVencimiento(dpFechaVigencia.getValue().toString());
            producto.setFotografiaBase64(imagenBase64);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void cargarInformacionProducto(Producto producto){
        tfNombre.setText(producto.getNombre());
        tfCodigo.setText(producto.getCodigo());
        tfPrecio.setText(producto.getPrecio().toString());
        tfCantidad.setText(producto.getCantidad().toString());
        dpFechaElaboracion.setValue(LocalDate.parse(producto.getFechaElaboracion()));
        dpFechaVigencia.setValue(LocalDate.parse(producto.getFechaVencimiento()));
        taDescripcion.setText(producto.getDescripcion());
        cbEstatus.getSelectionModel().select(producto.getEstatus() - 1);
        cbCategoria.getSelectionModel().select(producto.getCategoria() - 1);
        image = Utilidades.decodificarImagenBase64(producto.getFotografiaBase64());
        ivProducto.setImage(image);
    }

    private void cargarInformacionCategoria() {
        List<Categoria> listaCategoria = CategoriaDAO.obtenerCategorias();
        categorias = FXCollections.observableArrayList(listaCategoria);
        cbCategoria.setItems(categorias);
        cbCategoria.getSelectionModel().select(0);
    }

    public void obtenerProducto(Producto producto) {
        if (producto != null) {
            this.producto = ProductoDAO.obtenerProductoPorId(producto.getIdProducto());
            cargarInformacionProducto(this.producto);
        }else{
            cbEstatus.setDisable(true);
        }
    }

    private void mostrarEnImageview(File arhivoImagen) {
        try {
            BufferedImage buffer = ImageIO.read(arhivoImagen);
            Image imagen = SwingFXUtils.toFXImage(buffer, null);
            ivProducto.setImage(imagen);
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error al cargar",
                    "Error al intentar visualizar la imagen seleccionada",
                    Alert.AlertType.ERROR);
        }
    }
}
