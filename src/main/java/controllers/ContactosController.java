package controllers;

import Persistence.ClienteRepository;
import Service.ClienteService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import modelo.Cliente;
import utilities.Paths;

import java.io.IOException;
import java.util.List;

public class ContactosController {

    private Cliente cliente;

    @FXML
    private ListView<String> listaContactos;


    @FXML
    private TextField txtAgregarContacto;

    ClienteService servi = new ClienteService(new ClienteRepository());


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        mostrarContactos();
    }



    // mostramos contactos
    public void mostrarContactos() {
        ObservableList<String> contactos = FXCollections.observableArrayList();
        List<Cliente> clientes = servi.traerListaClientes();

        for (Cliente c : clientes) {
            if (!c.getUsuario().equals(cliente.getUsuario())) {
                contactos.add(c.getUsuario());
            }
        }

        listaContactos.setItems(contactos);

        listaContactos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // doble click
                String seleccionado = listaContactos.getSelectionModel().getSelectedItem();
                if (seleccionado != null) {
                    Cliente contacto = servi.traerContacto(seleccionado);
                    try {
                        cargarTransferir(contacto, event); }
                    catch (IOException e) {
                        servi.mostrarAlerta("Error", "No se pudo cargar la ventana", Alert.AlertType.ERROR);
                    e.printStackTrace(); }
                }
            }
        });

    }

    private void cargarTransferir(Cliente contacto, MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.TRANSFERIR));
        Parent transferirRoot = loader.load();

        // Obtener controlador y pasar datos
        TransferirController controller = loader.getController();
        controller.setCliente(cliente);
        controller.setContactoDelCliente(contacto);


        // Reemplazar el contenido de la escena actual
        Scene escenaActual = ((Node) mouseEvent.getSource()).getScene();
        escenaActual.setRoot(transferirRoot);
    }

    public void enterUsuario(ActionEvent actionEvent){
        if (servi.agregarContacto(txtAgregarContacto.getText()) == null) {
            servi.mostrarAlerta("Error", "Contacto Existente", Alert.AlertType.ERROR);
        } else {
            servi.mostrarAlerta("Exitoso", "Contacto agregado", Alert.AlertType.INFORMATION);
            mostrarContactos();

        }
    }


    @FXML
    void eliminarContacto(ActionEvent event) {
        String seleccionado = listaContactos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            servi.mostrarAlerta("Atención", "No seleccionaste ningún contacto", Alert.AlertType.WARNING);
            return;
        }


        servi.eliminarUsuario(seleccionado);


        listaContactos.getItems().remove(seleccionado);

        servi.mostrarAlerta("Éxito", "Contacto eliminado correctamente", Alert.AlertType.INFORMATION);
}


    @FXML
    void ClickRegresar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.DASHBOARD));
        Parent transferirRoot = loader.load();

        // Paso datos al controlador
        DashBoardController controller = loader.getController();
        controller.setCliente(cliente);

        // Cambio el contenido de la escena actual

        Scene escenaActual = ((Node) event.getSource()).getScene();
        escenaActual.setRoot(transferirRoot);
    }
}
