package controllers;

import Persistence.ClienteRepository;
import Service.ClienteService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import modelo.Cliente;
import utilities.Paths;

import java.io.IOException;

public class TransferirController {
    private Cliente cliente;

    private Cliente contactoDelCliente;

    @FXML
    private TextField txtMontoAEnviar;

    ClienteService servi = new ClienteService(new ClienteRepository());


    public TransferirController() {
    }


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setContactoDelCliente(Cliente contactoDelCliente) {
        this.contactoDelCliente = contactoDelCliente;
    }




    @FXML
    public void ClickEnviar(javafx.event.ActionEvent event) {
       Double monto= servi.parsearString(txtMontoAEnviar);
        if(monto<0){
            servi.mostrarAlerta("Error","El monto ingresado tiene que ser mayor a 0", Alert.AlertType.ERROR);
        }
        else{
            if(servi.realizarTransferencia(cliente,contactoDelCliente,monto)){
                servi.mostrarAlerta("Exito!","Se transfirieron  $"+monto, Alert.AlertType.INFORMATION );
            }
            else {
                servi.mostrarAlerta("Error", "Dinero en cuenta insuficiente", Alert.AlertType.ERROR);
            }
        }

    }

    public void ClickCancelar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.CONTACTOS));
        Parent contactosRoot = loader.load();

        // Paso datos al controlador
        ContactosController controller = loader.getController();
        controller.setCliente(cliente);

        // Cambio el contenido de la escena actual

        Scene escenaActual = ((Node) event.getSource()).getScene();
        escenaActual.setRoot(contactosRoot);
    }


}
