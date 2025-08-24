package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import modelo.Cliente;
import javafx.scene.control.Label;
import modelo.Transaccion;
import utilities.Paths;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;


public class DashBoardController {

    private Cliente cliente;

    @FXML
    private Label dinero;


    @FXML
    private ListView<Transaccion> listMovimientos;


    public DashBoardController() {
    }

    public DashBoardController(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        mostrarDinero(String.valueOf(cliente.getSaldo()));
        mostrarUltimosMovimientos(cliente.getTransacciones());
    }

    // Mostramos el saldo en cuenta que tiene el cliente
    private void mostrarDinero(String mensaje){
        dinero.setText("$"+mensaje);
    }

    private void mostrarUltimosMovimientos(List<Transaccion> transaccion){
        listMovimientos.setItems(FXCollections.observableArrayList(cliente.getTransacciones()));
    }

    @FXML

    public void ClickTransferir(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.CONTACTOS));
        Parent transferirRoot = loader.load();

        // Paso datos al controlador
        ContactosController controller = loader.getController();
        controller.setCliente(cliente);

        // Cambio el contenido de la escena actual
        Scene escenaActual = ((Node) actionEvent.getSource()).getScene();
        escenaActual.setRoot(transferirRoot);

    }
    @FXML
    void ClickDolar(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.DOLAR));
        Parent transferirRoot = loader.load();

        // Paso datos al controlador
        DolarController controller = loader.getController();
        controller.setCliente(cliente);

        // Cambio el contenido de la escena actual
        Scene escenaActual = ((Node) actionEvent.getSource()).getScene();
        escenaActual.setRoot(transferirRoot);
    }


}
